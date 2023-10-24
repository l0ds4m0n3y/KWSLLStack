import java.util.Iterator;
import java.util.NoSuchElementException;

public class SLLStack<E> implements PureStack, Iterable{
    private Node<E> topOfStack;

    private static class Node<E> {
        /** The data value. */
        private E data;
        /** The link */
        private Node<E> next = null;

        /**
         * Construct a node with the given data value and link
         * @param data - The data value 
         * @param next - The link
         */
        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }

        /**
         * Construct a node with the given data value
         * @param data - The data value 
         */
        public Node(E data) {
            this(data, null);
        }
    }
    
    @Override
    public boolean empty() {
        return topOfStack == null;
    }
    
    @Override
    public void push(Object element) {
        topOfStack = new Node<E>((E)element, topOfStack);
    }
    
    @Override
    public Object pop() {
        Node<E> temp = topOfStack;
        topOfStack = topOfStack.next;
        return temp.data;
    }
    
    @Override
    public Object peek() {
        return topOfStack.data;
    }

    @Override
    public Iterator<E> iterator() {
        return new StackIter();
    }

    private class StackIter implements Iterator{
        Node<E> ptr = topOfStack;

        @Override
        public boolean hasNext() {
            return ptr != null;
        }

        @Override
        public E next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            Node<E> temp = ptr;
            ptr = ptr.next;
            return temp.data;
        }
        
    }

    @Override
    public String toString(){
        StackIter iter = (SLLStack<E>.StackIter) this.iterator();
        String output = "[";
        while(iter.hasNext()){
            if(iter.ptr.next == null)
                output += iter.next();
            else
                output += iter.next() + ", ";
        }
        return output + "]";

    }

}
