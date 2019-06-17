package regex;

/**
 * A representation of a single NFA state and its exits.
 * 
 * @author AD
 */
public class State {
    private Character stateChar;
    private State exitState1;
    private State exitState2;
    private int startOfHit;
    private int lastList;
    
    /**
     * Creates a new state with single exit.
     * 
     * @param stateChar The state character
     * @param exitState1 The first (and only) exit state
     */
    public State(char stateChar, State exitState1) {
        this.stateChar = stateChar;
        this.exitState1 = exitState1;
        this.exitState2 = null;
        
        this.startOfHit = -1;
        this.lastList = -1;
    }

    /**
     * Creates a new state with two exits.
     * 
     * @param stateChar The state character
     * @param exitState1 The first exit state
     * @param exitState2 The second exit state
     */
    public State(Character stateChar, State exitState1, State exitState2) {
        this.stateChar = stateChar;
        this.exitState1 = exitState1;
        this.exitState2 = exitState2;
        
        this.startOfHit = -1;
        this.lastList = -1;
    }

    /**
     * Returns the index of the character from where the earliest possible
     * match with this state was found.
     * 
     * @return The index of the character where a potential hit was found
     */
    public int getStartOfHit() {
        return startOfHit;
    }

    /**
     * Returns the number of the latest simulation step where the state was
     * added to a list, or -1 if no such step exits.
     *  
     * @return The latest step where the step was added to a list or -1
     */
    public int getLastList() {
        return lastList;
    }

    /**
     * Sets the startOfHit variable, indicating the start of the earliest
     * possible match involving this state.
     * 
     * @param startOfHit The index of the first character of a potential match
     */
    public void setStartOfHit(int startOfHit) {
        this.startOfHit = startOfHit;
    }

    /**
     * Sets the latest simulation step where the state was added.
     * 
     * @param lastList The latest simulation step where the state was added
     */
    public void setLastList(int lastList) {
        this.lastList = lastList;
    }

    /**
     * Returns the state character.
     * 
     * @return The state character
     */
    public Character getStateChar() {
        return stateChar;
    }
    
    /**
     * Returns the first exit state.
     * 
     * @return The first exit state
     */
    public State getExitState1() {
        return exitState1;
    }

    /**
     * Returns the second exit state.
     * 
     * @return The second exit state
     */
    public State getExitState2() {
        return exitState2;
    }

    /**
     * Sets a new first exit state.
     * 
     * @param exitState1 The new first exit state
     */
    public void setExitState1(State exitState1) {
        this.exitState1 = exitState1;
    }

    /**
     * Sets a new second exit state.
     * 
     * @param exitState2 The new second exit state
     */
    public void setExitState2(State exitState2) {
        this.exitState2 = exitState2;
    }
    
    
}
