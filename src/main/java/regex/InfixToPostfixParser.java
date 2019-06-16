package regex;

import util.MyStack;

/**
 * A parser which turns the inputted regex into a form that can be read
 * easier by the program.
 * 
 * @author AD
 */
public class InfixToPostfixParser {
    private final char[] operators = {'|','+','?','*'};
    
    /**
     * Converts a given string into postfix format.
     * 
     * @param input The regex string as inputted by the user
     * @return The regex string in postfix format
     */
    public String convertInfixToPostfix(String input) {
        String output = "";
        input = this.formatInput(input);
        
        MyStack<Character> stack = new MyStack<>();
        
        for (int i = 0; i < input.length(); i++) {
            Character inputChar = input.charAt(i);
            
            switch (inputChar) {
                case '(':
                    stack.push(inputChar);
                    break;
                
                case ')':
                    while (!stack.peek().equals('(')) {
                        output += stack.pop();
                    }
                    stack.pop();
                    break;
                
                default:
                    while (!stack.isEmpty()) {
                        Character topOfStack = stack.peek();
                        
                        if (this.getPrecedence(topOfStack) >= this.getPrecedence(inputChar)) {
                            output += stack.pop();
                        } else {
                            break;
                        }
                    }
                    stack.push(inputChar);
                    break;
                    
            }
        }
        
        while (!stack.isEmpty()) {
            output += stack.pop();
        }
        
        
        return output;
    }
    
    
    
    // formats the input by adding . as a concatenation operator
    private String formatInput(String input) {
        input = input.toLowerCase();
        
        String output = "";
        
        for (int i = 0; i < input.length(); i++) {
            Character inputChar = input.charAt(i);
            
            if (i < input.length() - 1) {
                Character nextChar = input.charAt(i + 1);
                
                output += inputChar;
                
                // checks, whether . needs to be added
                if (!inputChar.equals('(')
                        && !nextChar.equals(')') 
                        && !this.isAnOperator(nextChar)
                        && !inputChar.equals('|')) {
                    output += '.';
                }
            }
        }
        // adding the last characted as a corner case
        output += input.charAt(input.length() - 1);
               
        return output;
    }
        
    
    // checks whether c is in the list of operator characters
    private boolean isAnOperator(char c) {
        
        for (int i = 0; i < operators.length; i++) {
            if (operators[i] == c) {
                return true;
            }
        }
        return false;
    }
    
    // precedence values for all operators used, and a default case
    private Integer getPrecedence(Character c) {
        switch (c) {
            case '(':
                return 1;
            case '|':
                return 2;
            case '.':
                return 3;
            case '?':
                return 4; 
            case '*':
                return 4; 
            case '+':
                return 4;
            default:
                return 5;
        }
    }
}
