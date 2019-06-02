package program;

import ui.*;
import io.*;
import regex.*;


public class Main {


    public static void main(String[] args) {
/*
        UI demo = new UI( );
        demo.setVisible(true);
        
        String testfilename = "./src/main/resources/samples/lorem.txt";
        IoReader reader = new IoReader(testfilename);
  */      
        
        String regex = "aaaa+bc";
        String text = "aaabcde";
        
        RegexMatcher matcher = new RegexMatcher();
        boolean isMatch = matcher.isRegexFoundInText(regex, text);
        int match = matcher.getFirstMatchPosition(regex, text);
        System.out.println(isMatch);
        System.out.println(match);

    }
    
}