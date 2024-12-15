package class096;

import java.io.*;
import java.util.Arrays;

// 分裂游戏
// 一共有n个瓶子，编号为0 ~ n-1，第i瓶里装有nums[i]个糖豆，每个糖豆认为无差别
// 有两个玩家轮流取糖豆，每一轮的玩家必须选i、j、k三个编号，并且满足i < j <= k
// 当前玩家从i号瓶中拿出一颗糖豆，分裂成两颗糖豆，并且往j、k瓶子中各放入一颗，分裂的糖豆继续无差别
// 要求i号瓶一定要有糖豆，如果j == k，那么相当于从i号瓶中拿出一颗，向另一个瓶子放入了两颗
// 如果轮到某个玩家发现所有糖豆都在n-1号瓶里，导致无法行动，那么该玩家输掉比赛
// 先手希望知道，第一步如何行动可以保证自己获胜，要求返回字典序最小的行动
// 第一步从0号瓶拿出一颗糖豆，并且往2、3号瓶中各放入一颗，可以确保最终自己获胜
// 第一步从0号瓶拿出一颗糖豆，并且往11、13号瓶中各放入一颗，也可以确保自己获胜
// 本题要求每个瓶子的编号看做是一个字符的情况下，最小的字典序，所以返回"0 2 3"
// 如果先手怎么行动都无法获胜，返回"-1 -1 -1"
// 先手还希望知道自己有多少种第一步取糖的行动，可以确保自己获胜，返回方法数
// 测试链接 : https://www.luogu.com.cn/problem/P3185
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class SplitGame {

    public static int t, n;
    public static int[] sg = new int[22];
    public static int[] nums = new int[22];
    public static boolean[] seen = new boolean[101];
    public static void build(){
        for(int i = 1; i <= 21; i++){
            Arrays.fill(seen, false);
            for(int j = i-1; j >= 0; j--){
                for(int k = j; k>=0; k--){
                    seen[sg[j]^sg[k]] = true;
                }
            }

            for(int s = 0; s < 101; s++){
                if(!seen[s]){
                    sg[i] = s;
                    break;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken() != StreamTokenizer.TT_EOF){
            t = (int) in.nval;
            in.nextToken();
            build();
            for(int j = 0; j < t; j++){
                n = (int) in.nval;
                in.nextToken();
                for(int i = n-1; i >=0; i--){
                    nums[i] = (int) in.nval;
                    in.nextToken();
                }
                out.println(compute());
            }
        }
        out.flush();
        out.close();
        br.close();
    }

    public static String compute(){
        int eor = 0;
        for(int i = n-1; i >= 0; i--){
            if(nums[i]%2!=0){
                eor ^= sg[i];
            }
        }

        if(eor == 0){
            return "-1 -1 -1\n0";
        }

        int cnt = 0, a = -1, b = -1, c = -1, pos;
        for(int i = n-1; i >= 1; i--){
            if(nums[i] > 0){
                for(int j = i-1; j >= 0; j--){
                    for(int k = j; k>=0; k--){
                        pos = eor^sg[i]^sg[j]^sg[k];
                        if(pos == 0){
                            cnt++;
                            if(a==-1){
                                a = i;
                                b = j;
                                c = k;
                            }
                        }
                    }
                }
            }
        }
        a = n-1-a;
        b = n-1-b;
        c = n-1-c;
        return String.valueOf(a)+" "+String.valueOf(b)+" "+String.valueOf(c)+"\n"+String.valueOf(cnt);
    }
}
