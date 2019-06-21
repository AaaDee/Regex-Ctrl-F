
package ui;

import java.io.File;
import javax.swing.JFileChooser;
import program.Program;

/**
 * A class responsible for the interaction between the UI and the rest
 * of the program.
 * 
 * @author AD
 */
public class UiController {
    private Ui ui;
    private Program program;

    /**
     * Creates a new controller with an associated instance of a program class.
     * 
     * @param program The instance of a program class connected to the controller
     */
    public UiController(Program program) {
        this.program = program;
    }
    
    /**
     * Creates a new UI and sets it visible.
     */
    public void createUi() {
        this.ui = new Ui(this);
        this.ui.setVisible(true);
    }
    
    /**
     *  Responds to the click of a choose file button by opening a file selector.
     */
    public void chooseFileButtonClicked() {
        final JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(this.ui);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            this.program.setFile(this, file);
        }
    }

    /**
     * Updates the UI with a new filename.
     * 
     * @param name The new filename
     */
    public void setFileLabel(String name) {
        String newText = "File chosen: " + name;
        this.ui.setFileTextContent(newText);
        
    }

    /**
     * Passes the click of a Run button to the program.
     */
    public void runFileButtonClicked() {
        this.program.runRegexFromBeginningOfText(this);
    }

    /**
     * Returns the content of the regex field of the UI.
     * 
     * @return The content of the regex field of the UI.
     */
    public String getRegexString() {
        return this.ui.getRegexFieldContent();
    }

    /**
     * Sets the UI display to show that no matches were found.
     */
    public void setNoMatchesFound() {
        this.ui.setDisplayContent("No matches found");
    }

    /**
     * Sets the UI to display that a match was found at a given line
     * and at a given position.
     * 
     * @param matchLine The line where the match was found
     * @param matchIndex The index of the character where the match started
     */
    public void setMatchFoundAt(int matchLine, int matchIndex) {
        String newText = "Match found at line "
                + matchLine
                + ", starting from character " 
                + matchIndex;
        
        this.ui.setDisplayContent(newText);
    }

    /**
     * Sets an error message showing that the search failed.
     */
    public void showRegexErrorMessage() {
        String errorText = "The regex term couldn't be parsed, please try again.";
        this.ui.setDisplayContent(errorText);
    }

    /**
     * Sets an error message showing that no file was found.
     */
    public void setNoFileFoundMessage() {
        String errorText = "Please choose a file first.";
        this.ui.setDisplayContent(errorText);
    }

    /**
     * Returns the UI connected to the controller.
     * 
     * @return The UI connected to the controller.
     */
    public Ui getUi() {
        return ui;
    }
    
}
