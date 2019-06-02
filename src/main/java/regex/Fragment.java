
package regex;

// These will be removed with the completed version
import java.util.ArrayList;
import java.util.List;

public class Fragment {
   private State state;
   private List<Exit> exitList;

    public Fragment(State state, List<Exit> exitList) {
        this.state = state;
        this.exitList = exitList;
    }

    public Fragment(State state) {
        this.state = state;
        this.exitList = new ArrayList<Exit>();
        this.exitList.add(new Exit(state, 1));
    }
    
    public void setExits(State newExit){
        for (Exit exit : exitList){
            State oldState = exit.getState();
            
            if (exit.getExitNumber() == 1){
                oldState.setExitState1(newExit);
            } else {
                oldState.setExitState2(newExit);
            }
        }
    }

    public State getState() {
        return state;
    }

    public List<Exit> getExitList() {
        return exitList;
    }
}
