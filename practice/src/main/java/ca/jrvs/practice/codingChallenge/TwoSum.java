package ca.jrvs.practice.codingChallenge;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

/**
 * ticket: https://www.notion.so/jarvisdev/Two-Sum-978448c91c534c2da4be481b289f676b
 */
public class TwoSum {

    /**
     * Big-O: O(n^2)
     * Justification: 2 nested for loop
     * @param arr
     * @param sum
     * @return
     */
    public static int[] normalSolution(int[] arr, int sum){
        for(int i=0; i<arr.length-1;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]+arr[j] == sum){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }

    /**
     * Big-O: O(nlog(n))
     * Note: Incomplete method. Don't use
     * @param arr
     * @param sum
     * @return
     */
    public static int[] sortSolution(int[] arr, int sum){
        //Warning: Need to have a way to keep track of old indices before sort for return result.
        //Method not ready for use!!
        int[] indexSaver = Arrays.copyOf(arr,arr.length);

        Arrays.sort(arr);
        int left = 0;
        int right = arr.length-1;
        while(left<right){
            if(arr[left]+arr[right]==sum)
                return new int[]{indexSaver[left],indexSaver[right]}; //this line need to be replaced
            if(arr[left]+arr[right] > sum){
                right--;
            }
            else if(arr[left]+arr[right] < sum){
                left++;
            }
        }
        return null;
    }

    /**
     * Big-O: O(n)
     * Justification: Loop through the array once
     * @param arr
     * @param sum
     * @return
     */
    public static int[] hashMapSolution(int[] arr, int sum){
        HashMap<Integer,Integer> checker = new HashMap<>();
        for(int i=0; i< arr.length;i++){
            int temp = sum- arr[i];
            if(checker.containsKey(temp)){
                return new int[]{i,checker.get(temp)};
            }
            else{
                checker.put(arr[i],i);
            }
        }
        return null;
    }
}
