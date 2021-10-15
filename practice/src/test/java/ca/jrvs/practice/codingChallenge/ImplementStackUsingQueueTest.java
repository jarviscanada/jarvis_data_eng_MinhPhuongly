package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImplementStackUsingQueueTest {
    ImplementStackUsingQueue<Integer> stack;

    @Before
    public void setUp() throws Exception {
        stack = new ImplementStackUsingQueue<>();
    }

    @Test
    public void push() {
        stack.push(2);
        assertNotNull(stack);
    }

    @Test
    public void top() {
        Integer expected = 2;
        stack.push(2);
        assertEquals(expected,stack.top());
    }

    @Test
    public void pop() {
        Integer expected2 = 2;
        Integer expected3 = 3;
        stack.push(2);
        stack.push(3);
        assertEquals(expected3,stack.pop());
        assertEquals(expected2,stack.pop());
        assertNull(stack.pop());
    }

    @Test
    public void isEmpty() {
        assertTrue(stack.isEmpty());
    }
}