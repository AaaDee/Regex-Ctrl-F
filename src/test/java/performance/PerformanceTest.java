
package performance;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import regex.RegexMatcher;
import io.IoReader;
import java.io.File;
import java.util.regex.Pattern;
import java.util.regex.Matcher;



/**
 *
 * @author AD
 */

public class PerformanceTest {
    
    public PerformanceTest() {
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
    public void testSimpleSearch(){
        String name = "Simple search";
        String testFilePath = "./src/test/resources/lorem.txt";
        String regex = "kanada";
        
        this.runTestsForSingleCase(name, testFilePath, regex);
        
    }
    
    @Test
    public void testComplicatedSearch(){
        String name = "Complicated Search";
        String testFilePath = "./src/test/resources/lorem.txt";
        String regex = "b?i*(ps)+u(m|x)oops";
        
        this.runTestsForSingleCase(name, testFilePath, regex);
        
    }

    @Test
    public void testPathologicalAaCase(){
        String name = "Aa-case";
        String testFilePath = "./src/test/resources/aa.txt";
        String regex = "(a+a+)+kanada";
        
        this.runTestsForSingleCase(name, testFilePath, regex);
        
    }
    
    
    
    
    
    private void runTestsForSingleCase(String testName, String textFilepath, String regex){
        IoReader reader = new IoReader(new File(textFilepath)); 
        String text = reader.giveNextLine();

        //Ctrl-F
        long timeElapsed = this.timeWithCtrlF(text, regex, 10);
        String output = "Test: " + testName + " Case: Ctrl-F Time: " + timeElapsed + " ms";
        System.out.println(output);
        
        //Default Java Regex
        timeElapsed = this.timeWithJavaRegex(text, regex, 10);
        output = "Test: " + testName + " Case: Java Regex: " + timeElapsed + " ms";
        System.out.println(output);
        
    }
    
    private long timeWithCtrlF(String text, String regex, int repetitions){
        long totalTime = 0;
        
        for (int i = 0; i < repetitions; i++){
            long startTime = System.currentTimeMillis();
            
            RegexMatcher matcher = new RegexMatcher();
            matcher.isRegexFoundInText(regex, text);
            
            long endTime = System.currentTimeMillis();
            totalTime += endTime - startTime;
        }
        
        long averageTime = totalTime / 10;
        return averageTime;
    }
    
    private long timeWithJavaRegex(String text, String regex, int repetitions){
        long totalTime = 0;
        regex = ".*" + regex;
        
        for (int i = 0; i < repetitions; i++){
            long startTime = System.currentTimeMillis();
            
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);
            matcher.matches();
            
            long endTime = System.currentTimeMillis();
            totalTime += endTime - startTime;
        }
        
        long averageTime = totalTime / 10;
        return averageTime;
    }
}
