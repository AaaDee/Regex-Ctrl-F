
package regex;

import java.util.Deque;

/* 
A place holder for a custom stack implementation, currently using the standard
implementation as a placeholder
*/


/// This will be removed later
import java.util.ArrayDeque;

import java.util.Collection;
import java.util.Iterator;


public class StackImplementation implements Deque {
    private Deque stack;

    public StackImplementation() {
        this.stack = new ArrayDeque();
    }
    
    
     @Override
    public void push(Object e) {
        stack.push(e); 
    }

    @Override
    public Object pop() {
        return stack.pop(); 
    }
    
     @Override
    public boolean isEmpty() {
        return stack.isEmpty(); 
    }
    
    @Override
    public Object peek() {
        return stack.peek();
    }
    
    @Override
    public int size() {
        return stack.size(); 
    }
    
    /// The following methods won't be necessary for the implementation, hence won't be implemented
   
    @Override
    public void addFirst(Object e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void addLast(Object e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean offerFirst(Object e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean offerLast(Object e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Object removeFirst() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Object removeLast() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Object pollFirst() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Object pollLast() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getFirst() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Object getLast() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Object peekFirst() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Object peekLast() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean add(Object e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean offer(Object e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Object remove() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Object poll() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Object element() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }


    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Iterator descendingIterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Object[] toArray(Object[] a) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean containsAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean removeAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean retainAll(Collection c) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    
    
}
