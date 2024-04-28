package class073;

import java.io.*;
import java.util.Arrays;

public class DependentKnapsack {
    public static int maxn = 32001;
    public static int maxm = 61;

    public static int n, m;

    public static int[] cost = new int[maxm];
    public static int[] val = new int[maxm];
    public static boolean[] king = new boolean[maxm];

    public static int[] fans = new int[maxm];
    public static int[][] follows = new int[maxm][2];

    public static int[] dp = new int[maxn];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken() != StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            clean();
            for(int i = 1, v, p, q; i<m+1; i++){
                in.nextToken();
                v = (int) in.nval;
                in.nextToken();
                p = (int) in.nval;
                in.nextToken();
                q = (int) in.nval;
                cost[i] = v;
                val[i] =v*p;
                king[i] = q == 0;
                if(q!=0){
                    follows[q][fans[q]++] = i;
                }
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(){
        Arrays.fill(dp, 0, n+1, 0);
        for(int i = 1; i <= m; i++){
            if(king[i]){
                for(int j = n, fan1, fan2; j>=cost[i]; j--) {
                    dp[j] = Math.max(dp[j],dp[j-cost[i]]+val[i]);
                    fan1 = fans[i]>=1? follows[i][0]:-1;
                    fan2 = fans[i]==2? follows[i][1]:-1;
                    if(fan1 != -1){
                        if(j-cost[i]>=cost[fan1]){
                            dp[j] = Math.max(dp[j-cost[i]-cost[fan1]]+val[i]+val[fan1], dp[j]);
                        }
                    }

                    if(fan2 != -1){
                        if(j-cost[i]>=cost[fan2]){
                            dp[j] = Math.max(dp[j-cost[i]-cost[fan2]]+val[i]+val[fan2],dp[j]);
                        }
                    }

                    if(fan1!=-1 && fan2!=-1){
                        if(j-cost[i]>=cost[fan2]+cost[fan1]){
                            dp[j] = Math.max(dp[j-cost[i]-cost[fan2]-cost[fan1]]+val[i]+val[fan1]+val[fan2],dp[j]);
                        }
                    }
                }
            }
        }
        return dp[n];
    }

    public static void clean(){
        for(int i = 1; i <=m;i++){
            fans[i] = 0;
        }
    }
}
