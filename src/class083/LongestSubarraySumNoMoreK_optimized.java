package class083;

import java.io.*;

public class LongestSubarraySumNoMoreK_optimized {
    public static int maxn = 100001;
    public static int[] nums = new int[maxn];
    public static int[] minSums = new int[maxn];
    public static int[] minSumEnds = new int[maxn];
    public static int n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            k = (int) in.nval;
            for(int i = 0; i < n; i++){
                in.nextToken();
                nums[i] = (int) in.nval;
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(){
        minSums[n-1] = nums[n-1];
        minSumEnds[n-1] = n-1;
        for(int i = n-2; i >= 0; i--){
            //   ... i, i+1, ... ,n-1
            if(nums[i]<nums[i]+minSums[i+1]){
                minSums[i] = nums[i];
                minSumEnds[i] = i;
            }else{
                minSums[i] = nums[i]+minSums[i+1];
                minSumEnds[i] = minSumEnds[i+1];
            }
        }

        // minSums[5] = -9
        // minSumEnds[5] = 8
        // 0,0,0,0,0,-3,-3,0,-3,5,5 ,5
        // 0,1,2,3,4, 5, 6,7, 8,9,10,11
        int ans = 0;
        for(int i = 0, sum = 0, end = 0; i<n; i++){
            while(end<n && sum+minSums[end]<=k){
                sum += minSums[end];
                end = minSumEnds[end]+1;
            }
            if(i == end){
                end++;
            }else {
                ans = Math.max(end - i, ans);
                sum -= nums[i];
            }
        }
        return ans;
    }


}
