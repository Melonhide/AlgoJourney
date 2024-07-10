package class086;


// 最长递增子序列字典序最小的结果
// 给定数组arr，设长度为n
// 输出arr的最长递增子序列
// 如果有多个答案，请输出其中字典序最小的
// 注意这道题的字典序设定（根据提交的结果推论的）：
// 每个数字看作是单独的字符，比如120认为比36的字典序大
// 保证从左到右每个数字尽量小
// 测试链接 : https://www.nowcoder.com/practice/30fb9b3cab9742ecae9acda1c75bf927
// 测试链接 : https://www.luogu.com.cn/problem/T386911
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过

import java.io.*;
import java.util.Arrays;

public class LIS {
    public static int maxn = 100001;
    public static int[] nums = new int[maxn];
    public static int n, len;
    public static int[] ans = new int[maxn];
    public static int[] dp = new int[maxn];
    public static int[] ends = new int[maxn];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken() != StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            for(int i = 0; i < n; i++){
                nums[i] = (int) in.nval;
                in.nextToken();
            }
            len = lis();
            for(int i = 0; i < len; i++){
                out.print(ans[i] + " ");
            }
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int lis(){
        int len = f();
        Arrays.fill(ans, 0, len, Integer.MAX_VALUE);
        for(int i = 0; i < n; i++){
            if(dp[i] == len){
                ans[0] = nums[i];
            }else{
                if(nums[i] > ans[len-dp[i]-1]){
                    ans[len-dp[i]] = nums[i];
                }
            }
        }
        return len;
    }

    public static int f(){
        int ans = 0;
        for(int i = n-1, find; i >= 0; i--){
            find = bs(nums[i], ans-1);
            if(find == -1){
                ends[ans++] = nums[i];
                dp[i] = ans;
            }else{
                ends[find] = nums[i];
                dp[i] = find+1;
            }
        }
        return ans;
    }

    public static int bs(int target, int limit){
        int l = 0;
        int r = limit;
        int ans = -1;
        while(l<=r){
            int m = (l+r)/2;
            if(ends[m] <= target){
                ans = m;
                r = m-1;
            }else{
                l = m+1;
            }
        }
        return ans;
    }

}
