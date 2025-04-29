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

    public static void main(String[] args) {
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

}
