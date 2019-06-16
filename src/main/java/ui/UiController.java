
package ui;

import javax.swing.JFileChooser;
import java.io.File;
import program.Program;

public class UiController {
    private Ui ui;
    private Program program;

    public UiController(Program program) {
        this.program = program;
    }
    
    public void createUi(){
        this.ui = new Ui(this);
        this.ui.setVisible(true);
    }
    
    void chooseFileButtonClicked() {
        final JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(this.ui);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            this.program.setFile(this, file);
        }
    }

    public void setFileLabel(String name) {
        String newText = "File chosen: " + name;
        this.ui.setFileTextContent(newText);
        
    }

    void runFileButtonClicked() {
        this.program.runRegexFromBeginningOfText(this);
    }

    public String getRegexString() {
        return this.ui.getRegexFieldContent();
    }

    public void setNoMatchesFound() {
        this.ui.setDisplayContent("No matches found");
    }

    public void setMatchFoundAt(int matchLine, int matchIndex) {
        String newText = "Match found at line " +
                matchLine +
                ", starting from character " +
                matchIndex;
        
        this.ui.setDisplayContent(newText);
    }


    
}
