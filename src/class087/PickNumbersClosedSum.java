package class087;

// 选择k个数字使得两集合累加和相差不超过1
// 给定一个正数n，表示1~n这些数字都可以选择
// 给定一个正数k，表示要从1~n中选择k个数字组成集合A，剩下数字组成集合B
// 希望做到集合A和集合B的累加和相差不超过1
// 如果能做到，返回集合A选择了哪些数字，任何一种方案都可以
// 如果不能做到，返回长度为0的数组
// 2 <= n <= 10^6
// 1 <= k <= n
// 来自真实大厂笔试，没有测试链接，用对数器验证
public class PickNumbersClosedSum {
    public static int[] pick(int n, int k){
        long target = (1+n)*n/2;
        int sumMin = 0;
        int sumMax = 0;
        for(int i = 1, j = n; i <= k; i++, j--){
            sumMin += i;
            sumMax += j;
        }

        if(sumMin>target||sumMax<target){
            return new int[0];
        }

        return generate(target, n, k);
    }

    public static int[] generate(long sum, int n, int k){
        int range = n-k;
        long cur = (1+k)*k/2;
        long need = sum-cur;
        int[] ans = new int[k];
        for(int i = 1; i <= k; i++){
            ans[i-1] = i;
        }
        int move =(int) need/range;
        int left =(int) need % range;
        int ind = 0;
        while(move != 0){
            ans[ind++] += range;
            move--;
        }
        if(left != 0){
            ans[ind] += left;
        }

        return ans;
    }

    public static boolean pass(int n, int k, int[] ans) {
        if (ans.length == 0) {
            if (canSplit(n, k)) {
                return false;
            } else {
                return true;
            }
        } else {
            if (ans.length != k) {
                return false;
            }
            int sum = (n + 1) * n / 2;
            int pickSum = 0;
            for (int num : ans) {
                pickSum += num;
            }
            return Math.abs(pickSum - (sum - pickSum)) <= 1;
        }
    }

    // 记忆化搜索
    // 不是最优解，只是为了验证
    // 返回能不能做到
    public static boolean canSplit(int n, int k) {
        int sum = (n + 1) * n / 2;
        int wantSum = (sum / 2) + ((sum & 1) == 0 ? 0 : 1);
        int[][][] dp = new int[n + 1][k + 1][wantSum + 1];
        return f(n, 1, k, wantSum, dp);
    }

    public static boolean f(int n, int i, int k, int s, int[][][] dp) {
        if (k < 0 || s < 0) {
            return false;
        }
        if (i == n + 1) {
            return k == 0 && s == 0;
        }
        if (dp[i][k][s] != 0) {
            return dp[i][k][s] == 1;
        }
        boolean ans = f(n, i + 1, k, s, dp) || f(n, i + 1, k - 1, s - i, dp);
        dp[i][k][s] = ans ? 1 : -1;
        return ans;
    }



    public static void main(String[] args) {
        int N = 60;
        int testTime = 5000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int n = (int) (Math.random() * N) + 2;
            int k = (int) (Math.random() * n) + 1;
            int[] ans = pick(n, k);
            if (!pass(n, k, ans)) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }
}
