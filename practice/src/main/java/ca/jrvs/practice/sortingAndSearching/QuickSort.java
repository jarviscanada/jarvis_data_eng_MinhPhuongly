package ca.jrvs.practice.sortingAndSearching;

public class QuickSort {
    public void quickSort(int arr[], int begin, int end){
        if (begin<end){
            int partitionIndex = partition(arr,begin,end);
            quickSort(arr,begin,partitionIndex-1);
            quickSort(arr,partitionIndex+1,end);
        }
    }

    private int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int pivotIndex = begin-1;
        for (int j=begin;j<end;j++){
            if (arr[j] <= pivot){
                pivotIndex++;

                int temp = arr[pivotIndex];
                arr[pivotIndex] = arr[j];
                arr[j] = temp;
            }
        }
        pivotIndex++;
        int temp = arr[pivotIndex];
        arr[pivotIndex] = arr[end];
        arr[end] = temp;

        return pivotIndex;
    }
}
