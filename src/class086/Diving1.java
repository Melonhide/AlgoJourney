package class086;


import java.io.*;

// 潜水的最大时间与方案
// 一共有n个工具，每个工具都有自己的重量a、阻力b、提升的停留时间c
// 因为背包有限，所以只能背重量不超过m的工具
// 因为力气有限，所以只能背阻力不超过v的工具
// 希望能在水下停留的时间最久
// 返回最久的停留时间和下标字典序最小的选择工具的方案
// 注意这道题的字典序设定（根据提交的结果推论的）：
// 下标方案整体构成的字符串保证字典序最小
// 比如下标方案"1 120"比下标方案"1 2"字典序小
// 测试链接 : https://www.luogu.com.cn/problem/P1759
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class Diving1 {
    public static int m,v,n;
    public static int maxn = 101, maxm = 201, maxv = 201;
    public static int[][] nums = new int[maxn][3];
    public static int[][][] dp = new int[maxn][maxm][maxv];
    public static String[][][] path = new String[maxn][maxm][maxv];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            m = (int) in.nval;
            in.nextToken();
            v = (int) in.nval;
            in.nextToken();
            n = (int) in.nval;
            for(int i = 0; i<n; i++){
                in.nextToken();
                nums[i][0] = (int) in.nval;
                in.nextToken();
                nums[i][1] = (int) in.nval;
                in.nextToken();
                nums[i][2] = (int) in.nval;
            }
            compute();
            out.println(dp[0][0][0]);
            out.println(path[0][0][0]);
        }
        out.flush();
        out.close();
        br.close();
    }

    public static void build(){
        for(int i = 0; i <= n; i++){
            for(int j = 0; j<=m; j++){
                for(int k = 0; k<=v; k++){
                    dp[i][j][k] = 0;
                    path[i][j][k] = null;
                }
            }
        }
    }

    public static void compute(){
        build();
        for(int i = n-1; i>=0; i--){
           for(int j = m; j >=0; j--){
               for(int k = v, ans2; k>=0; k--){
                   dp[i][j][k] = dp[i+1][j][k];
                   path[i][j][k] = path[i+1][j][k];
                   if(nums[i][0]+j<=m && nums[i][1]+k<=v){
                       ans2 = nums[i][2]+dp[i+1][nums[i][0]+j][nums[i][1]+k];
                       String path2;
                       if(path[i+1][nums[i][0]+j][nums[i][1]+k]==null){
                           path2 = String.valueOf(i+1);
                       }else{
                           path2 = String.valueOf(i+1) + " " + path[i+1][nums[i][0]+j][nums[i][1]+k];
                       }
                       if(ans2 > dp[i][j][k]){
                           dp[i][j][k] = ans2;
                           path[i][j][k] = path2;
                       }else if(ans2 == dp[i][j][k]){
                           path[i][j][k] = path2.compareTo(path[i][j][k]) < 0? path2:path[i][j][k];
                       }
                   }
               }
           }
       }
    }
}
