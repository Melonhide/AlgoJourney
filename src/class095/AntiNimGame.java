package class095;

import java.io.*;

// 反尼姆博弈(反常游戏)
// 一共有n堆石头，两人轮流进行游戏
// 在每个玩家的回合中，玩家需要选择任何一个非空的石头堆，并从这堆石头中移除任意正数的石头数量
// 谁先拿走最后的石头就失败，返回最终谁会获胜
// 先手获胜，打印John
// 后手获胜，打印Brother
// 测试链接 : https://www.luogu.com.cn/problem/P4279
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class AntiNimGame {

    public static int t, n;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken() != StreamTokenizer.TT_EOF){
            t = (int) in.nval;
            in.nextToken();
            for(int i = 0; i < t; i++){
                n = (int) in.nval;
                in.nextToken();
                int eor = 0;
                int sum = 0;
                for(int j = 0; j < n; j++){
                    eor ^= (int) in.nval;
                    sum += (int) in.nval;
                    in.nextToken();
                }
                out.println(sum == n? sum%2==1? "Brother":"John" : eor != 0? "John":"Brother");
            }
        }
        out.flush();
        out.close();
        br.close();
    }
}
