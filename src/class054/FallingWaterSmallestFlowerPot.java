package class054;

import java.io.*;
import java.util.Arrays;

public class FallingWaterSmallestFlowerPot {
    public static int n, d, maxsize = 100001, minh, mint, maxh, maxt;

    public static int[][] nums = new int[maxsize][2];
    public static int[] maxdeque = new int[maxsize];
    public static int[] mindeque = new int[maxsize];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out  = new PrintWriter(new OutputStreamWriter(System.out));

        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            d = (int) in.nval;
            for(int i = 0; i < n; i++){
                in.nextToken();
                nums[i][0] = (int) in.nval;
                in.nextToken();
                nums[i][1] = (int) in.nval;
            }
            out.println(build());
        }

        out.flush();
        out.close();
        br.close();
    }

    public static int build(){
        minh = mint = maxh = maxt = 0;
        Arrays.sort(nums, 0, n, (a,b)->a[0]-b[0]);
        int ans = Integer.MAX_VALUE;

        for(int l = 0, r = 0; l<n; l++){
            while(r<n && !ok()){
                push(r++);
            }

            if(ok()){
                ans = Math.min(ans, nums[r-1][0]-nums[l][0]);
            }
            pop(l);

        }

        return ans == Integer.MAX_VALUE? -1:ans;
    }

    public static void push(int i){
        while(maxh<maxt && nums[i][1]>=nums[maxdeque[maxt-1]][1]){
            maxt--;
        }
        maxdeque[maxt++] = i;

        while(minh<mint && nums[i][1]<=nums[mindeque[mint-1]][1]){
            mint--;
        }
        mindeque[mint++] = i;
    }

    public static void pop(int i){
        if(minh<mint && mindeque[minh] == i){
            minh++;
        }
        if(maxh<maxt && maxdeque[maxh] == i){
            maxh++;
        }
    }

    public static boolean ok(){
        int max = maxh==maxt? 0: nums[maxdeque[maxh]][1];
        int min = minh==mint? 0: nums[mindeque[minh]][1];
        return max-min>=d;
    }

}
