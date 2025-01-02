package class097;

import java.io.*;

// 判断较大的数字是否是质数(Miller-Rabin测试)
// 测试链接 : https://www.luogu.com.cn/problem/U148828
// 如下代码无法通过所有测试用例
// 本文件可以解决10^9范围内数字的质数检查
// 时间复杂度O(s * (logn)的三次方)，很快
// 为什么不能搞定所有long类型的数字检查
// 原因在于long类型位数不够，乘法同余的时候会溢出，课上已经做了说明
public class LargeNumberIsPrime1 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        int t = Integer.valueOf(br.readLine());
        for(int i = 0; i < t; i++){
            long n = Integer.valueOf(br.readLine());
            out.println(millerRabin(n)? "Yes":"No");
        }

        out.flush();
        out.close();
        br.close();
    }

    public static int[] p = new int[] {2,3,5,7,11,13,17,19,23,29,31,37};
    public static boolean millerRabin(long n) {
        if (n <= 2) {
            return n == 2;
        }
        if ((n & 1) == 0) {
            return false;
        }
        for (int i = 0; i < p.length && p[i] < n; i++) {
            if (witness(p[i], n)) {
                return false;
            }
        }
        return true;
    }

    // 单次测试的函数，不用纠结原理
    // 返回n是不是合数
    public static boolean witness(long a, long n) {
        long u = n - 1;
        int t = 0;
        while ((u & 1) == 0) {
            t++;
            u >>= 1;
        }
        long x1 = power(a, u, n), x2;
        for (int i = 1; i <= t; i++) {
            x2 = power(x1, 2, n);
            if (x2 == 1 && x1 != 1 && x1 != n - 1) {
                return true;
            }
            x1 = x2;
        }
        if (x1 != 1) {
            return true;
        }
        return false;
    }

    // 返回 : n的p次方 % mod
    // 快速幂，讲解098会重点讲述，此时直接用即可
    public static long power(long n, long p, long mod) {
        long ans = 1;
        while (p > 0) {
            if ((p & 1) == 1) {
                ans = (ans * n) % mod;
            }
            n = (n * n) % mod;
            p >>= 1;
        }
        return ans;
    }

}
