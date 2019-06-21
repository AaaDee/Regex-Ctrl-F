package regex;

import util.DynamicArray;

/**
 * A simulator for running a supplied NFA on a text, keeping track of where
 * a match has been found.
 * 
 * @author AD
 */
public class NfaSimulator {
    private Nfa nfa;
    private DynamicArray<State> currentStates;
    private DynamicArray<State> nextStates;
    private final DynamicArray<State> startStates;
    private boolean match;
    private int firstMatchPosition;
    private int iteration;

    /**
     * Tells whether the simulator has found a match yet.
     * 
     * @return Has the simulator found a match yet
     */
    public boolean isMatch() {
        return match;
    }

    /**
     * Returns the index of the character on the text string where the match
     * that was found started, or -99 if no matches have been found.
     * 
     * @return The index of the first character of the match found, or -99 if no matches
     */
    public int getFirstMatchPosition() {
        return firstMatchPosition;
    }
    
    /**
     * Initializes a simulator with a given NFA.
     * 
     * @param nfa The NFA used for simulation
     */
    public NfaSimulator(Nfa nfa) {
        this.nfa = nfa;
        this.match = false;
        this.currentStates = new DynamicArray<>();
        this.nextStates = new DynamicArray<>();
        this.startStates = new DynamicArray<>();
        this.iteration = 0;
        this.firstMatchPosition = -99;
    }
    
    /**
     * Simulates the NFA on the given text string, storing
     * results as instance variables.
     * 
     * @param input The text string on which the NFA/regex is run.
     */
    public void simulate(String input) {
        this.addStartStates(nfa.getStartState());
        this.currentStates.addAll(startStates);
        
        for (int i = 0; i < input.length(); i++) {
            Character inputChar = input.charAt(i);
            this.simulateStep(inputChar);
            
            
            if (this.match) {
                break;
            }
        }
        
        for (int i = 0; i < this.currentStates.getSize(); i++) {
            State state = this.currentStates.get(i);
            if (state.getStateChar().equals('M')) {
                this.match = true;
                this.adjustFirstMatch(state);
            }
        }
      
    }

    private void simulateStep(Character inputChar) {
        this.iteration++;
        this.nextStates = new DynamicArray<>();
        
        for (int i = 0; i < this.currentStates.getSize(); i++) {
            State state = this.currentStates.get(i);
            if (state.getStateChar().equals('M')) {
                this.match = true;
                this.adjustFirstMatch(state);
            }
            
            if (inputChar.equals(state.getStateChar())) {
                this.addStateToNextList(state.getExitState1(), state.getStartOfHit());
            }
        }
        
        this.currentStates = this.nextStates;
        this.addStateToCurrentList(this.nfa.getStartState());
    }
    
    // adds state to the list of the potential states where the NFA could be in.
    private void addStateToList(State state, DynamicArray list, int hit) {
        if (this.iteration == state.getLastList()) {
            return;
        }
        state.setLastList(this.iteration);
        state.setStartOfHit(hit);
        
        //Special case for the split state
        if (state.getStateChar().equals('S')) {
            this.addStateToList(state.getExitState1(), list, hit);
            this.addStateToList(state.getExitState2(), list, hit);
            return;
        }
        
        list.add(state);
    }
    
    private void addStateToNextList(State state, int hit) {        
        this.addStateToList(state, this.nextStates, hit);
    }
    
    private void addStateToCurrentList(State state) {
        this.addStateToList(state, this.currentStates, this.iteration);
    }
    
    private void addStartStates(State state) {
        this.addStateToList(state, this.startStates, this.iteration);
    }
    
    private void adjustFirstMatch(State state) {
        int newMatchPosition = state.getStartOfHit();
        if (this.firstMatchPosition == -99 | this.firstMatchPosition > newMatchPosition) {
            this.firstMatchPosition = newMatchPosition;
        }
    }
   
}
