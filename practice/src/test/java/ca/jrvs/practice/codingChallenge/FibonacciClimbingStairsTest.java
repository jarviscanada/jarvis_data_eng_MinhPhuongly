package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FibonacciClimbingStairsTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void fib() {
        assertNotEquals(5, FibonacciClimbingStairs.fib(4));
        assertEquals(5, FibonacciClimbingStairs.fib(5));
    }

    @Test
    public void fibDP() {
        assertNotEquals(5, FibonacciClimbingStairs.fibDP(4));
        assertEquals(5, FibonacciClimbingStairs.fibDP(5));
    }

    @Test
    public void stairsClimbing(){
        assertNotEquals(5, FibonacciClimbingStairs.stairsClimbing(4));
        assertEquals(5, FibonacciClimbingStairs.stairsClimbing(5));
    }
}