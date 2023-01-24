
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>(); //initialize records to an empty ArrayList
     }
        
     public void readFile(String filename) {
         // complete method to create a FileResource and to iterate over all the lines in the file
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()) { //for each line
             //create a LogEntry
             LogEntry entry = WebLogParser.parseEntry(line);
             
             //store it in the records ArrayList
             records.add(entry);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
