
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import regex.RegexMatcher;

/**
 *
 * @author ad
 */
public class RegexTest {
    
    public RegexTest() {
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
    public void correctMatchOnSimpleString() {
        String regex = "a";
        String text = "abc";
        
        RegexMatcher matcher = new RegexMatcher();
        boolean matchFound = matcher.isRegexFoundInText(regex, text);
        assert(matchFound);
    }
    
    @Test
    public void correctMatchOnSimpleString2() {
        String regex = "b";
        String text = "abc";
        
        RegexMatcher matcher = new RegexMatcher();
        boolean matchFound = matcher.isRegexFoundInText(regex, text);
        assert(matchFound);
    }
    
    @Test
    public void correctNonMatchOnSimpleString() {
        String regex = "e";
        String text = "abc";
        
        RegexMatcher matcher = new RegexMatcher();
        boolean matchFound = matcher.isRegexFoundInText(regex, text);
        assertFalse(matchFound);
    }
    
    @Test
    public void correctMatchOnQuestionMark(){
        String regex = "bc?";
        String text = "abc";
        
        RegexMatcher matcher = new RegexMatcher();
        boolean matchFound = matcher.isRegexFoundInText(regex, text);
        assert(matchFound);
    }
    
    @Test
    public void correctMatchOnQuestionMarkInTheBeginning(){
        String regex = "a?bc";
        String text = "abc";
        
        RegexMatcher matcher = new RegexMatcher();
        boolean matchFound = matcher.isRegexFoundInText(regex, text);
        assert(matchFound);
    }
    
    @Test
    public void positionOfSimpleStringCorrect(){
        String regex = "a";
        String text = "abcde";
        
        RegexMatcher matcher = new RegexMatcher();
        int match = matcher.getFirstMatchPosition(regex, text);
        assertEquals(match, 0);
    }
    
    @Test
    public void positionOfSimpleStringCorrect2(){
        String regex = "b";
        String text = "abcde";
        
        RegexMatcher matcher = new RegexMatcher();
        int match = matcher.getFirstMatchPosition(regex, text);
        assertEquals(match, 1);
    }
    
    @Test
    public void positionOfSimpleStringWothQuestionMarkCorrect(){
        String regex = "ak?";
        String text = "abcde";
        
        RegexMatcher matcher = new RegexMatcher();
        int match = matcher.getFirstMatchPosition(regex, text);
        assertEquals(match, 0);
    }
    
    @Test
    public void positionOfSimpleStringWothQuestionMarkinBeginningCorrect(){
        String regex = "f?b";
        String text = "abcde";
        
        RegexMatcher matcher = new RegexMatcher();
        int match = matcher.getFirstMatchPosition(regex, text);
        assertEquals(match, 1);
    }
    
    @Test
    public void nonMatchPositionMinus99(){
        String regex = "fdasgdsa";
        String text = "abcde";
        
        RegexMatcher matcher = new RegexMatcher();
        int match = matcher.getFirstMatchPosition(regex, text);
        assertEquals(match, -99);
    }
    
    @Test
    public void simpleVerticalLineMatchesCorrectly(){
        String regex = "ae|bc";
        String text = "abc";
        
        RegexMatcher matcher = new RegexMatcher();
        boolean matchFound = matcher.isRegexFoundInText(regex, text);
        assert(matchFound);
    }
    
    @Test
    public void simpleVerticalLineNonMatchesCorrectly(){
        String regex = "ae|fc";
        String text = "abc";
        
        RegexMatcher matcher = new RegexMatcher();
        boolean matchFound = matcher.isRegexFoundInText(regex, text);
        assertFalse(matchFound);
    }
    
    @Test
    public void simpleStarMatchesCorrectly(){
        String regex = "ab*c";
        String text = "abc";
        
        RegexMatcher matcher = new RegexMatcher();
        boolean matchFound = matcher.isRegexFoundInText(regex, text);
        assert(matchFound);
    }
    
    @Test
    public void StarLoopMatchesCorrectly(){
        String regex = "ab*c";
        String text = "abbbbc";
        
        RegexMatcher matcher = new RegexMatcher();
        boolean matchFound = matcher.isRegexFoundInText(regex, text);
        assert(matchFound);
    }
    
    @Test
    public void wrongStarIgnoredCorrectly(){
        String regex = "ax*bc";
        String text = "abc";
        
        RegexMatcher matcher = new RegexMatcher();
        boolean matchFound = matcher.isRegexFoundInText(regex, text);
        assert(matchFound);
    }
    
    @Test
    public void simplePlusMatchesCorrectly(){
        String regex = "a+bc";
        String text = "abc";
        
        RegexMatcher matcher = new RegexMatcher();
        boolean matchFound = matcher.isRegexFoundInText(regex, text);
        assert(matchFound);
    }
    
    @Test
    public void plusMatchesToDoubleCorrectly(){
        String regex = "a+bc";
        String text = "aabc";
        
        RegexMatcher matcher = new RegexMatcher();
        boolean matchFound = matcher.isRegexFoundInText(regex, text);
        assert(matchFound);
    }
    
    @Test
    public void plusDoesntMatchToNone(){
        String regex = "a+bc";
        String text = "bc";
        
        RegexMatcher matcher = new RegexMatcher();
        boolean matchFound = matcher.isRegexFoundInText(regex, text);
        assertFalse(matchFound);
    }
}
    

