package interviewPrepare.uber.vo;

public class NStableArray {
//    Given an array of integers, and an integer N, find the length of the longest “N-stable” continuous subarray.
//    An array is defined to be “N-stable” when the pair-wise difference between any two elements in the array is smaller or equal to N.
//
//    A subarray of a 0-indexed integer array is a contiguous non-empty sequence of elements within an array.
//    For instance, for array [ 4, 2, 3, 6, 2, 2, 3, 2, 7 , 4], and N = 2
//    The return value should be 4, since the longest 1-stable subarray is [ 2, 2, 3, 2 ]

    public static int getNStableArray(int[] nums, int n){
        int len = nums.length;
        int[] maxQueue = new int[len];
        int[] minQueue = new int[len];
        int maxl = 0;
        int maxr = 0;
        int minl = 0;
        int minr = 0;

        int resl = 0;
        int resr = 0;
        int res = 0;
        for(int l = 0, r = 0, curmin, curmax; r < len; r++){
            while(maxr>maxl && (nums[maxQueue[maxr-1]]<=nums[r])){
                maxr--;
            }
            maxQueue[maxr++] = r;

            while(minr>minl && (nums[minQueue[minr-1]] >= nums[r])){
                minr--;
            }
            minQueue[minr++] = r;

            curmin = minQueue[minl];
            curmax = maxQueue[maxl];
            while(nums[curmax]-nums[curmin] > n){
                if(curmin == l){
                    minl++;
                }

                if(curmax == l){
                    maxl++;
                }

                l++;
                curmin = minQueue[minl];
                curmax = maxQueue[maxl];
            }
            res = Math.max(r-l+1, res);
        }

        return res;
    }

    public static void main(String[] args){
        int[] nums = new int[]{4, 2, 3, 6, 2, 2, 3, 2, 7 , 4};
        System.out.println(getNStableArray(nums, 2));
    }
}
