package util;


public class MyStack<T> {
    private DynamicArray<T> array;

    public MyStack() {
        this.array = new DynamicArray<T>();
    }
    
    public void push(T element){
        array.add(element);
    }
    
    public T pop(){
        T element = this.peek();
        this.array.setSize(this.array.getSize() - 1);
        
        return element;
    }
    
    public boolean isEmpty(){
        return this.array.getSize() == 0;
    }
    
    public T peek(){
        int indexOfLatest = this.array.getSize() - 1;
        T element = this.array.get(indexOfLatest);
        
        return element;
    }
}
