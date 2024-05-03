package class075;

import java.io.*;
import java.util.Arrays;

public class BoundedKnapsackWithBinarySplitting {
    public static int n, W, j;
    public static int maxw = 40001;
    public static int maxn = 1001;

    public static int[] v = new int[maxn];
    public static int[] w = new int[maxn];
    public static int[] dp = new int[maxw];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            W = (int) in.nval;
            j = 1;
            for(int i = 1, vi, wi, mi; i <= n; i++){
                in.nextToken();
                vi = (int) in.nval;
                in.nextToken();
                wi = (int) in.nval;
                in.nextToken();
                mi = (int) in.nval;
                for(int k = 1; k<=mi; k<<=1, j++){
                    v[j] = vi*k;
                    w[j] = wi*k;
                    mi-=k;
                }
                if(mi>0){
                    v[j] = vi*mi;
                    w[j++] = wi*mi;
                }
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(){
        Arrays.fill(dp, 0, W+1, 0);
        for(int i =1; i <= j; i++){
            for(int k = W; k>=0; k--){
                if(k>=w[i]){
                    dp[k] = Math.max(dp[k], dp[k-w[i]]+v[i]);
                }
            }
        }
        return dp[W];
    }

}
