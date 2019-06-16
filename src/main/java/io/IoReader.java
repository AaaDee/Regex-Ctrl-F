package io;

import java.io.File;
import java.util.Scanner;

/**
 * A reader for converting text files into strings.
 * 
 * @author AD
 */
public class IoReader {
    private Scanner reader;

    /**
     * Creates a new reader with the file to be read as a parameter. 
     * 
     * @param file  file to be read
     */
    public IoReader(File file) {
        try {
            reader = new Scanner(file);
        } catch (Exception e) {
            System.out.println("Virhe: " + e.getMessage());
        }
    }
    
    /**
     * Tells whether the reader has any lines left.
     *
     * @return A boolean indicating whether the reader has lines left.
     */
    public boolean hasNextLine() {
        return reader.hasNext();
    }
    
    /**
     * Gives the next line of the file associated with the reader.
     * 
     * @return The next line of the file as a String or \\E if no lines are found.
     */
    public String giveNextLine() {
        if (reader.hasNext()) {
            return reader.nextLine();
        }
        
        return "\\E";
    }
}
