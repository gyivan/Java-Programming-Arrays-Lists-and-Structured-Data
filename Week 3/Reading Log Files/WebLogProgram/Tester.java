
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method which creates a LogAnalyzer object
        LogAnalyzer la = new LogAnalyzer();
        
        //call readFile on the data file short-test_log
        la.readFile("short-test_log");
        
        //call printAll to print all the web logs
        la.printAll();
    }
}
