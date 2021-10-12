package ca.jrvs.practice.codingChallenge;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EvenOddNumberTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void moduloChecker() {
        assertEquals("odd",EvenOddNumber.moduloChecker(3));
    }

    @Test
    public void bitwaseChecker() {
        assertEquals("even",EvenOddNumber.bitwaseChecker(4));
    }
}