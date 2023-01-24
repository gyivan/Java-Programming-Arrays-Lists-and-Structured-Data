
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class CaesarCipherTwo {

    private String alphabetUpper;
    private String alphabetLower;
    
    private String shiftedAlphabetUpper1;
    private String shiftedAlphabetLower1;
    
    private String shiftedAlphabetUpper2;
    private String shiftedAlphabetLower2;
    
    private int mainKey1;
    private int mainKey2;
    
    public CaesarCipherTwo(int key1, int key2) {
        
        alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        
        shiftedAlphabetUpper1 = alphabetUpper.substring(key1) + alphabetUpper.substring(0, key1);
        shiftedAlphabetLower1 = alphabetLower.substring(key1) + alphabetLower.substring(0, key1);
        
        shiftedAlphabetUpper2 = alphabetUpper.substring(key2) + alphabetUpper.substring(0, key2);
        shiftedAlphabetLower2 = alphabetLower.substring(key2) + alphabetLower.substring(0, key2);
        
        mainKey1 = key1;
        mainKey2 = key2;
        
    }
    
    public String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder(input);
        
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            
            int idx1 = alphabetUpper.indexOf(currChar);
            int idx2 = alphabetLower.indexOf(currChar);
            
            if (i%2 == 0) { //use key 1
                if(idx1 != -1){
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar1 = shiftedAlphabetUpper1.charAt(idx1);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar1);
                }
                
                if (idx2 != -1) {
                    char newChar2 = shiftedAlphabetLower1.charAt(idx2);
                    encrypted.setCharAt(i, newChar2);
                }
            } else { //use key 2
                if(idx1 != -1){
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar1 = shiftedAlphabetUpper2.charAt(idx1);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar1);
                }
                
                if (idx2 != -1) {
                    char newChar2 = shiftedAlphabetLower2.charAt(idx2);
                    encrypted.setCharAt(i, newChar2);
                }
            }
        
        
        }
    
        return encrypted.toString();
    }
    
    public String decrypt(String input) {
        CaesarCipherTwo cc = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        
        return cc.encrypt(input);
    }
    
}
