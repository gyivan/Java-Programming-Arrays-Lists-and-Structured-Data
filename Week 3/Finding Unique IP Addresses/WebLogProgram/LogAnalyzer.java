
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
     private ArrayList<String> uniqueIPs;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>(); //initialize records to an empty ArrayList
         uniqueIPs = new ArrayList<String>();
     }
        
     public void readFile(String filename) {
         // complete method to create a FileResource and to iterate over all the lines in the file
         FileResource fr = new FileResource(filename);
         records.clear();
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
     
     public int countUniqueIPs() {
         uniqueIPs.clear();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             
             if (!uniqueIPs.contains(ip)) { //if unique IP list does not contain IP address
                uniqueIPs.add(ip);
             }
             
         }
         
         return uniqueIPs.size();
     }
     
     public void printAllHigherThanNum(int num) {
         //examine all the web log entries in records
         for (LogEntry le : records) {
         
             if (le.getStatusCode() > num) { //print those LogEntrys that have a status code greater than num
                 System.out.println(le);
             }
             
         }
         
     }
     
     public ArrayList uniqueIPVisitsOnDay(String someday) {
        ArrayList<String> ipAddr = new ArrayList<String>();
        
        //accesses the web logs in records
        for (LogEntry le : records) {
            //convert date to string format
            String dateStr = le.getAccessTime().toString();

            //find unique IP addresses that had access on the given day
            if (dateStr.indexOf(someday) != -1) {
                String ip = le.getIpAddress();
                
                if (!ipAddr.contains(ip)) {
                    ipAddr.add(ip);
                }
            }
        }
        
        return ipAddr;
     }
     
     public int countUniqueIPsInRange(int low, int high) {
         //returns number of unique IP addresses in records that have a status code in the range from low to high, inclusive
         ArrayList<String> ipAddr = new ArrayList<String>();
         
         for (LogEntry le : records) {
            
             if ( (le.getStatusCode() >= low) && (le.getStatusCode() <= high) ) {
                 String ip = le.getIpAddress();
                 
                 if (!ipAddr.contains(ip)) {
                     ipAddr.add(ip);
                 }
                 
             }
             
         }
         
         return ipAddr.size();
     }
}
