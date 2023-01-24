
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public LogAnalyzer newLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        //la.readFile("short-test_log");
        //la.readFile("weblog-short_log");
        la.readFile("weblog1_log");
        return la;
    }
    
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        /*
        // complete method which creates a LogAnalyzer object
        LogAnalyzer la = new LogAnalyzer();
        
        //call readFile on the data file short-test_log
        la.readFile("short-test_log");
        */
        LogAnalyzer la = newLogAnalyzer();
        
        //call printAll to print all the web logs
        la.printAll();
    }
    
    public void testUniqueIP() {
        LogAnalyzer la = newLogAnalyzer();
        //la.readFile("short-test_log");
        System.out.println(la.countUniqueIPs());
    }
    
    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = newLogAnalyzer();
        la.printAllHigherThanNum(400);
    }
    
    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = newLogAnalyzer();
        ArrayList<String> test = la.uniqueIPVisitsOnDay("Mar 24");
        /*
        for (String s : test) {
            System.out.println(s);
        }
        */
        System.out.println(test.size());
    }
    
    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = newLogAnalyzer();
        System.out.println(la.countUniqueIPsInRange(200, 299));
        //System.out.println(la.countUniqueIPsInRange(300, 399));
    }
}
