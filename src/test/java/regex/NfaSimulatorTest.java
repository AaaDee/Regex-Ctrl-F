/*
Mainly tests that the simulator runs without problems, main regex testing
is done under RegexMatcherTest class.
*/

package regex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class NfaSimulatorTest {
    
    public NfaSimulatorTest() {
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
    public void matchOnASimpleText() {
        Nfa nfa = new Nfa("testi");
        NfaSimulator simulator = new NfaSimulator(nfa);
        
        simulator.simulate("testitesti");
        assertTrue(simulator.isMatch());
    }
    
    @Test
    public void matchIndexCorrectOnASimpleText() {
        Nfa nfa = new Nfa("testi");
        NfaSimulator simulator = new NfaSimulator(nfa);
        
        simulator.simulate("testitesti");
        assertEquals(simulator.getFirstMatchPosition(), 0);
    }

}
