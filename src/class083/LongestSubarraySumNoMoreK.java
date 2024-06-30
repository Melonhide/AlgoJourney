package class083;
// 累加和不大于k的最长子数组
// 给定一个无序数组arr，长度为n，其中元素可能是正、负、0
// 给定一个整数k，求arr所有的子数组中累加和不大于k的最长子数组长度
// 要求时间复杂度为O(n)
// 测试链接 : https://www.nowcoder.com/practice/3473e545d6924077a4f7cbc850408ade
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过


import java.io.*;

public class LongestSubarraySumNoMoreK {
    public static int n, k;
    public static int maxn = 100001;
    public static int[] nums = new int[maxn];
    public static int[] presum = new int[maxn];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken() != StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            k = (int) in.nval;
            for(int i = 0; i < n; i++){
                in.nextToken();
                nums[i] = (int) in.nval;
            }
            out.println(compute1());
        }
        out.flush();
        out.close();
        br.close();
    }

    //   [3, -2, -4, 0, 6]
    //   [.., 6] <= -2 [..]<= -8
    //[0, 3,  3,  3, 3, 3]
    public static int compute1(){
        int sum = 0;
        for(int i = 0; i < n; i++){
            sum += nums[i];
            presum[i+1] = Math.max(presum[i], sum);
        }

        int ans = 0;
        for(int i = n-1, pos; i >= 0; sum -= nums[i],  i--){
            pos = find(sum-k);
            if(pos != -1){
                ans = Math.max(ans, i-pos+1);
            }
        }

        return ans;
    }

    public static int find(int x){
        int l = 0;
        int r = n;
        int ans = -1;
        while(l<=r){
            int m = (l+r)/2;
            if(presum[m] >= x){
                ans = m;
                r = m-1;
            }else{
                l = m+1;
            }
        }
        return ans;
    }
}
