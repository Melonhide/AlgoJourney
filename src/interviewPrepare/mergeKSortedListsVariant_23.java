package interviewPrepare;

import java.util.PriorityQueue;

public class mergeKSortedListsVariant_23 {

    // Variant 1:
    // Merge k sorted Integer arrays;

    public static int[] mergeKSortedArrays(int[][] nums){
        int size = 0;
        PriorityQueue<int[]> heap = new PriorityQueue<>((int[] a, int[] b)->(nums[a[0]][a[1]]-nums[b[0]][b[1]]));
        for(int i = 0; i < nums.length; i++){
            size+=nums[i].length;
            heap.add(new int[]{i,0,nums[i].length});
        }
        int[] ans = new int[size];
        int ind = 0;
        while(!heap.isEmpty()){
            int[] cur = heap.poll();
            ans[ind++] = nums[cur[0]][cur[1]];
            if(cur[1]+1<cur[2]){
                heap.add(new int[]{cur[0],cur[1]+1, cur[2]});
            }
        }

        return ans;
    }

    //Variant 2:
    // Design an Iterator over k sorted integer arrays (lists).
    // Each integer array is sorted in ascending order.
    // The Iterator should support the following methods:
    //
    // - Iterator(List<List<Integer>> lists): Initializes the iterator with k sorted arrays.
    // - boolean hasNext(): Returns true if there is at least one integer left to return.
    // - int next(): Returns the next smallest integer among all arrays. Throws an error if no integers remain.
    //
    // Example 1:
    // Input: lists = [[1, 5], [1], [2, 6]]
    // Output sequence (calling next() and hasNext()):
    // -> 1, 1, 2, 5, 6
    //
    // Example 2:
    // Input: lists = [[1], [2], [3]]
    // Output sequence:
    // -> 1, 2, 3



    public static void main(String[] args){
        int[][] nums = {
                {1,4,5},
                {1,3,4},
                {2,6}
        };
        int[] ans = mergeKSortedArrays(nums);
        for(int num : ans){
            System.out.print(num + " ");
        }

    }
}
