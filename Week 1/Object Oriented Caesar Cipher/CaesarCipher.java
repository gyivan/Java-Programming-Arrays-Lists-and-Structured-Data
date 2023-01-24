
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarCipher {
    
    private String alphabetUpper;
    private String alphabetLower;
    private String shiftedAlphabetUpper;
    private String shiftedAlphabetLower;
    private int mainKey;
    
    public CaesarCipher(int key) {
        
        alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabetUpper = alphabetUpper.substring(key) + alphabetUpper.substring(0, key);
        
        alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        shiftedAlphabetLower = alphabetLower.substring(key) + alphabetLower.substring(0, key);
        
        mainKey = key;
        
    }
    
    public String encrypt(String input) {
        
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx1 = alphabetUpper.indexOf(currChar);
            int idx2 = alphabetLower.indexOf(currChar);
            //If currChar is in the alphabet
            if(idx1 != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar1 = shiftedAlphabetUpper.charAt(idx1);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar1);
            }
            
            if (idx2 != -1) {
                char newChar2 = shiftedAlphabetLower.charAt(idx2);
                encrypted.setCharAt(i, newChar2);
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    
    public String decrypt(String input) {
        
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        
        return cc.encrypt(input);
    }
    
}
