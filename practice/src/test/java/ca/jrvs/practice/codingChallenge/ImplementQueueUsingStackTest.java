package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImplementQueueUsingStackTest {
    ImplementQueueUsingStack<Integer> myQueue;
    @Before
    public void setUp() throws Exception {
        myQueue = new ImplementQueueUsingStack();
    }

    @Test
    public void push() {
        Integer checker = 2;
        myQueue.push(2);
        myQueue.push(3);
        assertEquals(checker,myQueue.peek());
    }

    @Test
    public void pop() {
        Integer checker = 2;
        myQueue.push(2);
        myQueue.push(3);
        assertEquals(checker,myQueue.pop());
    }

    @Test
    public void peek() {
        Integer checker = 2;
        myQueue.push(2);
        myQueue.pop();
        assertEquals(null,myQueue.peek());
        myQueue.push(2);
        assertEquals(checker,myQueue.peek());
    }

    @Test
    public void isEmpty() {
        assertTrue(myQueue.isEmpty());
    }
}