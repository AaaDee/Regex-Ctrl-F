/* 
A Parser which converts the standard infix regex notation to more easily
parseable postfix notation
*/

package regex;

// Will be replaced later
import java.util.Stack;





public class InfixToPostfixParser {
    private char[] Operators = {'|','+','?','*'};
    
    public String convertInfixToPostfix(String input){
        String output = "";
        input = this.formatInput(input);
        
        Stack<Character> stack = new Stack<Character>();
        
        for (int i = 0; i < input.length(); i++){
            Character inputChar = input.charAt(i);
            
            switch(inputChar){
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
                    while (!stack.isEmpty()){
                        Character topOfStack = stack.peek();
                        
                        if (this.getPrecedence(topOfStack) >= this.getPrecedence(inputChar)){
                            output += stack.pop();
                        } else {
                            break;
                        }
                    }
                    stack.push(inputChar);
                    break;
                    
            }
        }
        
        while (!stack.isEmpty()){
            output += stack.pop();
        }
        
        
        return output;
    }
    
    
    
    // formats the input by adding . as a concatenation operator
    public String formatInput(String input){
        input = input.toLowerCase();
        
        String output = "";
        
        for (int i = 0; i < input.length(); i++){
            Character inputChar = input.charAt(i);
            
            if (i < input.length() - 1){
                Character nextChar = input.charAt(i+1);
                
                output += inputChar;
                
                // checks, whether . needs to be added
                if(!inputChar.equals('(') && 
                        !nextChar.equals(')') &&
                        !this.isAnOperator(nextChar) && 
                        !inputChar.equals('|')){
                    output += '.';
                }
            }
        }
        
        // adding the last characted as a corner case
        output += input.charAt(input.length()-1);
               
      return output;
    }
        
    
    // checks whether c is in the list of operator characters
    public boolean isAnOperator(char c){
        
        for (int i=0; i < Operators.length; i++){
            if (Operators[i] == c){
                return true;
            }
        }
        return false;
    }
    
    private Integer getPrecedence(Character c){
        switch(c){
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
