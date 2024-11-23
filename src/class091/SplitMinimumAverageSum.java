package class091;

import java.util.Arrays;

// 平均值最小累加和
// 给定一个数组arr，长度为n
// 再给定一个数字k，表示一定要将arr划分成k个集合
// 每个数字只能进一个集合
// 返回每个集合的平均值都累加起来的最小值
// 平均值向下取整
// 1 <= n <= 10^5
// 0 <= arr[i] <= 10^5
// 1 <= k <= n
// 来自真实大厂笔试，没有在线测试，对数器验证
public class SplitMinimumAverageSum {
    public static int minAverageSum1(int[] arr, int k){
        int[] sums = new int[k];
        int[] cnts = new int[k];
        return f(0, arr, sums, cnts);
    }

    public static int f(int i, int[] arr, int[] sums, int[] cnts){
        if(i == arr.length){
            int ans = 0;
            for(int j = 0; j < cnts.length; j++){
                if(cnts[j] == 0){
                    return Integer.MAX_VALUE;
                }
                ans += sums[j]/cnts[j];
            }
            return ans;
        }else{
            int ans = Integer.MAX_VALUE;
            for(int j = 0; j < cnts.length; j++){
                sums[j] += arr[i];
                cnts[j]++;
                ans = Math.min(ans, f(i+1, arr, sums, cnts));
                cnts[j]--;
                sums[j] -= arr[i];
            }
            return ans;
        }
    }
    public static int minAverageSum2(int[] arr, int k){
        Arrays.sort(arr);
        int ans = 0;
        for(int i = 0; i < k-1; i++){
            ans += arr[i];
        }
        int sum = 0;
        for(int i = k-1; i<arr.length; i++){
            sum += arr[i];
        }
        ans += sum/(arr.length-(k-1));
        return ans;
    }

    // 为了测试
    public static int[] randomArray(int n, int v) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * v);
        }
        return ans;
    }

    // 为了测试
    public static void main(String[] args) {
        int N = 8;
        int V = 10000;
        int testTimes = 2000;
        System.out.println("测试开始");
        for (int i = 1; i <= testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[] arr = randomArray(n, V);
            int k = (int) (Math.random() * n) + 1;
            int ans1 = minAverageSum1(arr, k);
            int ans2 = minAverageSum2(arr, k);
            if (ans1 != ans2) {
                System.out.println("出错了!");
            }
            if (i % 100 == 0) {
                System.out.println("测试到第" + i + "组");
            }
        }
        System.out.println("测试结束");
    }
}
