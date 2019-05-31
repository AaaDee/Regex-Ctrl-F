
package regex;


public class State {
    private Character stateChar;
    private State exitState1;
    private State exitState2;

    public State(char stateChar, State exitState1) {
        this.stateChar = stateChar;
        this.exitState1 = exitState1;
        this.exitState2 = null;
    }

    public State(Character stateChar, State exitState1, State exitState2) {
        this.stateChar = stateChar;
        this.exitState1 = exitState1;
        this.exitState2 = exitState2;
    }

    public Character getStateChar() {
        return stateChar;
    }
    
    public State getExitState1() {
        return exitState1;
    }

    public State getExitState2() {
        return exitState2;
    }

    public void setExitState1(State exitState1) {
        this.exitState1 = exitState1;
    }

    public void setExitState2(State exitState2) {
        this.exitState2 = exitState2;
    }
    
    
}
