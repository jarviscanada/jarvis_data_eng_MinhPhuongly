package ca.jrvs.practice.sortingAndSearching;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class binarySearchTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void binarySearchRecursion() {
        Integer[] arr = {1,2,3,4,5};
        assertEquals(Optional.of(4), binarySearch.binarySearchRecursion(arr,5,0,arr.length-1));
    }

    @Test
    public void binarySearchIteration() {
        Integer[] arr = {1,2,3,4,5};
        assertEquals(Optional.of(3),binarySearch.binarySearchIteration(arr,4));
    }
}