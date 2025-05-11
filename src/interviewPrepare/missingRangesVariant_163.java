package interviewPrepare;

import java.util.ArrayList;
import java.util.List;

public class missingRangesVariant_163 {

    // Given inclusive range[lower, upper] and a sorted unique integer array nums.
    // Return the shortest sorted list of ranges that exactly covers all the missing numbers as a list of strings
    // Rules:
    // 1. One missing number, push it as an individual string
    // 2. two consecutive missing numbers, push them individually
    // 3. More than two, push as a range.
    // Example:
    // Input:[5,8,9,15,16,18,20], lower = 2, upper = 87
    // Output: ["2-4", "6", "7", "10-14", "17", "19", "21-87"]

    public static List<String> missingRanges(int[] nums, int lower, int upper){
        List<String> ans = new ArrayList<>();
        if(lower == nums[0]-1){
            ans.add(String.valueOf(lower));
        }else if(lower == nums[0]-2){
            ans.add(String.valueOf(lower));
            ans.add(String.valueOf(lower+1));
        }else if(lower< nums[0]){
            ans.add(lower + "-" + (nums[0]-1));
        }

        int start = nums[0]+1;
        for(int i = 1, end; i < nums.length; i++){
            if(nums[i] == start){
                start = nums[i]+1;
            }else{
                end = nums[i]-1;
                if(start == end){
                    ans.add(String.valueOf(start));
                }else if(start == end-1){
                    ans.add(String.valueOf(start));
                    ans.add(String.valueOf(end));
                } else{
                    ans.add(start + "-" + end);
                }
                start = nums[i]+1;
            }
        }
        if(start == upper){
            ans.add(String.valueOf(start));
        }else if(start == upper-1){
            ans.add(String.valueOf(start));
            ans.add(String.valueOf(upper));
        }else if(start<upper){
            ans.add(start + "-" + upper);
        }

        return ans;
    }

    public static void main(String[] args){
        //int[] nums = new int[]{5,8,9,15,16,18,20};
        int[] nums = new int[]{10,12,15,16,30,31,35};
        List<String> res = missingRanges(nums, 3, 34);
        for(String r:res){
            System.out.print(r + " ");
        }
    }
}
