package ui;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import program.Program;


public class UiTest {
    
    public UiTest() {
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
    public void uiCreatedAtStart() {
        Program program = new Program();
        UiController controller = new UiController(program);
        controller.createUi();

        Ui ui = controller.getUi();
        
        assertNotNull(ui);
    }
 
}
