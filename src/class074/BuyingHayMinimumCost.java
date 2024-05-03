package class074;

import java.io.*;
import java.util.Arrays;

public class BuyingHayMinimumCost {

    public static int n, h;
    public static int maxn = 101;
    public static int maxh = 55001;

    public static int max;
    public static int[] cost = new int[maxn];
    public static int[] val = new int[maxn];

    public static int[] dp = new int[maxh];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            h = (int) in.nval;
            max = 0;
            for(int i = 1; i <= n; i++){
                in.nextToken();
                val[i] = (int) in.nval;
                max = Math.max(max, val[i]);
                in.nextToken();
                cost[i] = (int) in.nval;
            }

            out.println(compute2());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(){
        int[][] dp = new int[n+1][h+max+1];
        for(int i = 0; i <=n; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        dp[0][0] = 0;

        for(int i = 1; i <=n; i++){
            dp[i][0] = 0;
            for(int j = 1; j<dp[0].length; j++){
                dp[i][j] = dp[i-1][j];
                if(j>=val[i] && dp[i][j-val[i]]!=Integer.MAX_VALUE){
                    dp[i][j] = Math.min(dp[i][j], dp[i][j-val[i]]+cost[i]);
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for(int i = h; i<dp[0].length; i++){
            ans = Math.min(dp[n][i], ans);
        }
        return ans;
    }

    public static int compute2(){
        int right = max+h+1;
        Arrays.fill(dp, 1, right, Integer.MAX_VALUE);

        for(int i = 1; i <=n; i++){
            for(int j = val[i]; j<right; j++){
                if(dp[j-val[i]]!=Integer.MAX_VALUE){
                    dp[j] = Math.min(dp[j], dp[j-val[i]]+cost[i]);
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for(int i = h; i<right; i++){
            ans = Math.min(dp[i], ans);
        }
        return ans;
    }
}
