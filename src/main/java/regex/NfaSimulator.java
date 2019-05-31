
package regex;

//Simulates the given regex-Nfa, trying to find matches.

//Will be removed later
import java.util.ArrayList;
import java.util.List;

public class NfaSimulator {
   private Nfa nfa;
   private List<State> currentStates;
   private List<State> nextStates;
   private boolean match;

    public NfaSimulator(Nfa nfa) {
        this.nfa = nfa;
        this.match = false;
        this.currentStates = new ArrayList<State>();
        this.nextStates = new ArrayList<State>();
    }
    
    public boolean simulate(String input){
        State start = this.nfa.getStartState();
        this.currentStates.add(start);
        
        for (int i = 0; i < input.length(); i++){
            Character inputChar = input.charAt(i);
            this.simulateStep(inputChar);
            if (this.match == true){
                return true;
            }
        }
        return false;
    }

    private void simulateStep(Character inputChar) {
        
    }
    
   
   
}
