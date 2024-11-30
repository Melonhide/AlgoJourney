package class094;

import java.io.*;
import java.util.Arrays;

// 砍树
// 一共有n棵树，每棵树都有两个信息：
// 第一天这棵树的初始重量、这棵树每天的增长重量
// 你每天最多能砍1棵树，砍下这棵树的收益为：
// 这棵树的初始重量 + 这棵树增长到这一天的总增重
// 从第1天开始，你一共有m天可以砍树，返回m天内你获得的最大收益
// 测试链接 : https://pintia.cn/problem-sets/91827364500/exam/problems/91827367873
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class CuttingTree {
    public static int maxn = 251;
    public static int[][] tree = new int[maxn][2];
    public static int[][] dp = new int[maxn][maxn];

    public static int n, m;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken()!= StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            in.nextToken();
            for(int i = 1 ; i <= n; i++){
                tree[i][0] = (int) in.nval;
                in.nextToken();
            }
            for(int i = 1 ; i <= n; i++){
                tree[i][1] = (int) in.nval;
                in.nextToken();
            }

            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }
    public static int compute(){
        Arrays.sort(tree, 1, n+1, (int[] a, int[] b)->(a[1]-b[1]));
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]+tree[i][0]+tree[i][1]*(j-1));
            }
        }

        return dp[n][m];
    }

}

