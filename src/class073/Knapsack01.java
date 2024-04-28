package class073;

import java.io.*;
import java.util.Arrays;


// 01背包(模版)
// 给定一个正数t，表示背包的容量
// 有m个货物，每个货物可以选择一次
// 每个货物有自己的体积costs[i]和价值values[i]
// 返回在不超过总容量的情况下，怎么挑选货物能达到价值最大
// 返回最大的价值
// 测试链接 : https://www.luogu.com.cn/problem/P1048
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的所有代码，并把主类名改成"Main"，可以直接通过
public class Knapsack01 {
    public static int maxm = 101;
    public static int maxt = 1001;
    public static int[] cost = new int[maxm];
    public static int[] val = new int[maxm];

    public static int[] dp = new int[maxt];
    public static int t, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            t = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            for(int i=0; i < m ; i++){
                in.nextToken();
                cost[i] = (int) in.nval;
                in.nextToken();
                val[i] = (int) in.nval;
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(){
        Arrays.fill(dp, 0, t+1, 0);
        for(int i = 1; i <= m; i++){
            for(int j = t; j>=cost[i-1]; j--){
                dp[j] = Math.max(dp[j], dp[j - cost[i - 1]] + val[i - 1]);
            }
        }
        return dp[t];
    }
}
