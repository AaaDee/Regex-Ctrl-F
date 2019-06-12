
package regex;

//Simulates the given regex-Nfa, trying to find matches.

import util.DynamicArray;

public class NfaSimulator {
   private Nfa nfa;
   private DynamicArray<State> currentStates;
   private DynamicArray<State> nextStates;
   private DynamicArray<State> startStates;
   private boolean match;
   private int firstMatchPosition;
   int iteration;

    public boolean isMatch() {
        return match;
    }

    public int getFirstMatchPosition() {
        return firstMatchPosition;
    }
    
    public NfaSimulator(Nfa nfa) {
        this.nfa = nfa;
        this.match = false;
        this.currentStates = new DynamicArray<>();
        this.nextStates = new DynamicArray<>();
        this.startStates = new DynamicArray<>();
        this.iteration = 0;
        this.firstMatchPosition = -99;
    }
    
    public void simulate(String input){
        this.addStartStates(nfa.getStartState());
        this.currentStates.addAll(startStates);
        
        for (int i = 0; i < input.length(); i++){
            Character inputChar = input.charAt(i);
            this.simulateStep(inputChar);
            
            
            if (this.match) {
                break;
            }
        }
        
        for (int i = 0; i < this.currentStates.getSize(); i++){
            State state = this.currentStates.get(i);
            if (state.getStateChar().equals('M')){
                this.match = true;
                this.adjustFirstMatch(state);
            }
        }
      
    }

    private void simulateStep(Character inputChar) {
        this.iteration++;
        this.nextStates = new DynamicArray<>();
        
        for (int i = 0; i < this.currentStates.getSize(); i++){
            State state = this.currentStates.get(i);
            if (state.getStateChar().equals('M')){
                this.match = true;
                this.adjustFirstMatch(state);
            }
            
            if (inputChar.equals(state.getStateChar())){
                this.addStateToNextList(state.getExitState1(), state.getStartOfHit());
            }
        }
        
        this.currentStates = this.nextStates;
        this.addStateToCurrentList(this.nfa.getStartState());
    }
    
    private void addStateToList(State state, DynamicArray list, int hit){
        if (this.iteration == state.getLastList()){
            return;
        }
        state.setLastList(this.iteration);
        state.setStartOfHit(hit);
        
        //Special case for the split state
        if (state.getStateChar().equals('S')){
            this.addStateToList(state.getExitState1(), list, hit);
            this.addStateToList(state.getExitState2(), list, hit);
            return;
        }
        
        list.add(state);
    }
    
    private void addStateToNextList(State state, int hit) {        
        this.addStateToList(state, this.nextStates, hit);
    }
    
    private void addStateToCurrentList(State state){
        this.addStateToList(state, this.currentStates, this.iteration);
    }
    
    private void addStartStates(State state){
        this.addStateToList(state, this.startStates, this.iteration);
    }
    
    private void adjustFirstMatch(State state){
        int newMatchPosition = state.getStartOfHit();
        if (this.firstMatchPosition == -99 | this.firstMatchPosition > newMatchPosition){
            this.firstMatchPosition = newMatchPosition;
        }
    }
   
}
