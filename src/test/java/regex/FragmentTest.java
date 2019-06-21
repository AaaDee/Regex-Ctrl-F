
package regex;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class FragmentTest {
    
    public FragmentTest() {
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
    public void exitsSetCorrectly() {
        State start = new State('s', null);
        Fragment fragment = new Fragment(start);
         
        State exit = new State('e', null);
         
        fragment.setExits(exit);
         
        assertEquals(start.getExitState1(), exit);
    }
}
