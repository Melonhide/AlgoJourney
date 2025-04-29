package interviewPrepare;

import java.util.*;

public class randomPickIndexVariants_398 {
    // Given an integer array nums, which may contain duplicates, randomly pick k unique numbers and return them as a new array.
    //
    // 1. You cannot pick the same number twice.
    //
    // 2. You cannot use extra space (e.g., no additional hash maps or arrays).
    //
    // 3. Your algorithm must run in O(n) time complexity.
    //
    //Examples:
    //
    //Input: nums = [6,8,2,1,3,10,4], k = 3
    //Possible output: [6,3,4] (Other valid outputs include [8,2,10], [1,10,4], etc.)
    //
    //Input: nums = [1,2], k = 2
    //Possible output: [1,2]


    //[6,8,2,1,3,10,4] k=3
    //[6,8,2] 3/4 Math.random()*4 [0,1,2,3]
    public static int[] randomPickIndexV1(int[] nums, int k){
            int[] ans = new int[k];
            for(int i = 0, len=1, loc; i<nums.length; i++, len++){
                if(((double)k/(len))>=1){
                    ans[i] = nums[i];
                }else{
                    loc = (int)(Math.random()*(len));
                    if(loc<k){
                        ans[loc] = nums[i];
                    }
                }
            }
            return ans;
    }


    // Given an integer array with possible duplicates, randomly return an index
    // of the maximum value in the array. Each index with the max value should have
    // equal probability. Must run in O(n) time and use O(1) extra space.
    //
    // Example:
    // Input:  [11, 11, 2, 30, 6, 30, 30, 2, 62, 62]
    // Output: 8 or 9  (each with 50% chance, since 62 is the max and appears at indices 8 and 9)
    public static int randomPickIndexV2(int[] nums){
        int max = nums[0];
        int cnt = 1;
        int ans = 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[i]>max){
                cnt = 1;
                ans = i;
                max = nums[i];
            }else if(nums[i] == max){
                cnt++;
                if((int)(Math.random()*cnt) == 0){
                    ans = i;
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println("Test case for variant 1");
        testv1();

        System.out.println();
        System.out.println("Test case for variant 2");
        testv2();
    }

    public static void testv1(){
        int[] nums = {1, 2, 3, 4};
        int k = 2;
        // 统计出现的结果
        Map<String, Integer> countMap = new HashMap<>();

        int iterations = 100000;

        for (int i = 0; i < iterations; i++) {
            int[] picked = randomPickIndexV1(nums, k);
            Arrays.sort(picked); // 排序确保一致性
            String key = Arrays.toString(picked);
            countMap.put(key, countMap.getOrDefault(key, 0) + 1);
        }

        // 输出每个组合出现的频率
        List<String> sortedKeys = new ArrayList<>(countMap.keySet());
        Collections.sort(sortedKeys);
        for (String key : sortedKeys) {
            System.out.println(key + " => " + countMap.get(key));
        }
    }

    public static void testv2(){
        int[] nums = {11, 11, 2, 30, 6, 30, 30, 2, 62, 62};
        int trials = 100_000;

        // 统计每个下标被选中的次数
        Map<Integer, Integer> indexCount = new HashMap<>();

        for (int i = 0; i < trials; i++) {
            int index = randomPickIndexV2(nums);
            indexCount.put(index, indexCount.getOrDefault(index, 0) + 1);
        }

        // 找出最大值下标
        int maxVal = Arrays.stream(nums).max().getAsInt();
        List<Integer> maxIndices = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == maxVal) {
                maxIndices.add(i);
            }
        }

        // 打印统计结果
        System.out.println("Max value: " + maxVal);
        System.out.println("Expected indices: " + maxIndices);
        for (int idx : maxIndices) {
            System.out.printf("Index %d selected %d times (%.2f%%)\n",
                    idx,
                    indexCount.getOrDefault(idx, 0),
                    100.0 * indexCount.getOrDefault(idx, 0) / trials);
        }
    }

}
