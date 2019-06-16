package regex;

import util.DynamicArray;

/**
 * A class representing a fragment of the NFA i.e. a collection of states
 * and transition arrows.
 * 
 * @author AD
 */
public class Fragment {
    private final State state;
    private final DynamicArray<Exit> exitList;

    /**
     * Constructs a fragment from a state and a existing list of exits.
     * 
     * @param state The starting state of the fragment
     * @param exitList The exits (outgoing arrows) of the fragment
     */
    public Fragment(State state, DynamicArray<Exit> exitList) {
        this.state = state;
        this.exitList = exitList;
    }

    /**
     * Creates a fragment from a single state, setting state's exit 1
     * as the only exit.
     * 
     * @param state The starting state of the fragment
     */
    public Fragment(State state) {
        this.state = state;
        this.exitList = new DynamicArray<>();
        this.exitList.add(new Exit(state, 1));
    }
    
    /**
     * Connects the exits on the exit list to a new exit state.
     * 
     * @param newExit The new exit state
     */
    public void setExits(State newExit) {
        for (int i = 0; i < exitList.getSize(); i++) {
            Exit exit = exitList.get(i);
            State oldState = exit.getState();
            
            if (exit.getExitNumber() == 1) {
                oldState.setExitState1(newExit);
            } else {
                oldState.setExitState2(newExit);
            }
        }
    }

    /**
     * Returns the starting state of the fragment.
     * 
     * @return The starting state
     */
    public State getState() {
        return state;
    }

    /**
     * Returns the exits (outgoing arrows) of the fragment as a list.
     * 
     * @return The exit list of the fragment
     */
    public DynamicArray<Exit> getExitList() {
        return exitList;
    }
}
