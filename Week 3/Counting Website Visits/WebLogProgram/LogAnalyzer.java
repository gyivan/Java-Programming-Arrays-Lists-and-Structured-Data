
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
        
        return ipAddr; //returns ArrayList of unique IP addresses that visited on that day
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
     
     public HashMap<String, Integer> countVisitsPerIP() {
         HashMap<String, Integer> map = new HashMap<String, Integer>();
         
         for (LogEntry le: records) {
             
             String ip = le.getIpAddress();
             
             if (!map.containsKey(ip)) { //if does not contain IP address yet
                 map.put(ip, 1);
             } else {
                 map.put(ip, map.get(ip) + 1);
             }
             
         }
         
         return map; //returns HashMap of <IP address : number of times that IP address appears in records/visited the website>
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> map) {
         int result = 0;
         
         for (String s : map.keySet()) {
             if (map.get(s) > result) {
                 result = map.get(s);
             }
         }
         
         return result; //returns the maximum number of visits to this website by a single IP address
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> map) {
         ArrayList<String> arrL = new ArrayList<String>();
         int mostNumVisits = mostNumberVisitsByIP(map);
         for (String s : map.keySet()) {
             if (map.get(s) == mostNumVisits) {
                 arrL.add(s);
             }
         }
         return arrL; //returns an ArrayList of IP addresses that all have the maximum number of visits to this website
     }
     
     public HashMap<String, ArrayList<String>> iPsForDays() {
        HashMap<String, ArrayList<String>> hash = new HashMap<String, ArrayList<String>>();
        
        for (LogEntry le : records) {
            //maps days from web logs to an ArrayList of IP addresses that occurred on that day (including repeated IP addresses)
            String timeStr = le.getAccessTime().toString();
            String dateStr = timeStr.substring(4, 10);
            //System.out.println(dateStr);
            String ip = le.getIpAddress();
            
            if (!hash.containsKey(dateStr)) { //if dateStr is not yet in hash
                ArrayList<String> temp = new ArrayList<String>();
                hash.put(dateStr, temp);
            }
            
            if (hash.containsKey(dateStr)) {
                ArrayList<String> arrL = hash.get(dateStr);
                arrL.add(ip);
            }
            
        }
        
        return hash; //returns a hashmap of <Days : IP addresses that visited on that day>
     }
     
     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> hash) {
         String res = "";
         int mostVisits = 0;
         
         for (String s : hash.keySet()) {
             if (hash.get(s).size() > mostVisits) {
                 res = s;
                 mostVisits = hash.get(s).size();
             }
         }
         
         return res; //returns the day that has the most IP address visits
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> hash, String day) {
         ArrayList<String> res = new ArrayList<String>();
         
         HashMap<String, Integer> visitCount = new HashMap<String, Integer>(); //maps IP addresses to number of times visited in that day
         //hash uses iPsForDays()
         //find all IP addresses (including repeated) that visited on that day
         ArrayList<String> ipAddrs = hash.get(day);
         
         for (String s : ipAddrs) { //for all the IP addresses that visited in that day
             
             if (!visitCount.containsKey(s)) { //map it to the number of times it visited
                 visitCount.put(s, 1);
             } else {
                 visitCount.put(s, visitCount.get(s) + 1);
             }
             
         }
         
         int mostVisits = 0;
         for (int i : visitCount.values()) {
            //find the maximum number of visits
            if (i > mostVisits) {
                mostVisits = i;
            }
         }
         
         for (String ip : visitCount.keySet()) { //for each IP address in the visitCount
             if (visitCount.get(ip) == mostVisits) { //if it has the most number of visits
                 res.add(ip); //add it to the ArrayList result
             }
         }
         
         return res; //returns an ArrayList of IP addresses that had the most accesses on the given day
     }
}
