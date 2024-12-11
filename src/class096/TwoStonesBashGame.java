package class096;


import java.util.Arrays;

// 两堆石头的巴什博弈
// 有两堆石头，数量分别为a、b
// 两个人轮流拿，每次可以选择其中一堆石头，拿1~m颗
// 拿到最后一颗石子的人获胜，根据a、b、m返回谁赢
// 来自真实大厂笔试，没有在线测试，对数器验证
public class TwoStonesBashGame {

    public static int MAXN = 101;

    public static String[][][] dp = new String[MAXN][MAXN][MAXN];

    public static String win1(int a, int b, int m) {
        if(a == 0 && b == 0){
            return "Last";
        }
        if(dp[a][b][m] != null){
            return dp[a][b][m];
        }
        String ans = "Last";
        for(int i = 1; i <= m && a-i>=0; i++){
            if(win1(a-i, b, m).equals("Last")){
                ans = "First";
                break;
            }
        }
        if(!ans.equals("First")){
            for(int i = 1; i <= m && b-i>=0; i++){
                if(win1(a, b-i, m).equals("Last")){
                    ans = "First";
                    break;
                }
            }
        }
        dp[a][b][m] = ans;
        return ans;
    }
    public static String win2(int a, int b, int m){
        int max = Math.max(a,b);
        int[] sg = new int[max+1];
        boolean[] seen = new boolean[max+1];
        for(int i = 1; i <= max; i++){
            Arrays.fill(seen, false);
            for(int j=1; j<=m && i-j>=0; j++){
                seen[sg[i-j]] = true;
            }

            for(int s=0; s<=max; s++) {
                if (!seen[s]) {
                    sg[i] = s;
                    break;
                }
            }
        }

        return (sg[a]^sg[b]) == 0? "Last":"First";
    }

    public static String win3(int a, int b, int m){
        return a%(m+1) == b%(m+1)? "Last":"First";
    }

    public static void main(String[] args) {
        System.out.println("测试开始");
        for (int a = 0; a < MAXN; a++) {
            for (int b = 0; b < MAXN; b++) {
                for (int m = 1; m < MAXN; m++) {
                    String ans1 = win1(a, b, m);
                    String ans2 = win2(a, b, m);
                    String ans3 = win3(a, b, m);
                    if (!ans1.equals(ans2) || !ans1.equals(ans3)) {
                        System.out.println("出错了！");
                    }
                }
            }
        }
        System.out.println("测试结束");
    }
}
