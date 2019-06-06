
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
     *
     * @param controller
     * @param file
     */
    public void updateFile(UiController controller, File file) {
        this.file = file;
        controller.setFileLabel(file.getName());
    }
    
    /**
     *
     * @param controller
     */
    public void startRegex(UiController controller) {
        String regex = controller.getRegexString();
        IoReader reader = new IoReader(file);
        RegexMatcher matcher = new RegexMatcher();
        
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
        
        if (matchIndex == -99) {
            controller.setNoMatchesFound();
        } else {
            controller.setMatchFoundAt(this.currentLine, matchIndex + 1);
        }
        
    }
    
    /**
     *
     * @param controller
     * @param lineNumber
     * @param characterIndex
     */
    public void findNextFrom(UiController controller, int lineNumber, int characterIndex) {
        
        
    }
    
    private void runRegexFrom(int lineNumber, int characterIndex) {
        
    }

    
    
}
