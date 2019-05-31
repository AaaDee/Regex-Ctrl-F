package io;

import java.util.Scanner;
import java.io.File;

public class IoReader {

    private Scanner reader;

    public IoReader(String filename) {
        try {
            reader = new Scanner(new File(filename));
            System.out.println("jee");
        } catch (Exception e) {
            System.out.println("Virhe: " + e.getMessage());
        }
    }
    
    
    private String giveNextLine() {
        if (reader.hasNext()){
            return reader.nextLine();
        }
        
        return "\\E";
    }
}
