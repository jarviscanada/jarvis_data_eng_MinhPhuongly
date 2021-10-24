package ca.jrvs.practice.sortingAndSearching;

import java.util.Optional;

public class binarySearch {
    /**
     * find the the target index in a sorted array
     *
     * @param arr input arry is sorted
     * @param target value to be searched
     * @return target index or Optional.empty() if not ound
     */
    public static <E extends Comparable<E>> Optional<Integer> binarySearchRecursion(E[] arr, E target,int start, int end) {
        if (start> end){
            return Optional.empty();
        }
        int mid = (start+end)/2;
        if (target.compareTo(arr[mid])==0){
            return Optional.of(mid);
        }
        else if (target.compareTo(arr[mid])<0){
            return binarySearchRecursion(arr,target,start,mid-1);
        }
        else{
            return binarySearchRecursion(arr,target,mid+1,end);
        }
    }

    /**
     * find the the target index in a sorted array
     *
     * @param arr input arry is sorted
     * @param target value to be searched
     * @return target index or Optional.empty() if not ound
     */
    public static <E extends Comparable<E>>  Optional<Integer> binarySearchIteration(E[] arr, E target) {
        int start = 0;
        int end = arr.length-1;
        while(start<=end){
            int mid = (start+end)/2;
            if (target.compareTo(arr[mid])==0){
                return Optional.of(mid);
            }
            else if (target.compareTo(arr[mid]) <0){ //if target < arr[mid]
                end = mid-1;
            }
            else{
                start = mid+1;
            }
        }
        return Optional.empty();
    }
}
