package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidAnagramTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void isAnagram() {
        assertTrue(ValidAnagram.isAnagram("abcd","dbca"));
    }
}