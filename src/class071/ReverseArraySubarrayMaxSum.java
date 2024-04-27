package class071;


// 可以翻转1次的情况下子数组最大累加和
// 给定一个数组nums，
// 现在允许你随意选择数组连续一段进行翻转，也就是子数组逆序的调整
// 比如翻转[1,2,3,4,5,6]的[2~4]范围，得到的是[1,2,5,4,3,6]
// 返回必须随意翻转1次之后，子数组的最大累加和
// 对数器验证
public class ReverseArraySubarrayMaxSum {
    public static int maxSumReverse1(int[] nums) {
        int ans = Integer.MIN_VALUE;

        for(int l = 0; l<nums.length; l++){
            for(int r=l; r<nums.length; r++){
                reverse(nums, l, r);
                ans = Math.max(ans, maxSum(nums));
                reverse(nums, l, r);
            }
        }
        return ans;

    }


    // nums[l...r]范围上的数字进行逆序调整
    public static void reverse(int[] nums, int l, int r) {
        while(l<r){
            int tmp = nums[l];
            nums[l++] = nums[r];
            nums[r--] = tmp;
        }
    }

    // 返回子数组最大累加和
    public static int maxSum(int[] nums) {
//        int n = nums.length;
//        int ans = Integer.MIN_VALUE;
//        for(int i = 0, pre = 0; i < n; i++){
//            pre = Math.max(nums[i], pre+nums[i]);
//            ans = Math.max(ans, pre);
//        }
//        return ans;
        int n = nums.length;
        int ans = nums[0];
        for (int i = 1, pre = nums[0]; i < n; i++) {
            pre = Math.max(nums[i], pre + nums[i]);
            ans = Math.max(ans, pre);
        }
        return ans;
    }


    public static int maxSumReverse2(int[] nums){
        int n = nums.length;

        // start[i] : 所有必须以i开头的子数组中，最大累加和是多少
        int[] start = new int[n];
        start[n-1] = nums[n-1];

        for(int i = n-2; i >= 0; i--){
            start[i] = Math.max(nums[i], nums[i]+start[i+1]);
        }

        int ans = Integer.MIN_VALUE;

        // end : 子数组必须以i-1结尾，其中的最大累加和
        int end = 0;

        // maxEnd :
        // 0~i-1范围上，
        // 子数组必须以0结尾，其中的最大累加和
        // 子数组必须以1结尾，其中的最大累加和
        // ...
        // 子数组必须以i-1结尾，其中的最大累加和
        // 所有情况中，最大的那个累加和就是maxEnd
        int maxend = 0;

        for(int i = 0; i < n; i++){
            ans = Math.max(ans, maxend+start[i]);
            end = Math.max(nums[i], end+nums[i]);
            maxend = Math.max(end, maxend);
        }

        return ans;
    }

    // 为了测试
    // 生成随机数组
    public static int[] randomArray(int n, int v) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * (v * 2 + 1)) - v;
        }
        return ans;
    }

    // 为了测试
    // 对数器
    public static void main(String[] args) {
        int n = 50;
        int v = 200;
        int testTime = 20000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * n) + 1;
            int[] arr = randomArray(len, v);
            int ans1 = maxSumReverse1(arr);
            int ans2 = maxSumReverse2(arr);

        }
        System.out.println("测试结束");
    }

}
