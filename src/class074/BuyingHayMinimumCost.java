package class074;

import java.io.*;

public class BuyingHayMinimumCost {

    public static int n, h;
    public static int maxn = 101;
    public static int maxh = 50001;
    public static int[] cost = new int[maxn];
    public static int[] val = new int[maxn];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            h = (int) in.nval;
            for(int i = 1; i <= n; i++){
                in.nextToken();
                val[i] = (int) in.nval;
                in.nextToken();
                cost[i] = (int) in.nval;
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(){

    }
}
