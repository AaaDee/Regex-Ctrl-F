
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import regex.InfixToPostfixParser;


public class InfixParserTests {
    private static InfixToPostfixParser parser;
    
    public InfixParserTests() {
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
    public void formatterRecognizesAnOperator(){
        assertTrue(parser.isAnOperator('+'));
    }
    
    @Test
    public void formatterRecognizesAnNonOperator(){
        assertFalse(parser.isAnOperator('a'));
    }
    
    @Test
    public void formatterFormatsBasicString(){
        String input = "Testi";
        
        String output = parser.formatInput(input);
        
        assertEquals(output, "t.e.s.t.i");     
    }
    
    @Test
    public void formatterFormasStringWithOperators(){
        String input = "T?(es)ti";
        String output = parser.formatInput(input);
        
        assertEquals(output, "t?.(e.s).t.i");       
    }
    
    @Test
    public void postfixParsedForStringWithoutOperands(){
        String input = "abc";
        String output = parser.convertInfixToPostfix(input);
        
        assertEquals(output, "ab.c.");
    }
    
    @Test
    public void postfixParsedForShortStringWithOperators(){
        String input = "a(bb)+a";
        String output = parser.convertInfixToPostfix(input);
        
        assertEquals(output, "abb.+.a.");
    }
    
}
