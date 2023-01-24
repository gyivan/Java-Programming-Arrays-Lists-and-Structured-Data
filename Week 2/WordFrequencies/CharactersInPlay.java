
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay {
    
    private ArrayList<String> chars;
    private ArrayList<Integer> counts;
    
    public CharactersInPlay() {
        chars = new ArrayList<String>();
        counts = new ArrayList<Integer>();
    }
    
    private void update(String person) {
        //update the two ArrayLists
        
        //add the character's name if it is not already there
        if (!chars.contains(person)) {
            chars.add(person);
            
            //count this line as one speaking part for this person
            counts.add(1);
        } else {
            int val = counts.get(chars.indexOf(person));
            counts.set(chars.indexOf(person), val + 1);
        }
        
    }
    
    public void findAllCharacters() {
        //clear appropriate instance variables before each new file
        chars.clear();
        counts.clear();
        
        //open a file
        FileResource f = new FileResource();
        
        //reads thie file line-by-line
        for (String line : f.lines()) {
            
            if (line.indexOf(".") != -1) { //for each line, if there is a period on the line,
                
                //extract the possible name of the speaking part
                String name = line.substring(0, line.indexOf("."));
                
                //call update to count it as in occurrence for this person
                update(name);
                
            }
            
        }
        
    }
    
    public void tester() {
        findAllCharacters();
        
        /*
        int threshold = 20; //change this number to determine main characters
        
        for (int i = 0; i < chars.size(); i++) {
            //for each main character, print out the main character and the number of speaking parts that character has
            if (counts.get(i) > threshold) {
                System.out.println(chars.get(i) + "\t" + counts.get(i));
            }
            
        }
        */
       charactersWithNumParts(10, 15);
       
    }
    
    private void charactersWithNumParts(int num1, int num2) {
        
        //print out the names of all those characters that have exactly number speaking parts, where num1 <= number <= num2
        for (int i = 0; i < chars.size(); i++) {
            //for each main character, print out the main character and the number of speaking parts that character has
            if ( (counts.get(i) >= num1) && (counts.get(i) <= num2) ) {
                System.out.println(chars.get(i) + "\t" + counts.get(i));
            }
            
        }
        
    }
    
}
