import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {

    private HashMap<String, ArrayList> map;
    
    public WordsInFiles() {
        map = new HashMap<String, ArrayList>();
    }
    
    private void addWordsFromFile(File f) {
        
        //add all the words from f into the map
        FileResource fr = new FileResource(f.getName());
        
        for (String word : fr.words()) {
            
            if (!map.containsKey(word)) { //if a word is not in the map
                //create a new ArrayList of type String with this word
                ArrayList<String> arrL = new ArrayList<String>();
                
                //map the word to this ArrayList
                map.put(word, arrL);
            }
            
            if (!map.get(word).contains(f.getName())) { //if the filename is not already on the ArrayList
                //add the filename to the ArrayList
                map.get(word).add(f.getName());
            }
            
        }
        
    }
    
    private void buildWordFileMap() {
        //clear the map
        map.clear();
        
        //use DirectoryResource to select a group of files
        DirectoryResource dr = new DirectoryResource();
        
        //for each file
        for (File f : dr.selectedFiles()) {
            //put all of its words into the map by calling the method addWordsFromFile
            addWordsFromFile(f);
        }
        
    }
    
    private int maxNumber() {
        int result = 0;
        
        for (String word : map.keySet()) {
            
            //get the length of the ArrayList
            if (map.get(word).size() > result) {
                result = map.get(word).size();
            }
            
        }
        
        return result;
    }
    
    private ArrayList wordsInNumFiles(int number) {
    
        ArrayList<String> result = new ArrayList<String>();
        
        for (String word : map.keySet()) {
            if (map.get(word).size() == number) {
                result.add(word);
            }
        }
        
        return result;
    }
    
    private void printFilesIn(String word) {
        //prints the names of the files this word appears in
        ArrayList<String> arrL = map.get(word);
        
        for (String filename : arrL) {
            System.out.println(filename);
        }
        
    }
    
    public void tester() {
        buildWordFileMap();
        /*
        for (String word : map.keySet()) {
            System.out.println("Key is: " + word);
            System.out.println("Files are: ");
            printFilesIn(word);
            System.out.println("\n");
        }
        */
        int number = 4;
        System.out.println("Number of words that appear in exactly " + number + " files: " + wordsInNumFiles(number).size());
        //ArrayList<String> arrL = wordsInNumFiles(number);
        /*
        for (String word : arrL) {
            System.out.println(word);
        }
        */
        System.out.println("Greatest number of files a word appears in: " + maxNumber());
        System.out.println("The word \"tree\" appears in files: ");
        ArrayList<String> arrL = map.get("tree");
        for (String filename : arrL) {
            System.out.println(filename);
        }
    }
    
}
