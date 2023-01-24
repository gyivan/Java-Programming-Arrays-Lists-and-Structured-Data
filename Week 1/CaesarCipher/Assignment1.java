import edu.duke.*;

public class Assignment1 {
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
    public void testCaesar() {
        int key = 17;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
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

