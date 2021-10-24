package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidParenthesesTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void isValid() {
        assertTrue(ValidParentheses.isValid("{}"));
        assertFalse(ValidParentheses.isValid("{(])}"));
    }
}