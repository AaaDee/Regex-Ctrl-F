package regex;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class InfixToPostfixParserTest {
    private static InfixToPostfixParser parser;
    
    public InfixToPostfixParserTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        parser = new InfixToPostfixParser();
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
    public void formatterFormatsBasicString() {
        String input = "Testi";
        
        String output = parser.convertInfixToPostfix(input);
        
        assertEquals(output, "te.s.t.i.");     
    }
    
    @Test
    public void formatterFormasStringWithOperators() {
        String input = "T?(es)ti";
        String output = parser.convertInfixToPostfix(input);
        
        assertEquals(output, "t?es..t.i.");       
    }
    
    @Test
    public void postfixParsedForStringWithoutOperands() {
        String input = "abc";
        String output = parser.convertInfixToPostfix(input);
        
        assertEquals(output, "ab.c.");
    }
    
    @Test
    public void postfixParsedForShortStringWithOperators() {
        String input = "a(bb)+a";
        String output = parser.convertInfixToPostfix(input);
        
        assertEquals(output, "abb.+.a.");
    }
    
}
