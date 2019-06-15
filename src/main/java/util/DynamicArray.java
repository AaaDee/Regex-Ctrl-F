package util;

/**
 * A dynamic array, which automatically scales up when full
 * 
 * @author AD
 * @param <T> The class of the objects stored in the array
 */
public class DynamicArray<T> {
    private int currentSize;
    private int maxSize;
    private T[] array;
    private static final int DEFAULT_SIZE = 16;

    /**
     * Creates a new empty DynamicArray
     * 
     */
    public DynamicArray() {
        this.currentSize = 0;
        this.maxSize = DEFAULT_SIZE;
        array = (T[]) new Object[maxSize];
    }
    
    /**
     * Adds an object to the end of the list, while checking that the maximum size
     * is not exceeded 
     * 
     * @param element The element to be added
     */
    public void add(T element){
        if (currentSize == maxSize){
            this.doubleSize();
        }
        this.array[currentSize] = element;
        this.currentSize++;
        
    }

    private void doubleSize() {
        T[] biggerArray = (T[]) new Object[this.maxSize * 2];
        
        for (int i = 0; i < this.maxSize; i++){
            biggerArray[i] = this.array[i];
        }
        
        this.array = biggerArray;
        this.maxSize = this.maxSize * 2;
    }
    
    /**
     * 
     * 
     * @param index Index of the object to be returned
     * @return The object from a given index
     */
    public T get(int index){
        return this.array[index];
    }
    
    /**
     * 
     * 
     * @return the current size i.e. the number of objects in the array.
     */
    public int getSize(){
        return this.currentSize;
    }
    
    /**
     * Adds another DynamicArray to the end of the current list element by element
     * 
     * @param list Another list to be added
     */
    public void addAll(DynamicArray<T> list){
        for (int i = 0; i < list.getSize(); i++){
            T element = list.get(i);
            this.add(element);
        }
    }
    
    /**
     * Sets the current size of the array to a given integer
     * 
     * @param newSize The new current size of the array.
     */
    public void setSize(int newSize){
        this.currentSize = newSize;
    }
    
}
