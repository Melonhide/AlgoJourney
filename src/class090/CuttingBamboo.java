package class090;

// 砍竹子II
// 现需要将一根长为正整数bamboo_len的竹子砍为若干段
// 每段长度均为正整数
// 请返回每段竹子长度的最大乘积是多少
// 答案需要对1000000007取模
// 测试链接 : https://leetcode.cn/problems/jian-sheng-zi-ii-lcof/
public class CuttingBamboo {
    public static int cuttingBamboo(int n){
        if(n == 2){
            return 1;
        }
        if(n == 3){
            return 2;
        }

        int mod = 1000000007;
        int tail = n%3 == 0? 1:(n%3 == 1? 4:2);
        int p = tail == 1? n/3: (n-tail)/3;
        return (int) ((power(3, p, mod)*tail)%mod);
    }

    public static long power(long x, int n, int mod){
        long ans = 1;
        while(n != 0){
            if((n & 1) != 0){
                ans = (ans * x) % mod;
            }
            x = (x*x)%mod;
            n >>>= 1;
        }
        return ans;
    }

}
