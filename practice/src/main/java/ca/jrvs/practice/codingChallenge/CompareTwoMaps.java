package ca.jrvs.practice.codingChallenge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Ticket: https://www.notion.so/jarvisdev/How-to-compare-two-maps-991ffc01190f4e608a3bf5776b5c3692
 */
public class CompareTwoMaps {

    /**
     * Big-O: O(n)
     * Justification: it has to iterate to check k-v pairs so that it's linear
     * @param m1
     * @param m2
     * @return
     */
    public static  boolean usingJavaAPI(Map m1, Map m2){
        return m1.equals(m2);
    }
}
