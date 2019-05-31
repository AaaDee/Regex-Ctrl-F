
package regex;


// A class used to identify the correct exit paths (or "arrows") from a fragment
public class Exit {
    private State state;
    private int exitNumber;

    public Exit(State state, int exit) {
        this.state = state;
        this.exitNumber = exit;
    }

    public State getState() {
        return state;
    }

    public int getExitNumber() {
        return exitNumber;
    }
    
    
}
