/*
 * Name: David Luna
 * Lab: 6
 * CS 270
 */

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SLLStack<E> implements PureStack<E>, Iterable<E>{
    private Node<E> topOfStack;
    private int modifications = 0;

    private static class Node<E> {
        private E data;
        private Node<E> next = null;

        /**
         * Constructs a node with the given data value and link
         * @param data - The data value 
         * @param next - The link
         */
        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }
    
    /**
	 *  Determines if the stack has no elements.
	 *
	 *  @return true - if the stack has no elements; otherwise,
	 *                          return false.
	 *
	 */
    @Override
    public boolean empty() {
        return topOfStack == null;
    }

    /**
	 *  Inserts a specified element on the top of the stack.
	 *  The averageTime(n) is constant and worstTime(n) is O(n).
	 *  @param element - the element to be pushed.
	 */
    @Override
    public void push(E element) {
        topOfStack = new Node<E>(element, topOfStack);
        modifications++;
    }

    /**
	 *  Removes the top element from the stack.
	 *  @return - the element removed.
	 *  @throws NoSuchElementException - if this PureStack object is empty.
	 */ 
    @Override
    public E pop() {
        if(this.empty()) throw new NoSuchElementException();
        Node<E> temp = topOfStack;
        topOfStack = topOfStack.next;
        modifications++;
        return temp.data;
    }

    /**
	 *  Returns the top element on the stack.
	 *  @return - the element returned.
	 *  @throws NoSuchElementException - if this PureStack object is empty.
	 */	
    @Override
    public E peek() {
        if(this.empty()) throw new NoSuchElementException();
        return topOfStack.data;
    }

    /**
     * Returns a special iterator for the stack.
     * @return Iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new StackIter();
    }

    private class StackIter implements Iterator<E>{
        Node<E> ptr = topOfStack;
        private int expectedModifications = modifications;
        
        /**
         * @return true - if an element exists in front of the ptr of the iterator
         * else false if null 
         */
        @Override
        public boolean hasNext() {
            return ptr != null;
        }
        /**
         * moves the iterator forward and 
         * @return the item that the iterator walked over
         */
        @Override
        public E next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            if(expectedModifications != modifications) throw new ConcurrentModificationException();
            Node<E> temp = ptr;
            ptr = ptr.next;
            return temp.data;
        }
        
    }

    /**
     * @return String containing all elements in the stack as a list
     */
    @Override
    public String toString(){
        StringBuilder output = new StringBuilder("[");
        StackIter iter = (SLLStack<E>.StackIter) this.iterator();
        while (iter.hasNext()) {
            output.append(iter.next() + ", ");
        }
        if(!this.empty())
            output.delete(output.length() - 2, output.length());
        output.append("]");
        return output.toString();
    }
}
