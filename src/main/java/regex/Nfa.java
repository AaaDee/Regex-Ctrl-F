
package regex;

/// importing the interface only
import java.util.Deque;

/// importing ArrayList for now, will be removed later
import java.util.ArrayList;

public class Nfa {
    private Deque<Fragment> stack;
    
    public Nfa() {
        this.stack = new StackImplementation();
    }
    
    public void initializeNfa(String input){
        for (int i = 0; i < input.length(); i++){
            Character inputChar = input.charAt(i);
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
        
        Fragment combinedFragment = new Fragment(firstFragment.getState(),
            secondFragment.getExitList());
        
        stack.push(combinedFragment);
    }
    
    private void patch(Fragment firstFragment, Fragment secondFragment){
        firstFragment.setExits(secondFragment.getState());
    }
}