package class075;

import java.io.*;

public class BoundedKnapsackWithMonotonicQueue {
    public static int maxn = 101;
    public static int maxW = 40001;
    public static int n, W, l, r;

    public static int[] v = new int[maxn];
    public static int[] w = new int[maxn];
    public static int[] m = new int[maxn];
    public static int[] queue = new int[maxW];
    public static int[] dp = new int[maxW];
    public static void main(String[] args) throws IOException {
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

            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(){
        int[][] dp = new int[n+1][W+1];

        for(int i = 1; i <= n ; i++){
            for(int mod = 0; mod<Math.min(w[i], W+1); mod++){
                l = 0;
                r = 0;
                for(int j = mod, cnt = 0; j<=W; j+=w[i], cnt++){
                    while(l!=r &&  dp[i-1][queue[r-1]]+diff(j-queue[r-1], i)<=dp[i-1][j]){
                        r--;
                    }
                    queue[r++] = j;
                    dp[i][j] = dp[i-1][queue[l]] + diff(j-queue[l], i);

                    if(cnt - queue[l]/w[i] == m[i]){
                        l++;
                    }
                }
            }
        }
        return dp[n][W];
    }

    public static int diff(int tot, int singleindex){
        return tot/w[singleindex] * v[singleindex];
    }

//    public static int compute2(){
//        Arrays.fill(dp, 0, W+1, 0);
//        for(int i = 1; i <= n ; i++){
//            for(int mod = 0; mod<Math.min(w[i], W+1); mod++){
//                l = 0;
//                r = 0;
//                for(int j = W; j>=mod; j-=w[i]){
//                    while(l!=r &&  dp[queue[r-1]]+diff(j-queue[r-1], i)<=dp[j]){
//                        r--;
//                    }
//                    queue[r++] = j;
//                    dp[j] = dp[queue[l]] + diff(j-queue[l], i);
//
//                }
//            }
//        }
//
//        return dp[W];
//    }
}
