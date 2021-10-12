package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.Test;

public class StringToIntegerTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void convertToInt() {
        System.out.println(StringToInteger.convertToInt("   -55.5"));
    }

    @Test
    public void convertToIntModulo(){
        System.out.println(StringToInteger.convertToIntModulo("  -55.6"));
    }
}