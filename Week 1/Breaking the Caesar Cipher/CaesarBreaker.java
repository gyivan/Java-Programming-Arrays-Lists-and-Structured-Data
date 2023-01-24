
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;


public class CaesarBreaker {
    
    String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public void countLetters(String s, int[] counts) {
        
        for (int i = 0; i < s.length(); i++) {
            
            char currentChar = s.charAt(i);
            
            currentChar = Character.toLowerCase(currentChar);
            
            if (alphabet.indexOf(currentChar) != -1) {
                counts[alphabet.indexOf(currentChar)] += 1;
            }
            
        }
        
    }
    
    public int maxIndex(int[] values) {
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
    
    public String decrypt(String encrypted) {
        
        int key = getKey(encrypted);
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encrypt(encrypted, 26 - key);
        return message;
        
    }
    
    public String halfOfString(String message, int start) {
        StringBuilder output = new StringBuilder("");
        
        for (int i = start; i < message.length(); i += 2) {
            output.append(message.charAt(i));
        }
        
        return output.toString();
    }
    
    public int getKey(String s) {
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
    
    public String decryptTwoKeys(String encrypted) {
        StringBuilder output = new StringBuilder("");
        
        String firstKeyStr = halfOfString(encrypted, 0);
        String secondKeyStr = halfOfString(encrypted, 1);
        
        int key1 = getKey(firstKeyStr);
        int key2 = getKey(secondKeyStr);
        
        System.out.println("Key 1 is: " + key1);
        System.out.println("Key 2 is: " + key2);
        
        CaesarCipher cc = new CaesarCipher();
        String message = cc.encryptTwoKeys(encrypted, 26 - key1, 26 - key2);
        return message;
    }
    
    public void testDecrypt() {
        //System.out.println(decrypt("Yjhi p ithi higxcv lxiw adih du ttttttttttttttttth"));
        //System.out.println(decrypt("Lwuv c vguv uvtkpi ykvj nqvu qh gggggggggggggggggu"));
        //System.out.println(decryptTwoKeys("Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu"));
        //System.out.println(decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko"));
        //System.out.println(halfOfString("Qbkm Zgis", 1));
        FileResource f = new FileResource("mysteryTwoKeysPractice.txt");
        String fileStr = f.asString();
        System.out.println(decryptTwoKeys(fileStr));
    }
    
}
