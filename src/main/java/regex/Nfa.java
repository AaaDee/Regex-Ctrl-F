
package regex;

import util.DynamicArray;
import util.MyStack;

public class Nfa {
    private MyStack<Fragment> stack;
    
    public Nfa() {
        this.stack = new MyStack<>();
    }
    
    public void initializeNfa(String input){
        String postfixInput = this.convertToPostfix(input);
        
        for (int i = 0; i < postfixInput.length(); i++){
            Character inputChar = postfixInput.charAt(i);
            this.insert(inputChar);
        }
        
        // add a final character for a matchstate
        this.insert('M');
        this.insert('.');
    }
    
    public State getStartState(){
        State start = stack.peek().getState();
        return(start);
    }
    
    // check whether the input char is a normal or some special character, and process accordingly
    public void insert(Character insertedChar){
        switch(insertedChar){
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
        
        DynamicArray<Exit> newExits = new DynamicArray<>();
        newExits.addAll(firstFragment.getExitList());
        newExits.addAll(secondFragment.getExitList());
        
        Fragment newFragment = new Fragment(split, newExits);
        stack.push(newFragment);
    }
    
    private void addQuestionMark() {
        Fragment previousFragment = stack.pop();
        State split = new State('S', previousFragment.getState());
        
        DynamicArray<Exit> newExits = new DynamicArray<>();
        newExits.addAll(previousFragment.getExitList());
        newExits.add(new Exit(split, 2));
        
        Fragment newFragment = new Fragment(split, newExits);
        stack.push(newFragment);
    }
    
    private void addStar(){
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
    
    private void patch(Fragment firstFragment, Fragment secondFragment){
        firstFragment.setExits(secondFragment.getState());
    }
    
    private void patchToState(Fragment fragment, State state){
        fragment.setExits(state);
    }

    private String convertToPostfix(String input) {
        InfixToPostfixParser parser = new InfixToPostfixParser();
        String postfixInput = parser.convertInfixToPostfix(input);
        return postfixInput;
    }

    
    
}