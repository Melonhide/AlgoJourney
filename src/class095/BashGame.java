package class095;

import java.io.IOException;

// 巴什博弈(Bash Game)
// 一共有n颗石子，两个人轮流拿，每次可以拿1~m颗石子
// 拿到最后一颗石子的人获胜，根据n、m返回谁赢
public class BashGame {
    public static String bashGame1(int n, int m){

    }
    public static String bashGame2(int n, int m){
        return n%(m+1) == 0? "后手":"先手";
    }
    public static void main(String[] args) throws IOException{

    }
}
