
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

//run one by one 
public class StackTesting {
    static SLLStack<String> myStack = new SLLStack<>();

    @BeforeAll
    public static void setUp(){
        myStack.push("Zerath");
        myStack.push("Won");
        myStack.push("Sue");
        myStack.push("Bree");
    }

    @Test
    void testEmpty(){
        while(!myStack.empty()){
            myStack.pop();
        }
        assertTrue(myStack.empty());
    }

    @Test
    void isItReallyEmpty(){
        while(!myStack.empty()){
            myStack.pop();
        }
        assertThrows(NoSuchElementException.class, () -> {
            myStack.pop();
        });
        assertThrows(NoSuchElementException.class, () -> {
            myStack.peek();
        });

        assertEquals("[]", myStack.toString());
    }

    @Test
    void popLastPushed(){
        myStack.push("Thor");
        assertEquals("Thor", myStack.peek());
        assertEquals("Thor", myStack.pop());
        assertEquals("Bree", myStack.pop());
    }

    @Test
    void IteratorTest(){
        Iterator<String> iter = myStack.iterator();
        assertEquals("Bree", iter.next());
        assertEquals("Sue", iter.next());
        assertEquals("Won", iter.next());
        assertEquals("Zerath", iter.next());
        assertThrows(NoSuchElementException.class, () -> {
            iter.next();
        });
    }

    @Test
    void ConcurrentModificationExceptionTest(){
        Iterator<String> iter = myStack.iterator();
        myStack.push("EvilMilk");
        assertThrows(ConcurrentModificationException.class, () -> {
            iter.next();
        });
    }
}
