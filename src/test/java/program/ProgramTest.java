/* 
Main testing of the regex functionality is under the program package,
here is just checked that the main program runs correctly.
*/

package program;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ui.UiController;

/**
 *
 * @author ad
 */
public class ProgramTest {
    
    public ProgramTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void programCanRunASearch() {
        Program program = new Program();
        UiController controller = new UiController(program);
        controller.createUi();
        
        String testfilename = "./src/main/resources/samples/lorem.txt";
        File file = new File(testfilename);
        
        program.setFile(controller, file);
        
        program.runRegexFromBeginningOfText(controller);
    }
}
