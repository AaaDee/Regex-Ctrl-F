package util;

/**
 * Creates a new stack implementation for a given class.
 * 
 * @author AD
 * @param <T> The class of objects to be contained in the stack
 */
public class MyStack<T> {
    private final DynamicArray<T> array;

    /**
     * Creates a new stack.
     */
    public MyStack() {
        this.array = new DynamicArray<>();
    }
    
    /**
     * Adds a new element to the top of the stack.
     * 
     * @param element The element to be added
     */
    public void push(T element) {
        array.add(element);
    }
    
    /**
     * Removes the top element of the stack and returns it.
     * 
     * @return The top element of the stack
     */
    public T pop() {
        T element = this.peek();
        this.array.setSize(this.array.getSize() - 1);
        
        return element;
    }
    
    /**
     * Returns true if the stack is empty, i.e. it has no objects in it.
     * 
     * @return Is the stack currently empty
     */
    public boolean isEmpty() {
        return this.array.getSize() == 0;
    }
    
    /**
     * Returns the element at the top of the stack without removing it.
     * 
     * @return The top element of the stack.
     */
    public T peek() {
        int indexOfLatest = this.array.getSize() - 1;
        T element = this.array.get(indexOfLatest);
        
        return element;
    }
}
