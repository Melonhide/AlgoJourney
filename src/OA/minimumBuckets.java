package OA;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class minimumBuckets {
//    Given an array of n integers, arr, distribute its elements into the minimum possible buckets. Buckets can hold any number of elements, but a bucket of x elements must have more than floor(x/2) elements of the same value. Determine the minimum number of buckets required.
//
//            Function Description
//
//    Complete the function minimumBuckets in the editor.
//
//    minimumBuckets has the following parameters:
//
//    int arr[n]: the array
//    Returns
//
//    int: the minimum number of buckets required

//    Input:  arr = [1, 2, 2, 3, 4]
//    Output: 3
//    1 ≤ n ≤ 105
//    1 ≤ arr[i] ≤ n

    public static int maxsize = 100001;
    public static int[] cntssort = new int[maxsize];
    public static int minimumBuckets(int[] arr) {
        HashMap<Integer, Integer> cnts = new HashMap<>();
        for (int num : arr) {
            cnts.put(num, cnts.getOrDefault(num, 0)+1);
        }
        int i = 0;
        int n = cnts.size();
        if(n == arr.length){
            return n;
        }

        for(Map.Entry<Integer, Integer> entry: cnts.entrySet()){
            cntssort[i++]= entry.getValue();
        }
        Arrays.sort(cntssort, 0 , n);

        int totalBuckets = 0;
        for(int l = 0, r = n-1, curarea = 0; l<=r;){
            if(curarea==0){
                totalBuckets++;
                curarea = cntssort[r]*2-1;
                curarea -= cntssort[r--];
            }else{
                while(curarea-cntssort[l]>=0){
                    curarea -= cntssort[l++];
                }
                cntssort[l] -= curarea-0;
            }
        }

        return totalBuckets;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 4};
        System.out.println(minimumBuckets(arr));
    }

}
