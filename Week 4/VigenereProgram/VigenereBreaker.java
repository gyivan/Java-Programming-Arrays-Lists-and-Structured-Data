import java.util.*;
import java.io.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {

        StringBuilder input = new StringBuilder(message);
        StringBuilder output = new StringBuilder();
        
        for (int i = whichSlice; i < message.length(); i += totalSlices) {
            output.append(input.charAt(i));
        }
        
        return output.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        
        //make use of CaesarCracker class and sliceString method to find the shift for each index in the key
        for (int i = 0; i < klength; i++) { //for each key-digit
            String slicedMessage = sliceString(encrypted, i, klength); //first get the relevant sliced string
            CaesarCracker cc = new CaesarCracker();
            int k = cc.getKey(slicedMessage);
            key[i] = k; //get the key for that sliced string
        }
        
        //fill in the key (which is an array of integers) and return it
        return key;
    }
    
    /*
    public void breakVigenere () {
        //create a new FileResource using its default constructor
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource("secretmessage1.txt");
        
        //use the AsString method to read the entire contents of the file into a String
        String encrypted = fr.asString();
        //String encrypted = "Hhdiu LVXNEW uxh WKWVCEW, krg k wbbsqa si Mmwcjiqm";
        
        //use the tryKeyLength method to find the key for the message you read in (pass 'e' for mostCommon)
        //int[] key = tryKeyLength(encrypted, 5, 'e');
        int[] key = tryKeyLength(encrypted, 4, 'e');
        
        //create a new VigenereCipher, passing in the key that tryKeyLength found
        VigenereCipher vc = new VigenereCipher(key);
        
        //use the VigenereCipher's decrypt method to decrypt the encrypted message
        String decrypted = vc.decrypt(encrypted);
        
        //finally, print out the decrypted message
        System.out.println(decrypted);
    }
    */
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public HashSet<String> readDictionary(FileResource fr) {
        
        HashSet<String> res = new HashSet<String>(); //make a new HashSet of Strings
        
        for (String line : fr.lines()) { //read each line in fr
            
            //convert that line to lowercase and put that line into HashSet
            res.add(line.toLowerCase());
            
        }
        
        return res;
    }
    
    public int countWords(String message, HashSet<String> dictionary) {
        int res = 0;
        
        //split the message into words
        String[] splitMsg = message.split("\\W+");
        
        for (int i = 0; i < splitMsg.length; i++) { //iterate over those words
            String word = splitMsg[i].toLowerCase();
            
            //see how many of them are "real words" that appear in the dictionary (in lowercase)
            if (dictionary.contains(word)) {
                res++;
            }
            
        }
        
        return res;
    }
    
    public String breakForLanguage(String encrypted, HashSet<String> dictionary) {
        
        int largestCount = 0; //largest count of real words
        String bestDecryption = "";
        int[] bestKey = tryKeyLength(encrypted, 1, mostCommonCharIn(dictionary));
        /*
        int[] bestKey = tryKeyLength(encrypted, 38, mostCommonCharIn(dictionary));
        VigenereCipher vc = new VigenereCipher(bestKey);
        String decrypted = vc.decrypt(encrypted);
        largestCount = countWords(decrypted, dictionary);
        */
        
        //try all key lengths from 1 to 100 using tryKeyLength
        for (int i = 1; i <= 100; i++) {
            
            int[] key = tryKeyLength(encrypted, i, mostCommonCharIn(dictionary));
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            
            //count the number of words in the decrypted string
            if (countWords(decrypted, dictionary) > largestCount) {
                bestDecryption = decrypted;
                largestCount = countWords(decrypted, dictionary);
                bestKey = key;
            }
            
        }
        
        System.out.println("Best key is: " + Arrays.toString(bestKey));
        System.out.println("Key length is: " + bestKey.length);
        System.out.println("Most number of valid words: " + largestCount);
        System.out.println("\n");
        
        return bestDecryption;
    }
    
    public void breakVigenere () {
        //create a new FileResource using its default constructor
        //FileResource fr = new FileResource();
        //FileResource fr = new FileResource("secretmessage3.txt");
        FileResource fr = new FileResource("secretmessage4.txt");
        
        //use the AsString method to read the entire contents of the file into a String
        String encrypted = fr.asString();
        //String encrypted = "Hhdiu LVXNEW uxh WKWVCEW, krg k wbbsqa si Mmwcjiqm";
        
        /*
        //use the tryKeyLength method to find the key for the message you read in (pass 'e' for mostCommon)
        //int[] key = tryKeyLength(encrypted, 5, 'e');
        //int[] key = tryKeyLength(encrypted, 4, 'e');
        
        //create a new VigenereCipher, passing in the key that tryKeyLength found
        VigenereCipher vc = new VigenereCipher(key);
        
        //use the VigenereCipher's decrypt method to decrypt the encrypted message
        String decrypted = vc.decrypt(encrypted);
        
        
        //make a new FileResource to read from the English dictionary file provided
        FileResource fileR = new FileResource("dictionaries/English");
        */
        
        //read multiple dictionaries
        DirectoryResource dr = new DirectoryResource();
        
        HashMap<String, HashSet<String>> langMap = new HashMap<String, HashSet<String>>();
        
        for (File f : dr.selectedFiles()) {
            String filename = f.getName(); //get filename
            FileResource fileR = new FileResource("dictionaries/" + filename);
            
            //use readDictionary method to read the contents of that file into a HashSet of Strings
            HashSet<String> dict = readDictionary(fileR);
            
            langMap.put(filename, dict);
            System.out.println(filename + " dictionary processing completed.");
        }
        
        //use breakForLanguage method to decrypt the encrypted message
        String decrypted = breakForAllLangs(encrypted, langMap);

        //finally, print out the decrypted message
        System.out.println(decrypted);
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public char mostCommonCharIn(HashSet<String> dictionary) {
        char res = 'a';
        int mostCommonCount = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        
        for (String word : dictionary) { //iterate across the HashSet
            
            for (int i = 0; i < word.length(); i++) {
                if (!map.containsKey(word.charAt(i))) { //if map does not contain letter
                    map.put(word.charAt(i), 1); //add it to map with count of 1
                } else {
                    map.put(word.charAt(i), map.get(word.charAt(i)) + 1); //else increase the count by 1
                }
            }
            
        }
        
        for (char c : map.keySet()) {
            if (map.get(c) > mostCommonCount) {
                res = c;
                mostCommonCount = map.get(c);
            }
        }
        
        //System.out.println("Most common character is: \'" + res + "\' with count " + mostCommonCount);
        return res; //returns the most common character in that dictionary
    }
    
    public String breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages) {
        //HashMap<String, HashSet<String>> maps <language, words in that language>
        int highestNumWords = 0;
        String bestLang = "";
        String bestDecryption = "";
        
        //iterate over the languages.keySet() to get the name of each language
        for (String language : languages.keySet()) {
            HashSet<String> dict = languages.get(language); //use .get() to look up the corresponding dictionary for that language
            String decrypted = breakForLanguage(encrypted, dict);
            int numWords = countWords(decrypted, dict);
            if (numWords > highestNumWords) { //if the new language is a better decrypting tool
                bestLang = language;
                highestNumWords = numWords;
                bestDecryption = decrypted;
            }
        }
        
        //print out the decrypted message
        //System.out.println(bestDecryption);
        
        //print out the language identified for the message
        System.out.println("\n");
        System.out.println("Language identified for decrypting: " + bestLang);
        System.out.println("\n");
        
        return bestDecryption;
    }
    
    public void test() {
        //System.out.println(sliceString("abcdefghijklm", 0, 3));
        /*
        FileResource fr = new FileResource("secretmessage1.txt");
        String frMsg = fr.asString();
        int[] key = tryKeyLength(frMsg, 4, 'e');
        System.out.println(Arrays.toString(key));
        */
        //FileResource fr = new FileResource("dictionaries/English");
        //mostCommonCharIn(readDictionary(fr));
        breakVigenere();
    }
    
}
