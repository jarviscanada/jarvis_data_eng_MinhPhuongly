package ca.jrvs.practice.codingChallenge;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ticket: https://www.notion.so/jarvisdev/Valid-Palindrome-62e901ded8b04b64abb5cb30bb4d4766
 */
public class ValidPalindrome {
    /**
     * Big-O: O(n/2)
     * Justification: loop through
     * @param s
     * @return
     */
    public static boolean isValid(String s){
        //remove all non-alphanumeric characters \W will exclude _ so need to put it in.
        //convert them all to lower case since requirement is not case-sensitive
        s = s.replaceAll("[\\W_]","").toLowerCase();
        int start = 0;
        int end = s.length()-1;
        while(start<end){
            if (s.charAt(start) != s.charAt(end)){
                return false;
            }
            start ++;
            end--;
        }
        return true;
    }
}
