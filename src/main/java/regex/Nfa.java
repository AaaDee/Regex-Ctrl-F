
package regex;

/// importing the interface only
import java.util.Deque;

/// importing ArrayList for now, will be removed later
import java.util.ArrayList;
import java.util.List;

public class Nfa {
    private Deque<Fragment> stack;
    
    public Nfa() {
        this.stack = new StackImplementation();
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
        
        List<Exit> newExits = new ArrayList(firstFragment.getExitList());
        newExits.addAll(secondFragment.getExitList());
        
        Fragment newFragment = new Fragment(split, newExits);
        stack.push(newFragment);
    }
    
    private void addQuestionMark() {
        Fragment previousFragment = stack.pop();
        State split = new State('S', previousFragment.getState());
        
        List<Exit> newExits = new ArrayList(previousFragment.getExitList());
        newExits.add(new Exit(split, 2));
        
        Fragment newFragment = new Fragment(split, newExits);
        stack.push(newFragment);
    }
    
    private void addStar(){
        Fragment previousFragment = stack.pop();
        State split = new State('S', previousFragment.getState());
        
        List<Exit> exits = new ArrayList<>();
        exits.add(new Exit(split, 2));

        Fragment newFragment = new Fragment(split, exits);
        
        this.patch(previousFragment, newFragment);
        
        stack.push(newFragment);
    }
    
    private void addPlus() {
        Fragment previousFragment = stack.pop();
        State split = new State('S', previousFragment.getState());
        
        List<Exit> exits = new ArrayList<>();
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