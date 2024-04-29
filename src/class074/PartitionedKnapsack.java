package class074;


// 分组背包(模版)
// 给定一个正数m表示背包的容量，有n个货物可供挑选
// 每个货物有自己的体积(容量消耗)、价值(获得收益)、组号(分组)
// 同一个组的物品只能挑选1件，所有挑选物品的体积总和不能超过背包容量
// 怎么挑选货物能达到价值最大，返回最大的价值
// 测试链接 : https://www.luogu.com.cn/problem/P1757
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的所有代码，并把主类名改成"Main"，可以直接通过


import java.io.*;
import java.util.Arrays;

public class PartitionedKnapsack {

    public static int maxm = 1001;
    public static int maxn = 1001;
    public static int[][] arr = new int[maxn][3];
    public static int[] dp = new int[maxm];

    public static int m, n;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            m = (int)in.nval;
            in.nextToken();
            n = (int)in.nval;
            for(int i = 0; i<n; i++){
                in.nextToken();
                arr[i][0] = (int) in.nval;
                in.nextToken();
                arr[i][1] = (int) in.nval;
                in.nextToken();
                arr[i][2] = (int) in.nval;
            }
            out.println(compute2());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute1(){
        Arrays.sort(arr, 0, n, (a,b)->(a[2]-b[2]));
        int teams = 1;
        for(int i = 1; i <n; i++){
            if(arr[i][2]!=arr[i-1][2]){
                teams++;
            }
        }
        int[][] dp = new int[teams+1][m+1];
        for(int i = 1, start = 0, end = start+1; i <= teams; i++){
            while(arr[end][2]==arr[start][2]){
                end++;
            }
//            for(int index=start; index<end; index++){
//                for(int j = 0; j<=m; j++){
//                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j]);
//                    if(j>=arr[index][0]){
//                        dp[i][j] = Math.max(dp[i][j], dp[i-1][j-arr[index][0]]+arr[index][1]);
//                    }
//                }
//            }

            for(int j = 0; j<=m; j++){
                dp[i][j] = dp[i-1][j];
                for(int index = start; index<end; index++){
                    if(j>=arr[index][0]){
                        dp[i][j] = Math.max(dp[i][j], dp[i-1][j-arr[index][0]]+arr[index][1]);
                    }
                }
            }
            start = end++;
        }
        return dp[teams][m];
    }


    public static int compute2(){
        Arrays.fill(dp, 0, m+1, 0);
        Arrays.sort(arr, 0, n, (a,b)->(a[2]-b[2]));

        for(int start = 0, end = start+1; start<n;){
            while(arr[end][2]==arr[start][2]){
                end++;
            }
            for(int j = m; j>=0; j--){
                for(int index = start; index<end; index++){
                    if(j>=arr[index][0]){
                        dp[j] = Math.max(dp[j], dp[j-arr[index][0]]+arr[index][1]);
                    }
                }
            }
            start = end++;
        }
        return dp[m];
    }
}
