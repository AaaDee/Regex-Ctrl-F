package program;

import ui.*;
import io.*;


public class Main {


    public static void main(String[] args) {
        UI demo = new UI( );
        demo.setVisible(true);
        
        String testfilename = "./src/main/resources/samples/lorem.txt";
        IOreader reader = new IOreader(testfilename);
        
        
    }
    
}