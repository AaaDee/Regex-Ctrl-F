package program;

import ui.*;
import io.*;
import regex.*;


public class Main {


    public static void main(String[] args) {
        UI demo = new UI( );
        demo.setVisible(true);
        
        String testfilename = "./src/main/resources/samples/lorem.txt";
        IoReader reader = new IoReader(testfilename);
        
        InfixToPostfixParser parser = new InfixToPostfixParser();
        
        String input = "abc";
        
        String formatted = parser.convertInfixToPostfix(input);
        System.out.println(formatted);
        Nfa nfa = new Nfa();
        nfa.initializeNfa(formatted);

    }
    
}