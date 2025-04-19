package class109;

import java.io.*;
import java.util.Arrays;

// 逆序对数量(值域树状数组)
// 测试链接 : https://www.luogu.com.cn/problem/P1908
public class NumberOfReversePair2 {
    public static int maxn = 500002;
    public static int n;
    public static int size;
    public static int[] tree = new int[maxn];
    public static int[] nums = new int[maxn];
    public static int[] sort = new int[maxn];

    public static void add(int i, int v){
        while(i<=n){
            tree[i] += v;
            i += rightone(i);
        }
    }

    public static long sum(int i){
        long ans = 0L;
        while(i>0){
            ans += tree[i];
            i -= rightone(i);
        }
        return ans;
    }

    public static int rightone(int i){
        return i&-i;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken() != StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            for(int i = 1; i <= n; i++){
                in.nextToken();
                nums[i] = (int) in.nval;
                sort[i] = (int) in.nval;
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static long compute(){
        Arrays.sort(sort, 1, n+1);
        size = 1;
        for(int i = 1; i <= n; i++){
            if(sort[i] != sort[size]){
                sort[++size] = sort[i];
            }
        }
        long ans = 0L;
        for(int i = 1; i <= n; i++){
            nums[i] = find(nums[i]);
            ans += sum(size)-sum(nums[i]);
            add(nums[i], 1);
        }
        return ans;
    }
    public static int find(int target){
        int l= 1;
        int r = size;

        while(l<=r){
            int m = (l+r)/2;
            if(sort[m] == target){
                return m;
            }else if(sort[m]<target){
                l = m+1;
            }else{
                r = m-1;
            }
        }
        return 0;
    }
}
