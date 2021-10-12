package ca.jrvs.practice.codingChallenge;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class CompareTwoMapsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void usingJavaAPI() {
        HashMap<String,Integer> map1 = new HashMap<>();
        HashMap<String,Integer> map2 = new HashMap<>();
        map1.put("a",1);
        assertFalse(CompareTwoMaps.usingJavaAPI(map1,map2));
        map2.put("a",1);
        map1.put("b",1);
        map2.put("b",1);
        assertTrue(CompareTwoMaps.usingJavaAPI(map1,map2));
    }
}