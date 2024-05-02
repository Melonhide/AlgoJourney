package class074;

import java.io.*;

public class UnboundedKnapsack {
    public static int t, m;
    public static int maxm = 10001;
    public static int maxt = 10000001;
    public static int[] cost = new int[maxm];
    public static int[] val = new int[maxm];
    public static long[] dp = new long[maxt];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            t = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            for(int i = 1; i <= m; i++){
                in.nextToken();
                cost[i] = (int) in.nval;
                in.nextToken();
                val[i] = (int) in.nval;
            }
            out.println(compute2());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static long compute(){
        long[][] dp =new long[m+1][t+1];
        for(int i = 1; i<=m; i++){
            for(int j = 0; j<=t; j++){
                dp[i][j] = dp[i-1][j];
                if(j>=cost[i]){
                    dp[i][j] = Math.max(dp[i][j], dp[i][j-cost[i]]+val[i]);
                }
            }
        }
        return dp[m][t];
    }
    public static long compute2(){

        for(int i = 1; i<=m; i++){
            for(int j = 0; j<=t; j++){
                if(j>=cost[i]){
                    dp[j] = Math.max(dp[j], dp[j-cost[i]]+val[i]);
                }
            }
        }
        return dp[t];
    }
}
