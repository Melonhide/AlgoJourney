package interviewPrepare;

public class MaximumSwapVariant_670 {
    // Build Second Largest
    // Given an integer array num. Rearrange the digits in such a way to build the second largest number
    // 0<=nums[i]<=9


    public static int[] BuildSecondLargest(int[] nums){
        int[] freq = new int[10];
        for(int num: nums){
            freq[num]++;
        }
        for(int i = 9, j = 0; i >= 0;){
            if(freq[i]>0){
                nums[j++] = i;
                freq[i]--;
            }else{
                i--;
            }
        }

        for(int i = nums.length-2, smallest = nums.length-1; i>=0; i--){
            if(nums[i] == nums[smallest]){
                smallest = i;
            }else{
                int tmp = nums[i];
                nums[i] = nums[smallest];
                nums[smallest] = tmp;
                break;
            }
        }

        return nums;
    }


    public static void main(String[] args){
        int[] nums1 = new int[]{2,7,3,6};
        nums1 = BuildSecondLargest(nums1);
        for(int num: nums1){
            System.out.print(num + " ");
        }
        System.out.println();
        nums1 = new int[]{1,1,2,1,1,1,1};
        nums1 = BuildSecondLargest(nums1);
        for(int num: nums1){
            System.out.print(num + " ");
        }

    }
}
