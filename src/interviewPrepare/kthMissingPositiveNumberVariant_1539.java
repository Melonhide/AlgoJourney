package interviewPrepare;

import java.util.*;

public class kthMissingPositiveNumberVariant_1539 {
    // Given a list of holidays (nums, sorted in ascending order and all unique),
    // and an integer k representing the number of working days required to finish a project,
    // return the earliest day the project can be completed, assuming work can only happen on non-holiday days.
    //
    // Example 1:
    // Input: nums = [4, 7, 9, 10], k = 1
    // Output: 5  (first available working day after skipping 4)
    //
    // Example 2:
    // Input: nums = [4, 7, 9, 10], k = 3
    // Output: 8  (missing = [5, 6, 8], third = 8)
    //
    // Example 3:
    // Input: nums = [1, 2, 4], k = 3
    // Output: 6  (missing = [3, 5, 6], third = 6)
    public static int findKthPositiveVariant(int[] nums, int k) {
        int l = 0;
        int r = nums.length-1;
        int find = 0;
        while(l<=r){
            int m = (l+r)/2;
            int miss = nums[m]-nums[0]+1 - (m+1);
            if(miss<k){
                find = m;
                l = m+1;
            }else{
                r = m-1;
            }
        }
        return nums[find] + k-(nums[find]-nums[0]+1 - (find+1));
    }

    public static int bruteforce(int[] nums, int k){
        Set<Integer> set = new HashSet<>();
        for(int num:nums){
            set.add(num);
        }
        int i = nums[0];
        while(k>0){
            i++;
            if(!set.contains(i)){
                k--;
            }
        }
        return i;
    }

    public static int[] generateSortedUniqueArray(int minVal, int maxVal, int size) {
        Set<Integer> set = new TreeSet<>();
        Random rand = new Random();
        while (set.size() < size) {
            set.add(rand.nextInt(maxVal - minVal + 1) + minVal);
        }
        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void runTests(int trials) {
        Random rand = new Random();
        for (int t = 1; t <= trials; t++) {
            //System.out.println("Test# "+t);
            int size = rand.nextInt(50) + 1; // up to 50 elements
            int[] nums = generateSortedUniqueArray(1, 200, size);
            Arrays.sort(nums);
            int k = rand.nextInt(100) + 1; // 1 <= k <= 100

            int expected = bruteforce(nums, k);
            int actual = findKthPositiveVariant(nums, k);

            if (expected != actual) {
                System.out.println("❌ Test failed!");
                System.out.println("nums = " + Arrays.toString(nums));
                System.out.println("k = " + k);
                System.out.println("Expected: " + expected);
                System.out.println("Actual:   " + actual);
                return;
            }
        }
        System.out.println("✅ All " + trials + " tests passed.");
    }

    public static void main(String[] args) {
        runTests(10000);
        //int[] nums = new int[] {7, 61, 71, 96, 122, 130, 131, 140, 164, 179};
        int[] nums = new int[] {4, 7, 9, 10};
        System.out.println(findKthPositiveVariant(nums, 3));
        System.out.println(bruteforce(nums, 3));
    }
}
