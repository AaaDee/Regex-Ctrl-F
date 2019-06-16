package program;

import ui.UiController;

/**
 * The main class, used only for setting up the program and the ui.
 * 
 * @author AD
 */
public class Main {

    /**
     * Starts the program, setting up the main program and the ui.
     * 
     * @param args Standard setup
     */
    public static void main(String[] args) {
        Program program = new Program();
        UiController controller = new UiController(program);
        controller.createUi();
    }
    
}