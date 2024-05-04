package class075;

import java.io.*;
import java.util.Arrays;

public class CherryBlossomViewing {

    public static int maxn = 100001;
    public static int maxt = 1001;
    public static int enough = 1001;
    public static int[] v = new int[maxn];
    public static int[] w = new int[maxn];
    public static int[] dp = new int[maxt];
    public static int n, hour1, minute1, hour2, minute2, t, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            hour1 = (int) in.nval;
            in.nextToken();
            in.nextToken();
            minute1 = (int) in.nval;
            in.nextToken();
            hour2 = (int) in.nval;
            in.nextToken();
            in.nextToken();
            minute2 = (int) in.nval;
            t = (hour2-hour1)*60 + minute2-minute1;
            in.nextToken();
            n = (int) in.nval;
            m = 1;
            for(int i = 0, cost, val, cnt; i<n; i++){
                in.nextToken();
                cost = (int) in.nval;
                in.nextToken();
                val = (int) in.nval;
                in.nextToken();
                cnt = (int) in.nval;
                cnt = cnt == 0? enough:cnt;

                for(int j = 1; j<=cnt; j<<=1, m++){
                    v[m] = val*j;
                    w[m] = cost*j;
                    cnt -= j;
                }

                if(cnt > 0){
                    v[m] = val*cnt;
                    w[m++] = cost*cnt;
                }
            }
            out.println(compute());

        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(){
        Arrays.fill(dp, 0, t+1, 0);
        for(int i = 1; i<=m ;i++){
            for(int j = t; j>=w[i]; j--){
                dp[j] = Math.max(dp[j], dp[j-w[i]]+v[i]);
            }
        }
        return dp[t];
    }
}
