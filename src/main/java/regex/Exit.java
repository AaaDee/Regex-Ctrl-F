
package regex;

/**
 * A class used to represent the exits (or outgoing arrows) of an NFA state.
 * 
 * @author AD
 */
public class Exit {
    private final State state;
    private final int exitNumber;

    /**
     * Constructs a new exit with a given starting state an exit number.
     * 
     * @param state The state from where the exit starts
     * @param exit An integer denoting the exit number (1 or 2)
     */
    public Exit(State state, int exit) {
        this.state = state;
        this.exitNumber = exit;
    }

    /**
     * Gives the starting state of the exit i.e. the beginning of the exit arrow.
     * 
     * @return The starting state of the exit
     */
    public State getState() {
        return state;
    }

    /**
     * Gives the order number of the exit, should be either 1 or 2.
     * 
     * @return The exit number
     */
    public int getExitNumber() {
        return exitNumber;
    }
}
