package class096;


import java.io.*;

// E&D游戏
// 桌子上有2n堆石子，编号为1、2、3...2n
// 其中1、2为一组；3、4为一组；5、6为一组...2n-1、2n为一组
// 每组可以进行分割操作：
// 任取一堆石子，将其移走，然后分割同一组的另一堆石子
// 从中取出若干个石子放在被移走的位置，组成新的一堆
// 操作完成后，组内每堆的石子数必须保证大于0
// 显然，被分割的一堆的石子数至少要为2
// 两个人轮流进行分割操作，如果轮到某人进行操作时，所有堆的石子数均为1，判此人输掉比赛
// 返回先手能不能获胜
// 测试链接 : https://www.luogu.com.cn/problem/P2148
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class EDGame2 {
    public static int t;
    public static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken() != StreamTokenizer.TT_EOF){
            t = (int) in.nval;
            in.nextToken();
            for(int j = 0; j < t; j++){
                n = (int) in.nval;
                in.nextToken();
                int ans = 0;
                for(int i = 0, a, b; i < n; i+=2){
                    a = (int) in.nval;
                    in.nextToken();
                    b = (int) in.nval;
                    in.nextToken();

                    ans ^= lowZero((a-1)|(b-1));
                }
                out.println(ans == 0? "NO":"YES");
            }
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int lowZero(int cur){
        int ans = 0;
        while((cur&1) != 0){
            ans++;
            cur >>>= 1;
        }
        return ans;
    }
}
