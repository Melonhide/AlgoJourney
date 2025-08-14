package interviewPrepare.uber.phoneScreening;

public class SortArrayBySquares {

    //给定一个 已按从小到大排序 的整数数组 nums，请将其按 平方值从小到大 的顺序重新排列，并返回重排后的原始数值序列（不是平方值）。
    //当两个数平方相等（绝对值相等）时，返回顺序不作要求。
    //要求时间复杂度 优于 O(N log N)（即 O(N)）。
    //
    //等价表述：按元素的绝对值从小到大重排。

    //Input:  nums = [-3, -2, 0, 1, 2, 5]
    //Output: [0, 1, -2, 2, -3, 5]
    //Explanation: 绝对值从小到大为 0, 1, 2, 2, 3, 5。相同绝对值的相对顺序不限。

    public static int[] sortBySquares(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int size = 0;

        int l = 0;
        int r = n-1;
        int find = -1;
        while(l<=r){
            int m = (l+r)/2;
            if(nums[m]<0){
                l = m+1;
            }else{
                r = m-1;
                find = m;
            }
        }
        if(find == 0){
            return nums;
        }else if(find == -1){
            for(int i = n-1; i >= 0; i--){
                res[size++] = nums[i];
            }
            return res;
        }
        int i = find;
        int j = find-1;
        while(i < n && j >= 0){
            if(Math.abs(nums[i])<=Math.abs(nums[j])){
                res[size++] = nums[i++];
            }else{
                res[size++] = nums[j--];
            }
        }

        while(i<n){
            res[size++] = nums[i++];
        }

        while(j>=0){
            res[size++] = nums[j--];
        }

        return res;
    }

    //Follow Up
    //给定一个 已按从小到大排序 的整数数组 nums 和一个正整数 k（1 <= k <= nums.length）。
    //如果将 nums 按 平方值从小到大 排序（相当于按绝对值从小到大），请返回其中的第 k 小的元素（返回原始数值本身）。
    //当两个数平方相等（绝对值相等）时，任意返回其中一个即可（不要求稳定/特定规则）。
    //不得 先整体重排后再取值；目标时间复杂度 O(log N)（或 O(log(min(n_neg, n_pos)))）。
    //
    //等价表述：给两个已排序数组 A、B：
    //A 为负数部分取绝对值并反向后得到的非降序数组；
    //B 为非负数部分（本身非降序）。
    //在 A 和 B 的“按值合并序列”中找第 k 小（按绝对值），并返回对应的原数组值（若来自 A 则为其负号，来自 B 则为非负）。

    //Input:  nums = [-7, -3, -2, 0, 1, 4, 8], k = 4
    //Output: 1
    //Explanation:
    //按绝对值排序后的序列（原值）可能为：
    //[0, 1, -2, -3, 4, -7, 8]（平手时顺序不唯一）
    //第 4 个为 -3 或 1 均可；本输出示例返回 1。

//    public static int kthBySquares(int[] nums, int k) {
//
//    }

    public static void main(String[] args){
        //int[] nums = new int[] {-3, -2, 0, 1, 2, 5};
        //int[] nums = new int[] {-4, -3, -2, 0};
        int[] nums = new int[] {0, 1, 2, 5};

        int[] res = sortBySquares(nums);
        for(int num: res){
            System.out.print(num + " ");
        }
    }
}
