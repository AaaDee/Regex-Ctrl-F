import io.IoReader;
import ui.Ui;
import ui.UiController;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import program.Program;
import java.io.File;

public class UITest {
    
    public UITest() {
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
    public void fileReadRegexWorksCorrectly(){
        //update this
        
        Program program = new Program();
        UiController controller = new UiController(program);
        controller.createUi();
        
        String testfilename = "./src/main/resources/samples/lorem.txt";
        File file = new File(testfilename);
        
        
        
        
    }
 
}
