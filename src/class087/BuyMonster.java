package class087;


// 贿赂怪兽
// 开始时你的能力是0，你的目标是从0号怪兽开始，通过所有的n只怪兽
// 如果你当前的能力小于i号怪兽的能力，则必须付出b[i]的钱贿赂这个怪兽
// 然后怪兽就会加入你，他的能力a[i]直接累加到你的能力上
// 如果你当前的能力大于等于i号怪兽的能力，你可以选择直接通过，且能力不会下降
// 但你依然可以选择贿赂这个怪兽，然后怪兽的能力直接累加到你的能力上
// 返回通过所有的怪兽，需要花的最小钱数
// 测试链接 : https://www.nowcoder.com/practice/736e12861f9746ab8ae064d4aae2d5a9
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过

import java.io.*;

public class BuyMonster {
    public static int n;
    public static int[] a;
    public static int[] b;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            a = new int[n+1];
            b = new int[n+1];

            for(int i = 0; i<n; i++){
                in.nextToken();
                a[i] = (int) in.nval;
                in.nextToken();
                b[i] = (int) in.nval;
            }
            out.println(compute2_enhance());
        }
        out.flush();
        out.close();
        br.close();
    }

    // 假设a[i]数值的范围很大，但是b[i]数值的范围不大
//    public static int compute1(){
//        pass;
//    }

    // 假设a[i]数值的范围不大，但是b[i]数值的范围很大
    public static int compute2(){
        int m = 0;
        for(int i:a){
            m += i;
        }

        int[][] dp = new int[n+1][m+1];
        for(int i = n-1; i>=0; i--){
            for(int j = 0; j<=m-a[i]; j++){
                dp[i][j] = b[i] + dp[i+1][j+a[i]];
                if(j>=a[i]){
                    dp[i][j] = Math.min(dp[i][j], dp[i+1][j]);
                }
            }
        }
        return dp[0][0];
    }

    public static int compute2_enhance(){
        int m = 0;
        for(int i:a){
            m += i;
        }

        int[] dp = new int[m+1];
        for(int i = n-1; i>=0; i--){
            for(int j = 0, tmp; j<=m-a[i]; j++){
                tmp = dp[j];
                dp[j] = b[i] + dp[j+a[i]];
                if(j>=a[i]){
                    dp[j] = Math.min(dp[j], tmp);
                }
            }
        }
        return dp[0];
    }
}
