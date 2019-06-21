package io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class IoReaderTest {
    
    public IoReaderTest() {
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
    public void readerReturnsALineCorrectly() {
        File file = new File("./src/test/resources/aa.txt");
        IoReader reader = new IoReader(file);
        String text = reader.giveNextLine();
        assertEquals(text, "aaaaaaaaaaaaaaaaaaaa");
    }
    
    @Test
    public void readerHasLinesWithNewText() {
        File file = new File("./src/test/resources/aa.txt");
        
        IoReader reader = new IoReader(file);
        assertTrue(reader.hasNextLine());
    }
    
    @Test
    public void readerHasNoMoreLinesAfterTextIsDone() {
        File file = new File("./src/test/resources/aa.txt");
        IoReader reader = new IoReader(file);
        String text = reader.giveNextLine();
        assertFalse(reader.hasNextLine());
    }
}
