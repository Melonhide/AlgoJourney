package class095;

import java.io.*;
import java.math.BigDecimal;

// 威佐夫博弈(Wythoff Game)
// 有两堆石子，数量任意，可以不同，游戏开始由两个人轮流取石子
// 游戏规定，每次有两种不同的取法
// 1) 在任意的一堆中取走任意多的石子
// 2) 可以在两堆中同时取走相同数量的石子
// 最后把石子全部取完者为胜者
// 现在给出初始的两堆石子的数目，返回先手能不能获胜
// 测试链接 : https://www.luogu.com.cn/problem/P2252
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class WythoffGame {
    public static BigDecimal rate = new BigDecimal("1.61803398874989484");
    public static int n,m;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken() != StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(){
        int min = Math.min(m,n);
        int max = Math.max(m,n);
        if(min != rate.multiply(new BigDecimal(max-min)).intValue()){
            return 1;
        }else{
            return 0;
        }
    }
}
