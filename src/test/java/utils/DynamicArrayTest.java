
package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import util.DynamicArray;


public class DynamicArrayTest {
    
    public DynamicArrayTest() {
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
    public void newArrayShouldBeSizeZero(){
        DynamicArray<Integer> testArray = new DynamicArray<>();
        int size = testArray.getSize();
        assertEquals(size, 0);
    }
    
    @Test
    public void addingIntegersIncreasesSize(){
        DynamicArray<Integer> testArray = new DynamicArray<>();
        testArray.add(10);
        
        int size = testArray.getSize();
        assertEquals(size, 1);
    }
    
    @Test
    public void addedIntegerCanBeRetrieved(){
        DynamicArray<Integer> testArray = new DynamicArray<>();
        testArray.add(10);
        
        int number = testArray.get(0);
        assertEquals(number, 10);
    }
    
    @Test
    public void addedIntegerCanBeRetrievedAfterSizeDouble(){
        DynamicArray<Integer> testArray = new DynamicArray<>();
        
        for (int i = 0; i < 16; i++){
            testArray.add(1);
        }
        
        testArray.add(10);
        
        int number = testArray.get(16);
        assertEquals(number, 10);
    }
    
    @Test
    public void sizeReturnedCorrectlyAfterSizeDouble(){
        DynamicArray<Integer> testArray = new DynamicArray<>();
        
        for (int i = 0; i < 17; i++){
            testArray.add(1);
        }
        
        int size = testArray.getSize();
        assertEquals(size, 17);
    }
   
}
