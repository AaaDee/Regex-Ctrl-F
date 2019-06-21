
package program;

import io.IoReader;
import java.io.File;
import regex.RegexMatcher;
import ui.UiController;

/**
 * The main program, which coordinates the work between the UI and the regex engine.
 *
 * @author AD
 */
public class Program {
    private File file;
    private int currentLine;
    private int currentChar;
    
    /**
     * Sets a new file to program's memory, and informs the controller.
     * 
     * @param controller The UiController of the UI currently running
     * @param file The new file
     */
    public void setFile(UiController controller, File file) {
        this.file = file;
        controller.setFileLabel(file.getName());
    }
    
    /**
     * Runs the regex on the text from the beginning, taking the inputs from
     * the UI.
     * 
     * @param controller The UiController of the UI currently running
     */
    public void runRegexFromBeginningOfText(UiController controller) {
        if (!this.checkForFile(controller)) {
            return;
        }
        
        String regex = controller.getRegexString();
        IoReader reader = new IoReader(file);
        RegexMatcher matcher = new RegexMatcher();
        
        int matchIndex = -99;
        
        try {
            matchIndex = this.feedInputLines(matcher, reader, regex);
        } catch (Exception e) {
            controller.showRegexErrorMessage();
            return;
        }
        
        if (matchIndex == -99) {
            controller.setNoMatchesFound();
        } else {
            controller.setMatchFoundAt(this.currentLine, matchIndex + 1);
        }
        
    }
    
    private int feedInputLines(RegexMatcher matcher, IoReader reader, String regex) {
        this.currentLine = 0;
        int matchIndex = -99;
        
        while (reader.hasNextLine()) {
            this.currentLine++;
            String text = reader.giveNextLine();
            
            matchIndex = matcher.getFirstMatchPosition(regex,text);
            
            if (matchIndex != -99) {
                break;
            }
        }
        return matchIndex;
    }    

    private boolean checkForFile(UiController controller) {
        if (this.file == null) {
            controller.setNoFileFoundMessage();
            return false;
        }
        return true;
    }
}
