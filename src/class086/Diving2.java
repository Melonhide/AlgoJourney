package class086;

import java.io.*;

public class Diving2 {
    public static int m,v,n;
    public static int maxn = 101, maxm = 201, maxv = 201;
    public static int[][] nums = new int[maxn][3];
    public static int[][] dp = new int[maxm][maxv];
    public static String[][] path = new String[maxm][maxv];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            m = (int) in.nval;
            in.nextToken();
            v = (int) in.nval;
            in.nextToken();
            n = (int) in.nval;
            for(int i = 0; i<n; i++){
                in.nextToken();
                nums[i][0] = (int) in.nval;
                in.nextToken();
                nums[i][1] = (int) in.nval;
                in.nextToken();
                nums[i][2] = (int) in.nval;
            }
            compute();
            out.println(dp[0][0]);
            out.println(path[0][0]);
        }
        out.flush();
        out.close();
        br.close();
    }

    public static void build(){
        for(int j = 0; j<=m; j++){
            for(int k = 0; k<=v; k++){
                dp[j][k] = 0;
                path[j][k] = null;
            }
        }
    }

    public static void compute(){
        build();
        for(int i = n-1; i>=0; i--){
            for(int j = 0; j <= m; j++){
                for(int k = 0, ans2; k<=v; k++){
                    if(nums[i][0]+j<=m && nums[i][1]+k<=v){
                        ans2 = nums[i][2]+dp[nums[i][0]+j][nums[i][1]+k];
                        String path2;
                        if(path[nums[i][0]+j][nums[i][1]+k]==null){
                            path2 = String.valueOf(i+1);
                        }else{
                            path2 = String.valueOf(i+1) + " " + path[nums[i][0]+j][nums[i][1]+k];
                        }
                        if(ans2 > dp[j][k]){
                            dp[j][k] = ans2;
                            path[j][k] = path2;
                        }else if(ans2 == dp[j][k]){
                            path[j][k] = path2.compareTo(path[j][k]) < 0? path2:path[j][k];
                        }
                    }
                }
            }
        }
    }
}
