package class109;

import java.io.*;
import java.util.Arrays;

public class IncreasingTriples {

    public static int maxn = 30001;
    public static int n;
    public static int size;
    public static int[] t1 = new int[maxn];
    public static int[] t2 = new int[maxn];
    public static int[] nums = new int[maxn];
    public static int[] sortednums = new int[maxn];

    public static void add(int[] tree, int i, int v){
        while(i<=n){
            tree[i] += v;
            i += rightone(i);
        }
    }

    public static int sum(int[] tree, int i){
        int ans = 0;
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
            for(int i = 0; i < n; i++){
                in.nextToken();
                nums[i] = (int) in.nval;
                sortednums[i] = (int) in.nval;
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(){
        build();
        int ans = 0;
        for(int i = 0; i < n; i++){
            ans += sum(t2, nums[i]-1);
            add(t2, nums[i], sum(t1, nums[i]-1));
            add(t1, nums[i], 1);
        }

        return ans;
    }

    public static void build(){
        Arrays.sort(sortednums, 0, n);
        size = 1;
        for(int i = 1; i < n; i++){
            if(sortednums[i] != sortednums[i-1]){
                sortednums[size++] = sortednums[i];
            }
        }
        for(int i = 0; i < n; i++){
            nums[i] = find(nums[i])+1;
        }
    }

    public static int find(int target){
        int l = 0;
        int r = size-1;
        while(l<=r){
            int m = (l+r)/2;
            if(sortednums[m] == target){
                return m;
            }else if(sortednums[m] < target){
                l = m+1;
            }else{
                r = m-1;
            }
        }
        return 0;
    }
}
