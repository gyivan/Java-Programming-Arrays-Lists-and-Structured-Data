import edu.duke.*;
import java.util.*;

public class Assignment1 {
    
    //Create a private variable to store a HashMap to map DNA codons to their count
    private HashMap<String, Integer> codonCount;
    
    //Write a constructor to initialize the HashMap variable
    public Assignment1() {
        codonCount = new HashMap<String, Integer>();
    }
    
    private void buildCodonMap(int start, String dna) {
            
        codonCount.clear();
        
        //build a new map of codons mapped to their counts from the string dna with the reading frame with the position start (a value of 0, 1 or 2)
        String dnaRead = dna.substring(start).trim();
        
        while (dnaRead.length() >= 3) {
            String codon = dnaRead.substring(0, 3);
            dnaRead = dnaRead.substring(3);
            
            if (!codonCount.containsKey(codon)) { //if codon is not yet in map
                codonCount.put(codon, 1);
            } else { //codon is already in map
                codonCount.put(codon, codonCount.get(codon) + 1);
            }
            
        }
        
    }
    
    private String getMostCommonCodon() {
        String result = "";
        int highestCount = 0;
        
        for (String s : codonCount.keySet()) {
            
            if (codonCount.get(s) > highestCount) {
                highestCount = codonCount.get(s);
                result = s;
            }
            
        }
        
        return result;
    }
    
    private void printCodonCounts(int start, int end) {
        
        for (String s : codonCount.keySet()) {
            
            if ( (codonCount.get(s) >= start) && (codonCount.get(s) <= end) ) {
                System.out.println("Codon: " + s + "\t" + "Count: " + codonCount.get(s));
            }
            
        }
        
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase();
        
        for (int i = 0; i <3; i++) {
            
            buildCodonMap(i, dna);
            System.out.println("Reading frame starting with " + i + " results in " + codonCount.size() + " unique codons");
            System.out.println("and most common codon is " + getMostCommonCodon() + " with count " + codonCount.get(getMostCommonCodon()));
            //System.out.println("Counts of codons between 1 and 5 inclusive are:");
            //printCodonCounts(1, 5);
            System.out.println("Counts of codons that occur exactly 7 times are:");
            printCodonCounts(7, 7);
            System.out.println("\n");
            
        }
        
        
    }
    
}
