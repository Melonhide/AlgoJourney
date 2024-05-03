package class075;

import java.io.*;
import java.util.Arrays;

public class BoundedKnapsack {

    public static int n;
    public static int W;

    public static int maxn = 101;
    public static int maxw = 40001;
    public static int[] v = new int[maxn];
    public static int[] w = new int[maxn];
    public static int[] m = new int[maxn];
    public static int[] dp = new int[maxw];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            W = (int) in.nval;
            for(int i = 1; i <= n; i++){
                in.nextToken();
                v[i] = (int) in.nval;
                in.nextToken();
                w[i] = (int) in.nval;
                in.nextToken();
                m[i] = (int) in.nval;
            }
            out.println(compute2());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(){
        int[][] dp = new int[n+1][W+1];

        for(int i = 1; i <= n; i++){
            for(int j = 0; j<=W; j++){
                dp[i][j] = dp[i-1][j];
                if(j>=w[i]){
                    for(int k = 1; k <= m[i]; k++){
                        if(j-w[i]*k>=0){
                            dp[i][j] = Math.max(dp[i][j], dp[i-1][j-w[i]*k]+k*v[i]);
                        }
                    }
                }
            }
        }
        return dp[n][W];
    }

    public static int compute2(){
        Arrays.fill(dp, 0, W+1, 0);
        for(int i = 1; i <= n; i++){
            for(int j = W; j>=0; j--){
                if(j>=w[i]){
                    for(int k = 1; k <= m[i]; k++){
                        if(j-w[i]*k>=0){
                            dp[j] = Math.max(dp[j], dp[j-w[i]*k]+k*v[i]);
                        }
                    }
                }
            }
        }
        return dp[W];
    }
}
