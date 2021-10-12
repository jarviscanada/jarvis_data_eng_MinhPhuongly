package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TwoSumTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void normalSolution() {
        int[] input = {3,2,4};
        int[] result = TwoSum.normalSolution(input,6);
        int[] expect = new int[]{1,2};
        Arrays.sort(result);
        assertArrayEquals(expect, result);
    }

    @Test
    public void sortSolution() {
        int[] input = {3,2,4};
        int[] result = TwoSum.sortSolution(input,6);
        int[] expect = new int[]{1,2};
        Arrays.sort(result);
        assertArrayEquals(expect, result);
    }

    @Test
    public void hashMapSolution() {
        int[] input = {3,2,4};
        int[] result = TwoSum.hashMapSolution(input,6);
        int[] expect = new int[]{1,2};
        Arrays.sort(result);
        assertArrayEquals(expect, result);
    }
}