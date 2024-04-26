package class071;


// 子序列累加和必须被7整除的最大累加和
// 给定一个非负数组nums，
// 可以任意选择数字组成子序列，但是子序列的累加和必须被7整除
// 返回最大累加和
// 对数器验证
public class MaxSumDividedBy7 {
    public static int maxSum1(int[] nums){
        return f(nums, 0 , 0);
    }

    public static int f(int[] nums, int i, int sum){
        if(i == nums.length){
            return sum%7==0? sum:0;
        }

        return Math.max(f(nums, i+1, sum+nums[i]), f(nums, i+1, sum));
    }

    public static int maxSum2(int[] nums){
        int n = nums.length;
        int[][] dp = new int[n+1][7];
        for(int i = 1; i < 7; i++){
            dp[0][i] = -1;
        }

        for(int i = 1; i <= n; i++){
            for(int j = 0, need; j<7; j++){
                dp[i][j] = dp[i-1][j];
                need = (j - nums[i-1]%7 +7)%7;
                if(dp[i-1][need]!=-1) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][need] + nums[i - 1]);
                }
            }
        }
        return dp[n][0];
    }

    // 为了测试
    // 生成随机数组
    public static int[] randomArray(int n, int v) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * v);
        }
        return ans;
    }
    public static void main(String[] args) {
        int n = 15;
        int v = 30;
        int testTime = 20000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * n) + 1;
            int[] nums = randomArray(len, v);
            int ans1 = maxSum1(nums);
            int ans2 = maxSum2(nums);
            if (ans1 != ans2) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }

}
