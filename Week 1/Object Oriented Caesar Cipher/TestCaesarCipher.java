
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipher {

    String alphabet = "abcdefghijklmnopqrstuvwxyz";

    private void countLetters(String s, int[] counts) {
        
        for (int i = 0; i < s.length(); i++) {
            
            char currentChar = s.charAt(i);
            
            currentChar = Character.toLowerCase(currentChar);
            
            if (alphabet.indexOf(currentChar) != -1) {
                counts[alphabet.indexOf(currentChar)] += 1;
            }
            
        }
        
    }
    
    private int maxIndex(int[] values) {
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
    
    public void simpleTests() {
    
        //read in a file as a string
        FileResource f = new FileResource();
        String fileStr = f.asString();
        
        //create a CaesarCipher object with key 18
        CaesarCipher cc = new CaesarCipher(18);
        
        //encrypt the String read in using the CaesarCipher object
        String encrypted = cc.encrypt(fileStr);
        
        //print the encrypted String
        System.out.println(encrypted);
        
        //decrypt the encrypted String using the decrypt method
        //String decrypted = cc.decrypt(encrypted);
        //System.out.println(decrypted);
        
        breakCaesarCipher(encrypted);
        
    }
    
    public void test() {
        
        String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipher cc = new CaesarCipher(15);
        String encrypted = cc.encrypt(input);
        System.out.println(encrypted);
        
    }
    
    private int getKey(String s) {
        //call countLetters to get an array of the letter frequencies in String s
        int[] counts = new int[26];
        
        countLetters(s, counts);
        
        //use maxIndex to calculate the index of the largest letter frequency
        //max count corresponds to letter 'e' which is index 4
        int key = 0;
        
        if (maxIndex(counts) >= 4) {
            key = maxIndex(counts) - 4;
        } else {
            key = maxIndex(counts) + 22;
        }
        
        
        return key;
    }
    
    public void breakCaesarCipher(String input) {
        CaesarCipher cc = new CaesarCipher(getKey(input));
        String decrypted = cc.decrypt(input);
        System.out.println(decrypted);
    }
    
}
