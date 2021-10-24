package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RotateStringTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void isRotateString() {
        assertFalse(RotateString.isRotateString("abcd","cdba"));
        assertTrue(RotateString.isRotateString("abcd","cdab"));
    }
}