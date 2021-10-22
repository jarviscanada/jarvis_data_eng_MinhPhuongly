package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidPalindromeTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void isValid() {
        assertTrue(ValidPalindrome.isValid("ab_a"));
    }
}