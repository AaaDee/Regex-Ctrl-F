package utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import util.MyStack;


public class MyStackTest {
    
    public MyStackTest() {
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
    public void emptyStackIsEmpty() {
        MyStack<Integer> stack = new MyStack();
        assertTrue(stack.isEmpty());
    }
    
    @Test
    public void nonEmptyStackIsMotEmpty() {
        MyStack<Integer> stack = new MyStack();
        stack.push(1);
        assertFalse(stack.isEmpty());
    }
    
    @Test
    public void stackPopsACorrectThing() {
        MyStack<Integer> stack = new MyStack();
        stack.push(1);
        stack.push(2);
        
        int topOfStack = stack.pop();
        
        assertEquals(topOfStack, 2);
    }
    
    @Test
    public void stackPopsACorrectThingAfterPop() {
        MyStack<Integer> stack = new MyStack();
        stack.push(1);
        stack.push(2);
        
        stack.pop();
        int topOfStack = stack.pop();
        
        assertEquals(topOfStack, 1);
    }
    
    @Test
    public void stackPeeksACorrectThing() {
        MyStack<Integer> stack = new MyStack();
        stack.push(1);
        stack.push(2);
        
        int topOfStack = stack.peek();
        
        assertEquals(topOfStack, 2);
    }
    
    @Test
    public void stackPeeksACorrectThingAfterPop() {
        MyStack<Integer> stack = new MyStack();
        stack.push(1);
        stack.push(2);
        
        stack.pop();
        int topOfStack = stack.peek();
        
        assertEquals(topOfStack, 1);
    }
}
