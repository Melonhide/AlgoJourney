package class087;

import java.io.*;

// 两个排列的最长公共子序列长度
// 给出由1~n这些数字组成的两个排列
// 求它们的最长公共子序列长度
// n <= 10^5
// 测试链接 : https://www.luogu.com.cn/problem/P1439
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class PermutationLCS {
    public static int n;
    public static int[] a = new int[100001];
    public static int[] b = new int[100001];
    public static int[] where = new int[100001];
    public static int[] ends = new int[100001];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            for(int i = 0; i < n; i++){
                a[i] = (int) in.nval;
                in.nextToken();
            }

            for(int i = 0; i < n; i++){
                b[i] = (int) in.nval;
                in.nextToken();
            }

            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(){
        for(int i = 0; i < n; i++){
            where[a[i]] = i;
        }

        for(int i = 0; i < n; i++){
            b[i] = where[b[i]];
        }
        int len = 0;
        for(int i = 0, find; i < n; i++){
            find = f(len, b[i]);
            if(find == -1){
                ends[len++] = b[i];
            }else {
                ends[find] = b[i];
            }
        }

        return len;
    }

    public static int f(int len, int target){
        int l = 0;
        int r = len-1;
        int ans = -1;
        while(l<=r){
            int m = (l+r)/2;
            if(ends[m]>=target){
                ans = m;
                r = m-1;
            }else{
                l = m+1;
            }

        }

        return ans;
    }


}
