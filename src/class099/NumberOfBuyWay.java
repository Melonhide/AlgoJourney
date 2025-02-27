package class099;

// 最大公约数为1的子序列数量
// 给你一个数组，返回有多少个子序列的最大公约数是1
// 结果可能很大对1000000007取模
// 测试链接 : https://www.luogu.com.cn/problem/CF803F
// 测试链接 : https://codeforces.com/problemset/problem/803/F
// 1 <= n <= 10^5
// 1 <= nums[i] <= 10^5
// 扩展问题
// 最大公约数为k的子序列数量
// 给定一个长度为n的正数数组nums，还有正数k
// 返回有多少子序列的最大公约数为k
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过

import java.io.*;

public class NumberOfBuyWay {
    public static int n;
    public static int[] cnts = new int[100001];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken() != StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            for(int i = 0; i < n; i++){
                cnts[i]++;
            }
            out.println(compute());
        }

    }

    public static int compute(){

    }

}
