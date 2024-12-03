package class095;

// 巴什博弈(Bash Game)
// 一共有n颗石子，两个人轮流拿，每次可以拿1~m颗石子
// 拿到最后一颗石子的人获胜，根据n、m返回谁赢
public class BashGame {

    public static int MAXN = 1001;

    public static String[][] dp = new String[MAXN][MAXN];
    public static String bashGame1(int n, int m){
        if(n == 0){
            return "Last";
        }

        if(dp[n][m] != null){
            return dp[n][m];
        }
        String ans = "Last";
        for(int i = 1; i <= m; i++){
            if(bashGame1(n-i, m).equals("Last")){
                ans = "First";
                break;
            }
        }
        dp[n][m] = ans;
        return dp[n][m];
    }
    public static String bashGame2(int n, int m){
        return n%(m+1) == 0? "Last":"First";
    }
    public static void main(String[] args) {
        int V = 500; // 需要比MAXN小
        int testTimes = 5000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * V);
            int m = (int) (Math.random() * V) + 1;
            String ans1 = bashGame1(n, m);
            String ans2 = bashGame2(n, m);
            if (!ans1.equals(ans2)) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }
}
