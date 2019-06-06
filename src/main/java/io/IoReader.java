package io;

import java.io.File;
import java.util.Scanner;


public class IoReader {
    private Scanner reader;

    public IoReader(File file) {
        try {
            reader = new Scanner(file);
        } catch (Exception e) {
            System.out.println("Virhe: " + e.getMessage());
        }
    }
    
    
    public boolean hasNextLine() {
        return reader.hasNext();
    }
    
    public String giveNextLine() {
        if (reader.hasNext()) {
            return reader.nextLine();
        }
        
        return "\\E";
    }
}
