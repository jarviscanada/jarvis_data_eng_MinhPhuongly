package ca.jrvs.practice.codingChallenge;

import java.util.HashMap;

/**
 * ticket: https://www.notion.so/jarvisdev/Valid-Anagram-d011e89cc07844ea9642817b4b64a5a4
 */
public class ValidAnagram {
    /**
     * Big-O: O(n)
     * Justification: O(n) for time complexity since it loop through the two strings
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t){
        HashMap<String, Integer> counterS = new HashMap<>();
        HashMap<String,Integer> counterT = new HashMap<>();
        if (s.length() != t.length()){
            return false;
        }
        for(int i =0; i<s.length();i++ ){
            String tempS = String.valueOf(s.charAt(i));
            String tempT = String.valueOf(t.charAt(i));
            if(!counterS.containsKey(tempS)){
                counterS.put(tempS,1);
            }
            else{
                counterS.put(tempS,counterS.get(tempS)+1);
            }
            if (!counterT.containsKey(tempT)){
                counterT.put(tempT,1);
            }
            else{
                counterT.put(tempT,counterT.get(tempT)+1);
            }
        }
        if (counterS.equals(counterT)){
            return true;
        }
        return false;
    }
}
