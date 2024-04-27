package class072;

import java.io.*;

public class LongestNoDecreaseModifyKSubarray {

    public static int maxn = 100001;
    public static int[] nums = new int[maxn];
    public static int[] help = new int[maxn];
    public static int[] ends = new int[maxn];

    public static int n, k;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            k = (int) in.nval;

            for(int i = 0; i<n; i++){
                in.nextToken();
                nums[i] = (int) in.nval;
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    // k = 3
    // 0,1,2,3,4,5,6,7

    public static int compute(){
        right();
        int ans = Integer.MIN_VALUE;
        int len = 0;
        for(int i = 0, j = k, find, left; j<n;j++, i++){
            find = bs2(len-1, nums[j]);
            left = find == -1? len:find;
            ans = Math.max(ans, left+k+help[j]);

            find = bs2(len-1, nums[i]);
            if(find == -1){
                ends[len++] = nums[i];

            }else{
                ends[find] = nums[i];
            }
        }
        return Math.max(ans,len+k);
    }

    public static int bs2(int r, int num){
        int find = -1, l = 0;
        while(l<=r){
            int mid = l + ((r-l)>>>1);
            if(ends[mid]>num){
                find = mid;
                r = mid-1;
            }else{
                l = mid+1;
            }
        }
        return find;
    }

    // 生成辅助数组right
    // right[j] :
    // 一定以arr[j]做开头的情况下，arr[j...]上最长不下降子序列长度是多少
    // 关键逻辑 :
    // 一定以arr[i]做开头的情况下，arr[i...]上最长不下降子序列
    // 就是！从n-1出发来看(从右往左遍历)，以arr[i]做结尾的情况下的最长不上升子序列
    public static void right(){
        int len = 0;

        help[n-1] = 1;
        ends[0] = nums[n-1];
        for(int i = n-2, find; i >= 0; i--){
            find = bs1(len-1, nums[i]);
            if(find == -1){
                ends[len++] = nums[i];
                help[i] = len;
            }else{
                ends[find] = nums[i];
                help[i] = find+1;
            }
        }
    }

    // 求最长不上升子序列长度的二分
    // ends[0...len-1]是降序的，找到<num的最左位置
    // 不存在返回-1
    public static int bs1(int r, int num){
        int ans = -1;
        int l = 0;
        while(l<=r){
            int mid = l + ((r-l)>>>1);
            if(ends[mid] < num){
                ans = mid;
                r = mid-1;
            }else{
                l = mid+1;
            }
        }
        return ans;
    }
}
