package class077;

import java.io.*;

// 合唱队
// 具体描述情打开链接查看
// 测试链接 : https://www.luogu.com.cn/problem/P3205
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的所有代码，并把主类名改成"Main"，可以直接通过
public class HeightAndChoir {
    public static int mod = 19650827;
    public static int n;
    public static int maxn = 1001;
    public static int[] arr = new int[maxn];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            for(int i = 0; i < n; i++){
                in.nextToken();
                arr[i] = (int) in.nval;
            }
            out.println(compute2());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(){
        int[] ans = f(0, n-1);
        return (ans[0]%mod+ans[1]%mod)%mod;
    }
    public static int[] f(int l , int r){
        if(l == r-1){
            if(arr[l]<arr[r]){
                return new int[]{1,1};
            }else{
                return new int[]{0,0};
            }
        }


        int left = 0;
        int right = 0;
        int[] tmp = f(l+1, r);
        if(arr[l]<arr[l+1]){
            left = (left + tmp[0])%mod;
        }
        if(arr[l]<arr[r]){
            left = (left+tmp[1])%mod;
        }

        tmp = f(l, r-1);
        if(arr[r]>arr[r-1]){
            right = (right + tmp[1])%mod;
        }
        if(arr[r]>arr[l]){
            right = (right+tmp[0])%mod;
        }

        return new int[]{left, right};

    }

    public static int compute2(){
        int[][][] dp = new int[n][n][2];
        for(int i = 0; i < n-1; i++){
            if(arr[i]<arr[i+1]){
                dp[i][i+1][0] = 1;
                dp[i][i+1][1] = 1;
            }
        }

        for(int l = n-3; l>=0; l--){
            for(int r = l+2; r<n; r++){
                if(arr[l]<arr[l+1]){
                    dp[l][r][0] = (dp[l][r][0] + dp[l+1][r][0])%mod;
                }
                if(arr[l]<arr[r]){
                    dp[l][r][0] = (dp[l][r][0] + dp[l+1][r][1])%mod;
                }

                if(arr[r]>arr[r-1]){
                    dp[l][r][1] = (dp[l][r][1] + dp[l][r-1][1])%mod;
                }
                if(arr[r]>arr[l]){
                    dp[l][r][1] = (dp[l][r][1]+dp[l][r-1][0])%mod;
                }
            }
        }

        return (dp[0][n-1][0]+dp[0][n-1][1]) % mod;
    }
}
