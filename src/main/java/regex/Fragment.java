
package regex;

// These will be removed with the completed version
import util.DynamicArray;

public class Fragment {
    private State state;
    private DynamicArray<Exit> exitList;

    public Fragment(State state, DynamicArray<Exit> exitList) {
        this.state = state;
        this.exitList = exitList;
    }

    public Fragment(State state) {
        this.state = state;
        this.exitList = new DynamicArray<Exit>();
        this.exitList.add(new Exit(state, 1));
    }
    
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

    public State getState() {
        return state;
    }

    public DynamicArray<Exit> getExitList() {
        return exitList;
    }
}
