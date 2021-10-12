package ca.jrvs.practice.codingChallenge;

import java.util.ArrayList;
import java.util.List;

/**
 * ticket:https://www.notion.so/jarvisdev/String-to-Integer-atoi-dd2f1483bdb847af901839fe248d08ba
 */
public class StringToInteger {
    /**
     * Big-O: O(n)
     * Justification: using java parsing methods
     * @param s
     * @return
     */
    public static int convertToInt(String s){
        String expectedNumberStr = "";
        int sign = 1;

        s = s.trim();
        if (s.length()==1 && (s.charAt(0)=='-' || s.charAt(0)=='+')){
            return 0;
        }

        if(s.charAt(0)=='-'){
            sign = -1;
        }

        for(String item : s.split("[\\s\\.\\-+]")){
            if (!item.isEmpty()){
                expectedNumberStr=item;
                break;
            }
        }

        try{
            return Integer.parseInt(expectedNumberStr)*sign;
        }catch(NumberFormatException e){
            throw new RuntimeException("Unable to parse!", e);
        }
    }

    /**
     * Big-O: O(n)
     * Justification: using modulo operator
     * @param s
     * @return
     */
    public static int convertToIntModulo(String s){
        String expectedNumberStr = "";
        int sign = 1;

        s = s.trim();
        if (s.length()==1 && (s.charAt(0)=='-' || s.charAt(0)=='+')){
            return 0;
        }

        if(s.charAt(0)=='-'){
            sign = -1;
        }

        for(String item : s.split("[\\s\\.\\-+]")){
            if (!item.isEmpty()){
                expectedNumberStr=item;
                break;
            }
        }

        try{
            int result = 0;
            for(int i=0;i<expectedNumberStr.length();i++){
                char c = expectedNumberStr.charAt(i);
                if (Character.isDigit(c)){
                    int ascii_code = (int) expectedNumberStr.charAt(i);
                    result = result*10+(ascii_code%48);
                }
                else{
                    break;
                }
            }
            return result*sign;
        }catch(NumberFormatException e){
            throw new RuntimeException("Unable to parse!", e);
        }
    }
}
