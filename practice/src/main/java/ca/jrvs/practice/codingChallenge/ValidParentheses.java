package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/**
 * ticket: https://www.notion.so/jarvisdev/Valid-Parentheses-0a91b7beba9142f9b51cc3057ef3a631
 */
public class ValidParentheses {
    /**
     * Big-O: O(n)
     * Justification: loop through n character of the string.
     * @param s
     * @return
     */
    public static boolean isValid(String s){
        String[] arr = s.split("");
        HashMap<String,String> pairs = new HashMap<>();
        pairs.put("{","}");
        pairs.put("[","]");
        pairs.put("(",")");
        Stack<String> checker = new Stack<>();
        for (String i:arr){
            if(pairs.containsKey(i)){
                checker.add(i);
            }
            else{ //i is a closing backet
                if (checker.isEmpty()){
                    return false; //no opening bracket yet => false
                }
                String opening_bracket = checker.pop();
                //return false if i not equal closing bracket of the current opening bracket
                if (!pairs.get(opening_bracket).equals(i)){
                    return false;
                }
            }
        }
        if (checker.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
}
