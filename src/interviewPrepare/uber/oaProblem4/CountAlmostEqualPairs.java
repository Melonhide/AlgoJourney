package interviewPrepare.uber.oaProblem4;

import java.util.*;

public class CountAlmostEqualPairs {

    //You are given a list of integers nums, where each integer represents a distinct digit string (e.g., 123, 321).
    // Two numbers a and b are considered almost equal if:
    //
    //  Their digit strings have the same length.
    //
    //  You can make them exactly equal by swapping at most two digits in one of them (i.e., perform at most one swap on a or on b).
    //
    // Return the number of pairs (i, j) such that 0 ≤ i < j < nums.length and nums[i] and nums[j] are almost equal.
    //Input: nums = [123, 321, 231]
    //Output: 2
    //
    //Explanation:
    //- (123, 321): swap '1' and '3' in 123 → 321 ✅
    //- (123, 231): cannot be equal via one swap ❌
    //- (321, 231): swap '2' and '3' in 321 → 213 ✅
    //So, total = 2 pairs

    public static int countAlmostEqualPairs(List<Integer> nums){
        int res = 0;
        int n = nums.size();
        for(int i = 0; i < n; i++){
            String cur = String.valueOf(nums.get(i));
            for(int j = i+1; j < n; j++){
               String p = String.valueOf(nums.get(j));
               if(cur.length() == p.length()) {
                   char diffc = 'a';
                   char diffp = 'a';
                   boolean use = false;
                   boolean pair = true;
                   for(int k = 0; k < cur.length(); k++){
                       if(p.charAt(k) != cur.charAt(k)){
                           if(use){
                               pair = false;
                               break;
                           }
                           if(diffc == 'a'){
                               diffc = cur.charAt(k);
                               diffp = p.charAt(k);
                           }else{
                               if(diffc != p.charAt(k) || diffp != cur.charAt(k)){
                                   pair = false;
                                   break;
                               }else{
                                   diffc = 'a';
                                   diffp = 'a';
                                   use = true;
                               }
                           }
                       }
                   }

                   res += pair && diffc==diffp? 1:0;
               }
            }
        }

        return res;
    }

    public static int countAlmostEqualPairs2(List<Integer> nums){
        int res = 0;
        int n = nums.size();
        Set<Integer> set = new HashSet<>();
        for(Integer num:nums){
            set.add(num);
        }

        for(Integer num:nums){
            char[] cur = String.valueOf(num).toCharArray();
            for(int i = 0; i < cur.length; i++){
                for(int j = i+1, find; j < cur.length; j++){
                    if((cur[j] == '0' && i == 0)||(cur[i] == cur[j])){
                        continue;
                    }

                    char tmp = cur[i];
                    cur[i] = cur[j];
                    cur[j] = tmp;
                    find = Integer.valueOf(String.valueOf(cur));
                    if(set.contains(find)){
                        res++;
                    }

                    tmp = cur[i];
                    cur[i] = cur[j];
                    cur[j] = tmp;
                }
            }

            set.remove(num);
        }

        return res;
    }


    public static void main(String[] args) {
        int testCount = 1000;
        int maxLength = 10;    // 控制数组长度
        int maxValue = 999;    // 控制数字范围

        for (int t = 0; t < testCount; t++) {
            List<Integer> input = generateRandomList(maxLength, maxValue);

            int res1 = countAlmostEqualPairs(input);
            int res2 = countAlmostEqualPairs2(input);

            if (res1 != res2) {
                System.out.println("test: "+t);
                System.out.println("❌ Mismatch found!");
                System.out.println("Input: " + input);
                System.out.println("BruteForce: " + res1);
                System.out.println("Optimized: " + res2);
                return;
            }
        }

        System.out.println("✅ All tests passed!");
    }

    public static List<Integer> generateRandomList(int maxLen, int maxVal) {
        Random rand = new Random();
        int len = rand.nextInt(maxLen) + 1; // 至少一个元素

        // 用 Set 去重
        Set<Integer> seen = new HashSet<>();
        while (seen.size() < len) {
            int val = rand.nextInt(maxVal + 1);
            seen.add(val);
        }

        return new ArrayList<>(seen);
    }
}
