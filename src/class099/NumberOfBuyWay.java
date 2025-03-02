package class099;

import java.io.*;

// 多次查询购买方法
// 一共有4种硬币，面值分别为v0、v1、v2、v3，这个永远是确定的
// 每次去购物的细节由一个数组arr来表示，每次购物都是一次查询
// arr[0] = 携带v0面值的硬币数量
// arr[1] = 携带v1面值的硬币数量
// arr[2] = 携带v2面值的硬币数量
// arr[3] = 携带v3面值的硬币数量
// arr[4] = 本次购物一定要花多少钱
// 返回每次有多少种花钱的方法
// 1 <= v0、v1、v2、v3、arr[i] <= 10^5
// 查询数量 <= 1000
// 测试链接 : https://www.luogu.com.cn/problem/P1450
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class NumberOfBuyWay {

    public static int maxn = 10001;
    public static int n;
    public static long[] dp = new long[maxn];
    public static int[] value = new int[5];
    public static int[] cnts = new int[5];
    public static int target;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken() != StreamTokenizer.TT_EOF){
            for(int i = 1; i <= 4; i++){
                value[i] = (int) in.nval;
                in.nextToken();
            }
            n = (int) in.nval;
            in.nextToken();
            build();
            for(int i = 0; i < n; i++){
                for(int j = 1; j <= 4; j++){
                    cnts[j] = (int) in.nval;
                    in.nextToken();
                }
                target = (int) in.nval;
                in.nextToken();
            }

        }
        out.flush();
        out.close();
        br.close();
    }

    public static void build(){
        for(int i = 1; i <= 4; i++){
            for(int j = value[i]; j < maxn; j++){
                dp[j] += dp[j-value[i]];
            }
        }
    }

}
