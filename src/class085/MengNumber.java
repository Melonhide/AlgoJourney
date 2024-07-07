package class085;

import java.io.*;

// 萌数
// 如果一个数字，存在长度至少为2的回文子串，那么这种数字被称为萌数
// 比如101、110、111、1234321、45568
// 求[l,r]范围上，有多少个萌数
// 由于答案可能很大，所以输出答案对1000000007求余
// 测试链接 : https://www.luogu.com.cn/problem/P3413
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class MengNumber {
    public static int mod = 1000000007;
    public static int[][][][] dp = new int[1001][11][11][2];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        String[] strs = br.readLine().split(" ");
        out.println(compute(strs[0].toCharArray(), strs[1].toCharArray()));
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(char[] l, char[] r){
        int ans = (count(r)-count(l)+mod)%mod;
        if(check(l)){
            ans = (ans + 1)%mod;
        }
        return ans;
    }

    public static int count(char[] num){
        if(num[0] == '0'){
            return 0;
        }
        int n = num.length;
        long all = 0;
        long base = 1;

        for(int i = n-1; i >= 0; i--){
            all = (all + (num[i]-'0') * base)%mod;
            base = (base*10)%mod;
        }

        build(n);
        return (int) ((all-f(num,0, 10, 10,0)+mod)%mod);
    }

    // 如果之前的位已经确定比num小，那么free == 1，表示接下的数字可以自由选择
    // 如果之前的位和num一样，那么free == 0，表示接下的数字不能大于num当前位的数字
    public static int f(char[] num, int i, int pp, int p, int free){
        if(i == num.length){
            return 1;
        }

        if(dp[i][pp][p][free] != -1){
            return dp[i][pp][p][free];
        }

        int cur = num[i]-'0';
        int ans = 0;
        if(free == 0){
            if(p == 10){
                ans = (ans + f(num, i+1, 10, 10, 1))%mod;
                for(int c = 1; c<cur; c++){
                    ans = (ans + f(num, i+1, 10, c, 1))%mod;
                }
                ans = (ans + f(num, i+1, 10, cur, 0))%mod;
            }else{
                for(int c = 0; c<cur; c++){
                    if(c != p && c != pp){
                        ans = (ans + f(num, i+1, p, c, 1))%mod;
                    }
                }
                if(cur != p && cur != pp){
                    ans = (ans + f(num, i+1, p, cur, 0))%mod;
                }
            }
        }else{
            if(p == 10){
                for(int c = 1; c <= 10; c++){
                    ans = (ans + f(num, i+1, 10, c, 1))%mod;
                }
            }else{
                for(int c = 0; c<10; c++){
                    if(c != p && c != pp){
                        ans = (ans + f(num, i+1, p, c, 1))%mod;
                    }
                }
            }
        }
        dp[i][pp][p][free] = ans;
        return ans;
    }

    public static void build(int n){
        for(int i = 0; i<n; i++){
            for(int j = 0; j< 11; j++){
                for(int k = 0; k<11; k++){
                    dp[i][j][k][0] = -1;
                    dp[i][j][k][1] = -1;
                }
            }
        }
    }

    public static boolean check(char[] l){
        int pp = 10;
        int p = 10;
        for(int i = 0, cur; i<l.length; i++){
            cur = l[i]-'0';
            if(cur == pp || cur == p){
                return true;
            }
            pp = p;
            p = cur;
        }
        return false;
    }
}
