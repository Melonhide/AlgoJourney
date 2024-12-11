package class096;

import java.util.Arrays;

// 三堆石头拿取斐波那契数博弈
// 有三堆石头，数量分别为a、b、c
// 两个人轮流拿，每次可以选择其中一堆石头，拿取斐波那契数的石头
// 拿到最后一颗石子的人获胜，根据a、b、c返回谁赢
// 来自真实大厂笔试，每堆石子的数量在10^5以内
// 没有在线测试，对数器验证
public class ThreeStonesPickFibonacci {
    public static int maxn = 201;
    public static int[] f = new int[] {1,2,3,5,8,13,21,34,55,89,144};
    public static String[][][] dp = new String[maxn][maxn][maxn];
    public static boolean[] seen = new boolean[maxn];
    public static int[] sg = new int[maxn];
    public static String win1(int a, int b, int c){
        if(a+b+c == 0){
            return "Last";
        }
        if(dp[a][b][c] != null){
            return dp[a][b][c];
        }
        String ans = "Last";
        for(int i = 0; i < f.length; i++){
            if(f[i]<=a){
                if(win1(a-f[i], b, c).equals("Last")){
                    ans = "First";
                    break;
                }
            }
            if(f[i]<=b){
                if(win1(a, b-f[i], c).equals("Last")){
                    ans = "First";
                    break;
                }
            }
            if(f[i]<=c){
                if(win1(a, b, c-f[i]).equals("Last")){
                    ans = "First";
                    break;
                }
            }

        }
        dp[a][b][c] = ans;
        return ans;
    }


    public static void build(){
        for(int i = 1; i < maxn; i++){
            Arrays.fill(seen, false);
            for(int j = 0; j < f.length && i-f[j]>=0; j++){
                seen[sg[i-f[j]]] = true;
            }

            for(int j = 0; j < maxn; j++){
                if(!seen[j]){
                    sg[i] = j;
                    break;
                }
            }
        }
    }
    public static String win2(int a, int b, int c){
        return (sg[a]^sg[b]^sg[c]) == 0? "Last":"First";
    }

    public static void main(String[] args) {
        build();
        System.out.println("测试开始");
        for (int a = 0; a < maxn; a++) {
            for (int b = 0; b < maxn; b++) {
                for (int c = 0; c < maxn; c++) {
                    String ans1 = win1(a, b, c);
                    String ans2 = win2(a, b, c);
                    if (!ans1.equals(ans2)) {
                        System.out.println("出错了！");
                    }
                }
            }
        }
        System.out.println("测试结束");

        // 试图找到简洁规律，想通过O(1)的过程就得到sg(x)
        // 于是打印200以内的sg值，开始观察
        // 刚开始有规律，但是在sg(138)之后开始发生异常波动
        // 这道题在考的时候，数据量并没有大到需要O(1)的过程才能通过
        // 那就用build方法计算sg值，不再找寻简洁规律
        // 考试时一切根据题目数据量来决定是否继续优化
        for (int i = 0; i < maxn; i++) {
            System.out.println("sg(" + i + ") : " + sg[i]);
        }
    }

}
