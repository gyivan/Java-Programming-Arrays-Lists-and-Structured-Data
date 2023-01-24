import edu.duke.*;
import java.util.*;

public class GladLibMap {
    
    private HashMap<String, ArrayList<String>> myMap = new HashMap<String, ArrayList<String>>(); //maps category to ArrayList of words in that category
    
    /*
    private ArrayList<String> adjectiveList;
    private ArrayList<String> nounList;
    private ArrayList<String> colorList;
    private ArrayList<String> countryList;
    private ArrayList<String> nameList;
    private ArrayList<String> animalList;
    private ArrayList<String> timeList;
    private ArrayList<String> verbList;
    private ArrayList<String> fruitList;
    */
   
    private ArrayList<String> usedWords;
    private int wordsReplaced = 0;
    private ArrayList<String> usedCategories;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";
    
    public GladLibMap(){
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        usedWords = new ArrayList<String>();
        usedCategories = new ArrayList<String>();
    }
    
    public GladLibMap(String source){
        initializeFromSource(source);
        myRandom = new Random();
        usedWords = new ArrayList<String>();
        usedCategories = new ArrayList<String>();
    }
    
    private void initializeFromSource(String source) {
        String[] categories = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
        
        for (String s : categories) { //iterate over categories array
            
            //for each category, create an ArrayList of the words
            ArrayList<String> words = readIt(source+"/"+s+".txt");
            myMap.put(s, words);
            
        }
        /*
        adjectiveList= readIt(source+"/adjective.txt"); 
        nounList = readIt(source+"/noun.txt");
        colorList = readIt(source+"/color.txt");
        countryList = readIt(source+"/country.txt");
        nameList = readIt(source+"/name.txt");      
        animalList = readIt(source+"/animal.txt");
        timeList = readIt(source+"/timeframe.txt");     
        verbList = readIt(source+"/verb.txt");
        fruitList = readIt(source+"/fruit.txt");
        */
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (!usedCategories.contains(label)) {
            usedCategories.add(label);
        }
        
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        /*
        if (label.equals("country")) {
            return randomFrom(countryList);
        }
        if (label.equals("color")){
            return randomFrom(colorList);
        }
        if (label.equals("noun")){
            return randomFrom(nounList);
        }
        if (label.equals("name")){
            return randomFrom(nameList);
        }
        if (label.equals("adjective")){
            return randomFrom(adjectiveList);
        }
        if (label.equals("animal")){
            return randomFrom(animalList);
        }
        if (label.equals("timeframe")){
            return randomFrom(timeList);
        }
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        if (label.equals("verb")){
            return randomFrom(verbList);
        }
        if (label.equals("fruit")){
            return randomFrom(fruitList);
        }
        */
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        while (usedWords.contains(sub)) {
            sub = getSubstitute(w.substring(first+1,last));
        }
        usedWords.add(sub);
        wordsReplaced++;
        //System.out.println(sub);
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    private int totalWordsInMap() { //returns total number of words in all the ArrayLists in the HashMap
        int result = 0;
        
        for (String s : myMap.keySet()) {
            
            //get ArrayList length
            result += myMap.get(s).size();
            
        }
        
        return result;
    }
    
    private int totalWordsConsidered() {
        int result = 0;
        for (String label : myMap.keySet()) {
            if (usedCategories.contains(label)) {
                result += myMap.get(label).size();
            }
        }
        return result;
    }
    
    public void makeStory(){
        wordsReplaced = 0;
        usedCategories.clear();
        usedWords.clear();
        System.out.println("\n");
        //String story = fromTemplate("data/madtemplate.txt");
        String story = fromTemplate("data/madtemplate2.txt");
        printOut(story, 60);
        System.out.println("\n");
        System.out.println("Number of words replaced: " + wordsReplaced);
        System.out.println("Total words in map: " + totalWordsInMap());
        System.out.println("Total words considered: " + totalWordsConsidered());
    }
    


}
