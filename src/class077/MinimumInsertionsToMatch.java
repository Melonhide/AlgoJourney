package class077;

import java.io.*;

// 完成配对需要的最少字符数量
// 给定一个由'['、']'、'('，')'组成的字符串
// 请问最少插入多少个括号就能使这个字符串的所有括号正确配对
// 例如当前串是 "([[])"，那么插入一个']'即可满足
// 输出最少需要插入多少个字符
// 测试链接 : https://www.nowcoder.com/practice/e391767d80d942d29e6095a935a5b96b
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class MinimumInsertionsToMatch {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        String str = br.readLine();
        out.println(compute(str));
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(String str){
        char[] s = str.toCharArray();
        int n = s.length;
        int[][] dp = new int[n][n];

        for(int i = 0; i < n; i++){
            dp[i][i] = 1;
            if(i+1<n){
                if(!((s[i] == '(' && s[i+1] == ')') || (s[i] == '[' && s[i+1] == ']'))){
                    dp[i][i+1] = 2;
                }
            }
        }

        for(int l = n-3; l>=0; l--){
            for(int r = l+2, p1 ,p2; r<n; r++){
                p1 = Integer.MAX_VALUE;
                p2 = Integer.MAX_VALUE;
                if((s[l] == '(' && s[r] == ')') || (s[l] == '[' && s[r] == ']')){
                    p1 = dp[l+1][r-1];
                }
                for(int m = l; m<r; m++){
                    p2 = Math.min(p2, dp[l][m]+dp[m+1][r]);
                }
                dp[l][r] = Math.min(p1,p2);
            }
        }

        return dp[0][n-1];
    }
    public static int f(char[] s, int l , int r){
        if(l==r){
            return 1;
        }

        if(l == r-1){
            if((s[l] == '(' && s[r] == ')') || (s[l] == '[' && s[r] == ']')){
                return 0;
            }else{
                return 2;
            }
        }

        int p1 = Integer.MAX_VALUE;
        if((s[l] == '(' && s[r] == ')') || (s[l] == '[' && s[r] == ']')){
            p1 = f(s, l+1, r-1);
        }
        int p2 = Integer.MAX_VALUE;
        for(int m = l ; m<r; m++){
            p2 = Math.min(f(s, l, m)+f(s, m+1, r), p2);
        }
        return Math.min(p1,p2);
    }
}
