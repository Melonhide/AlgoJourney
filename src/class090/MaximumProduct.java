package class090;

// 分成k份的最大乘积
// 一个数字n一定要分成k份，得到的乘积尽量大是多少
// 数字n和k，可能非常大，到达10^12规模
// 结果可能更大，所以返回结果对1000000007取模
// 来自真实大厂笔试，没有在线测试，对数器验证
public class MaximumProduct {

    public static int maxValue1(int n, int k){
        return f(n, k);
    }

    public static int f(int n, int k){
        if(k == 1){
            return n;
        }
        int ans = 0;
        for(int i = 1; i < n && (n-i)>=(k-1); i++){
            ans = Math.max(ans, i*f(n-i, k-1));
        }
        return ans;
    }

    public static long power(long x, int n, int mod){
        long ans = 1;
        while(n != 0){
            if((n&1) == 1){
                ans = (ans*x)%mod;
            }
            n >>>= 1;
            x = (x*x)%mod;
        }
        return ans;
    }


    public static int maxValue2(int n, int k){
        int mod = 1000000007;
        int mean = n/k;
        int larger = n%k;
        long p1 = power(mean, k-larger, mod);
        long p2 = power(mean+1, larger, mod);
        return (int) (p1*p2)%mod;
    }


    public static void main(String[] args) {
        int N = 30;
        int testTimes = 2000;
        System.out.println("测试开始");
        for (int i = 1; i <= testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            int k = (int) (Math.random() * n) + 1;
            int ans1 = maxValue1(n, k);
            int ans2 = maxValue2(n, k);
            if (ans1 != ans2) {
                // 如果出错了
                // 可以增加打印行为找到一组出错的例子
                // 然后去debug
                System.out.println("出错了！");
            }
            if (i % 100 == 0) {
                System.out.println("测试到第" + i + "组");
            }
        }
        System.out.println("测试结束");
    }
}
