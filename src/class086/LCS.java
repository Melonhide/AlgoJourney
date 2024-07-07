package class086;

import java.io.*;

// 最长公共子序列其中一个结果
// 给定两个字符串str1和str2
// 输出两个字符串的最长公共子序列
// 如果最长公共子序列为空，则输出-1
// 测试链接 : https://www.nowcoder.com/practice/4727c06b9ee9446cab2e859b4bb86bb8
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class LCS {

    public static int maxn = 5001;
    public static char[] s1, s2;
    public static int n, m, k;
    public static int[][] dp = new int[maxn][maxn];
    public static char[] ans = new char[maxn];
    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        s1 = br.readLine().toCharArray();
        s2 = br.readLine().toCharArray();
        n = s1.length;
        m = s2.length;
        lcs();
        if(k == 0){
            out.println("-1");
        }else{
            for(int i = 0; i < k; i++){
                out.print(ans[i]);
            }
        }

        out.flush();
        out.close();
        br.close();
    }

    public static void lcs(){
        for(int i = 0; i <= n; i++){
            dp[i][m] = 0;
        }
        for(int i = 0; i <= m; i++){
            dp[n][i] = 0;
        }
        dp();
        k = dp[0][0];

        for(int i = 0, j = 0, target = k, c = 0; target>0;){
            if(s1[i] == s2[j]){
                ans[c++] = s1[i];
                target --;
                i++;
                j++;
            }else{
                if(dp[i+1][j] >= dp[i][j+1]){
                    i++;
                }else{
                    j++;
                }
            }
        }

    }

    public static void dp(){
        for(int i = n-1; i>=0; i--){
            for(int j = m-1; j>=0; j--){
                if(s1[i] == s2[j]){
                    dp[i][j] = 1 + dp[i+1][j+1];
                }else{
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
            }
        }
    }


}
