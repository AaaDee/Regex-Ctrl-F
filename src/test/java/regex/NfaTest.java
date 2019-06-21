
package regex;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class NfaTest {
    
    public NfaTest() {
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
    public void startStateReturnedCorrectly() {
        Nfa nfa = new Nfa("testi");
        State start = nfa.getStartState();
        char startChar = start.getStateChar();
        
        assertEquals(startChar, 't');
    }
}
