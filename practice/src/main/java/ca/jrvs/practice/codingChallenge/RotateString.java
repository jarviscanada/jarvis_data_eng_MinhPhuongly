package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/jarvisdev/Rotate-String-9be58aa100254d338161fd3b9ad2108e
 */
public class RotateString {
    /**
     * Big-O: O(n^2)
     * Justification: contains() is brute-force search
     * @param s
     * @param goal
     * @return
     */
    public static boolean isRotateString(String s, String goal){
        if (s.length() != goal.length()){
            return false;
        }
        String ss = s+s; //double the s to make a simulation of a full rotated circle of s string
        return ss.contains(goal);
    }
}
