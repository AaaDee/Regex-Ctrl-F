package regex;

import util.DynamicArray;
import util.MyStack;

/**
 * A class for representing the regex string as a nondeterministic finite
 * automaton (NFA). 
 * 
 * @author AD
 */
public class Nfa {
    private final MyStack<Fragment> stack;

    /**
     * Constructs a new NFA from the input string.
     * @param regex The regex string (in infix notation) used to form the NFA.
     */
    public Nfa(String regex) {
        this.stack = new MyStack<>();
        this.initializeNfa(regex);
    }
    
 
    private void initializeNfa(String input) {
        String postfixInput = this.convertToPostfix(input);
        
        // process input characters one by one
        for (int i = 0; i < postfixInput.length(); i++) {
            Character inputChar = postfixInput.charAt(i);
            this.insert(inputChar);
        }
        
        // add a final character for a final matching state
        this.insert('M');
        this.insert('.');
    }
    
    /**
     * Returns the starting state of the NFA.
     * 
     * @return the starting state
     */
    public State getStartState() {
        State start = stack.peek().getState();
        return start;
    }
    
    // check whether the input char is a normal or some special character, and process accordingly
    private void insert(Character insertedChar) {
        switch (insertedChar) {
            case '.':
                this.addPoint();
                break;
            
            case '|':
                this.addVerticalLine();
                break;
                
            case '?':
                this.addQuestionMark();
                break;
                
            case '*':
                this.addStar();
                break;
                
            case '+':
                this.addPlus();
                break;
            
            default:
                this.addNormal(insertedChar); 
                break;
        }
    }
    

    private void addNormal(Character insertedChar) {
        State state = new State(insertedChar, null);
        Fragment fragment = new Fragment(state);
        stack.push(fragment);
    }

    private void addPoint() {
        Fragment secondFragment = stack.pop();
        Fragment firstFragment = stack.pop();
        
        this.patch(firstFragment, secondFragment);
        
        Fragment newFragment = new Fragment(firstFragment.getState(),
            secondFragment.getExitList());
        
        stack.push(newFragment);
    }
    
    private void addVerticalLine() {
        Fragment secondFragment = stack.pop();
        Fragment firstFragment = stack.pop();
        
        State split = new State('S', firstFragment.getState(), secondFragment.getState());
        
        DynamicArray<Exit> newExits = firstFragment.getExitList();
        newExits.addAll(secondFragment.getExitList());
        
        Fragment newFragment = new Fragment(split, newExits);
        stack.push(newFragment);
    }
    
    private void addQuestionMark() {
        Fragment previousFragment = stack.pop();
        State split = new State('S', previousFragment.getState());
        
        DynamicArray<Exit> newExits = previousFragment.getExitList();
        newExits.add(new Exit(split, 2));
        
        Fragment newFragment = new Fragment(split, newExits);
        stack.push(newFragment);
    }
    
    private void addStar() {
        Fragment previousFragment = stack.pop();
        State split = new State('S', previousFragment.getState());
        
        DynamicArray<Exit> exits = new DynamicArray<>();
        exits.add(new Exit(split, 2));

        Fragment newFragment = new Fragment(split, exits);
        
        this.patch(previousFragment, newFragment);
        
        stack.push(newFragment);
    }
    
    private void addPlus() {
        Fragment previousFragment = stack.pop();
        State split = new State('S', previousFragment.getState());
        
        DynamicArray<Exit> exits = new DynamicArray<>();
        exits.add(new Exit(split, 2));

        Fragment newFragment = new Fragment(previousFragment.getState(), exits);
        
        this.patchToState(previousFragment, split);
        
        stack.push(newFragment);
    }
    
    private void patch(Fragment firstFragment, Fragment secondFragment) {
        firstFragment.setExits(secondFragment.getState());
    }
    
    private void patchToState(Fragment fragment, State state) {
        fragment.setExits(state);
    }

    private String convertToPostfix(String input) {
        InfixToPostfixParser parser = new InfixToPostfixParser();
        String postfixInput = parser.convertInfixToPostfix(input);
        return postfixInput;
    }
}