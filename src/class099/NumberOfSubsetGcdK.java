package class099;

import java.io.*;

// 最大公约数为1的子序列数量
// 给你一个数组，返回有多少个子序列的最大公约数是1
// 结果可能很大对1000000007取模
// 测试链接 : https://www.luogu.com.cn/problem/CF803F
// 测试链接 : https://codeforces.com/problemset/problem/803/F
// 1 <= n <= 10^5
// 1 <= nums[i] <= 10^5
// 扩展问题
// 最大公约数为k的子序列数量
// 给定一个长度为n的正数数组nums，还有正数k
// 返回有多少子序列的最大公约数为k
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class NumberOfSubsetGcdK {
    public static int n;
    public static int limit = 100001;
    public static int[] cnts = new int[limit];
    public static int[] dp = new int[limit];
    public static int[] pow2 = new int[limit];
    public static int mod = 1000000007;

    public static void build(){
        pow2[0] = 1;
        for(int j = 1; j<limit; j++){
            pow2[j] = (pow2[j-1]*2)%mod;
        }
    }
    public static void main(String[] args) throws IOException {
        build();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken() != StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            for(int i = 0; i < n; i++){
                in.nextToken();
                cnts[(int) in.nval]++;
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(){

        for(int i = limit-1, count; i>0; i--){
            count = 0;
            for(int j=i; j<limit; j+=i){
                count += cnts[j];
            }
            dp[i] = (pow2[count]-1+mod)%mod;
            for(int j = 2; j*i<limit; j++){
                dp[i] = (dp[i]-dp[j*i]+mod)%mod;
            }
        }

        return dp[1];
    }
}
