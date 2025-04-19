package class109;

import java.io.*;

// 逆序对数量(归并分治)
// 测试链接 : https://www.luogu.com.cn/problem/P1908
public class NumberOfReversePair1 {
    public static int n;
    public static int maxn = 500001;
    public static int[] nums = new int[maxn];
    public static int[] helper = new int[maxn];


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken() != StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            for(int i = 0; i<n; i++){
                in.nextToken();
                nums[i] = (int) in.nval;
            }
            out.println(f(0, n-1));
        }
        out.flush();
        out.close();
        br.close();
    }


    public static long f(int l, int r){
        if(l>=r){
            return 0;
        }
        int m = (l+r)/2;
        long ans = 0L;

        ans += f(l, m);
        ans += f(m+1, r);
        return ans + merge(l, m, r);
    }

    public static long merge(int l, int m, int r){
        int i = m;
        int j = r;
        long ans = 0L;
        while(i>=l && j>m){
            if(nums[i] > nums[j]){
                ans += j-m;
                i--;
            }else{
                j--;
            }
        }

        i = l;
        j = m+1;
        int k = l;
        while(i<=m && j<=r){
            if(nums[i]<nums[j]){
                helper[k++] = nums[i++];
            }else{
                helper[k++] = nums[j++];
            }
        }

        while(i<=m){
            helper[k++] = nums[i++];
        }

        while(j<=r){
            helper[k++] = nums[j++];
        }
        for(int x = l; x <= r; x++){
            nums[x] = helper[x];
        }
        return ans;
    }

}
