package interviewPrepare;

import java.util.*;

public class diagnoalTraverseIIVariant_1424 {
    // Variant 1: Anti diagonal Traverse
    public static int[] findAntiDiagonalOrder(List<List<Integer>> nums) {
        List<Integer> res = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int k = 0, i, j; k < size; k++){
                int[] cur = queue.poll();
                i = cur[0];
                j = cur[1];
                res.add(nums.get(i).get(j));
                if(j+1<nums.get(i).size()){
                    queue.add(new int[]{i, j+1});
                }

                if(j == 0 && i+1<nums.size()){
                    queue.add(new int[]{i+1, j});
                }
            }
        }
        int[] ans = new int[res.size()];
        for(int i = 0; i < res.size(); i++){
            ans[i] = res.get(i);
        }

        return ans;
    }

    // Variant 2: Print Anti diagonal
    public static void printAntiDiagonalOrder(List<List<Integer>> nums) {
        int[] ans = findAntiDiagonalOrder(nums);
        for(int num:ans){
            System.out.print(num + " ");
        }
    }

    public static void main(String[] args){
        List<List<Integer>> nums = new ArrayList<>();
        nums.add(Arrays.asList(1,2,3,4,5));
        nums.add(Arrays.asList(6,7));
        nums.add(Arrays.asList(8));
        nums.add(Arrays.asList(9,10,11));
        nums.add(Arrays.asList(12,13,14,15,16));

        printAntiDiagonalOrder(nums);
    }
}
