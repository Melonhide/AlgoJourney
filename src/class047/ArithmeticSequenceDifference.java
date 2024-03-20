package class047;

import java.io.*;
import java.util.Arrays;

public class ArithmeticSequenceDifference {
    public static int maxsize = 10000005;
    public static long[] nums = new long[maxsize];

    public static int n, m;

    public static long max, xor;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        while(in.nextToken() != StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            for(int i=0, l, r, s, e, d; i<m; i++){
                in.nextToken();
                l = (int) in.nval;
                in.nextToken();
                r = (int) in.nval;
                in.nextToken();
                s = (int) in.nval;
                in.nextToken();
                e = (int) in.nval;

                d = (e-s)/(r-l);

                set(l,r,s,e,d);
            }
            build();
            max = 0;
            xor = 0;
            for( int i = 0; i<=n; i++){
                max = Math.max(max, nums[i]);
                xor ^= nums[0];
            }

            out.println(xor + " " + max);
        }

        out.flush();
        out.close();
        br.close();

    }

    public static void set(int l, int r, int s ,int e, int d){
        Arrays.fill(nums, 0);
        nums[l] += s;
        nums[l+1] += d-s;
        nums[r+1] -= e+d;
        nums[r+2] += e;
    }

    public static void build(){
        for(int i = 1; i<=n; i++){
            nums[i]+=nums[i-1];
        }

        for(int i = 1; i<=n; i++){
            nums[i]+=nums[i-1];
        }
    }

}
