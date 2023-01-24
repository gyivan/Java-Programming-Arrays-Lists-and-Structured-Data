
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipherTwo {
    
    private String alphabet = "abcdefghijklmnopqrstuvwxyz";

    private String halfOfString(String message, int start) {
        StringBuilder output = new StringBuilder("");
        
        for (int i = start; i < message.length(); i += 2) {
            output.append(message.charAt(i));
        }
        
        return output.toString();
    }
    
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
        CaesarCipherTwo cc = new CaesarCipherTwo(17, 3);
        
        //encrypt the String read in using the CaesarCipher object
        String encrypted = cc.encrypt(fileStr);
        
        //print the encrypted String
        System.out.println(encrypted);
        
        //decrypt the encrypted String using the decrypt method
        //String decrypted = cc.decrypt(encrypted);
        //System.out.println(decrypted);
        
        breakCaesarCipherTwo(encrypted);    
        
    }
    
    public void test() {
        
        String input = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        CaesarCipherTwo cc = new CaesarCipherTwo(14, 24);
        //String encrypted = cc.encrypt(input);
        //System.out.println(encrypted);
        //String decrypted = cc.decrypt(input);
        //System.out.println(decrypted);
        //breakCaesarCipherTwo("Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!");
        
        FileResource f = new FileResource("mysteryTwoKeysQuiz.txt");
        String fStr = f.asString();
        breakCaesarCipherTwo(fStr);
        
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
    
    public void breakCaesarCipherTwo(String input) {
        StringBuilder output = new StringBuilder("");
        
        String firstKeyStr = halfOfString(input, 0);
        String secondKeyStr = halfOfString(input, 1);
        
        int key1 = getKey(firstKeyStr);
        int key2 = getKey(secondKeyStr);
        
        System.out.println("Key 1 is: " + key1);
        System.out.println("Key 2 is: " + key2);
        
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - key1, 26 - key2);
        String message = cc.encrypt(input);
        System.out.println(message);
    }
    
}
