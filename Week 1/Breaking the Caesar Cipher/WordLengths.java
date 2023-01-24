
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class WordLengths {
    
    public void countWordLengths(FileResource resource, int[] counts) {
        
        //read the words from resource
        for (String word : resource.words()) { //for all the words in resource
            
            //count the length of the word
            int wordLength = word.length();
            
            for (int i = 0; i < wordLength; i++) {
            
                if ( (i == 0) || (i == wordLength - 1) ) { //if the word has a non-letter as the first or last character, it should not be counted as part of the word length
                    
                    if (!Character.isLetter(word.charAt(i))) {
                        wordLength--;
                    }
                }
                
            }
            
            //store these counts in the array counts
            if (wordLength >= counts.length - 1) {
                counts[counts.length - 1]++;
            } else {
                counts[wordLength]++;
            }
            
        }
        
    }
    
    public void testCountWordLengths() {
        FileResource file = new FileResource("manywords.txt");
        int[] counts = new int[31];
        countWordLengths(file, counts);
        System.out.println(indexOfMax(counts));
        /*
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                System.out.println(counts[i] + " words of length " + i);
            }
        }
        */
    }
    
    public int indexOfMax(int[] values) {
        int currentLargest = 0;
        int idx = 0;
        
        for (int i = 0; i < values.length; i++) {
            
            if (values[i] > currentLargest) {
                currentLargest = values[i];
                idx = i;
            }
            
        }
        
        return idx; //return the index position of the largest element in values
    }
    
}
