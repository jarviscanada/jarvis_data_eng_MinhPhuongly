package ca.jrvs.practice.sortingAndSearching;

public class MergeSort {

    public static void mergeSort(int[] arr,int n){
        if (n<2)
            return;

        int mid = n/2;
        int[] l=new int[mid];
        int[] r = new int[n-mid];
        //break arr into 2 smaller arrays
        for (int i=0;i<mid;i++){
            l[i] = arr[i];
        }
        for (int i=mid;i<n;i++){
            r[i-mid] = arr[i];
        }
        mergeSort(l,mid);
        mergeSort(r,n-mid);

        //merge them back into arr
        merge(arr,l,r,mid,n-mid);
    }

    private static void merge(int[] arr, int[] l, int[] r, int l_len, int r_len) {
        int i=0;
        int j=0;
        int k=0;
        //put elements from l r to arr
        while (i<l_len && j<r_len){
            if (l[i]<= r[j]){
                arr[k++] = l[i++];
            }
            else{
                arr[k++] = r[j++];
            }
        }
        while (i<l_len){
            arr[k++] = l[i++];
        }
        while (j<r_len){
            arr[k++] = r[j++];
        }
    }
}
