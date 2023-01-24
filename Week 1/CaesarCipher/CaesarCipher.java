import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        
        //Compute the shifted alphabet
        String shiftedAlphabetUpper = alphabetUpper.substring(key) + alphabetUpper.substring(0, key);
        String shiftedAlphabetLower = alphabetLower.substring(key) + alphabetLower.substring(0, key);
        
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
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        
        //Write down the alphabet
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        
        //Compute the shifted alphabet
        String shiftedAlphabetUpper1 = alphabetUpper.substring(key1) + alphabetUpper.substring(0, key1);
        String shiftedAlphabetLower1 = alphabetLower.substring(key1) + alphabetLower.substring(0, key1);
        
        String shiftedAlphabetUpper2 = alphabetUpper.substring(key2) + alphabetUpper.substring(0, key2);
        String shiftedAlphabetLower2 = alphabetLower.substring(key2) + alphabetLower.substring(0, key2);
        
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
    
    public void testCaesar() {
        /*
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
        */
        //System.out.println(encrypt("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15));
        //System.out.println(encrypt("Just a test string with lots of eeeeeeeeeeeeeeeees", 2));
        //System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
        System.out.println(encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx", 26 - 2, 26 - 20));
    }
    
    public boolean isVowel(char ch) {
        String vowels = "aeiouAEIOU";
        if (vowels.indexOf(ch) != -1) { //ch is a vowel
            return true;
        }
        return false; //ch is not a vowel
    }
    
    public void testIsVowel() {
        System.out.println(isVowel('F'));
        System.out.println(isVowel('A'));
    }
    
    public String replaceVowels(String phrase, char ch) {
        StringBuilder result = new StringBuilder(phrase);
        
        for (int i = 0; i < phrase.length(); i++) {
            char currChar = result.charAt(i);
            
            if (isVowel(currChar)) {
                result.setCharAt(i, ch);
            }
        }
        return result.toString();
    }
    
    public void testReplaceVowels() {
        System.out.println(replaceVowels("Hello World", '*'));
    }
    
    public String emphasize(String phrase, char ch) {
        StringBuilder result = new StringBuilder(phrase);
        
        for (int i = 0; i < phrase.length(); i++) {
            char currChar = result.charAt(i);
            //if char is in an odd number location in the string (index is even)
            if ( ( (currChar == ch) || (currChar == Character.toUpperCase(ch) ) ) && (i%2 == 0) ) {
                result.setCharAt(i, '*');
            }
            
            //if char is at an even number location in the string (index is odd)
            if ( ( (currChar == ch) || (currChar == Character.toUpperCase(ch) ) ) && (i%2 == 1) ) {
                result.setCharAt(i, '+');
            }
            
        }
        return result.toString();
    }
    
    public void testEmphasize() {
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }
}

