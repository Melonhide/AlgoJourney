package class068;

// 有效涂色问题
// 给定n、m两个参数
// 一共有n个格子，每个格子可以涂上一种颜色，颜色在m种里选
// 当涂满n个格子，并且m种颜色都使用了，叫一种有效方法
// 求一共有多少种有效的涂色方法
// 1 <= n, m <= 5000
// 结果比较大请 % 1000000007 之后返回
// 对数器验证

import java.util.Arrays;

public class FillCellsUserAllColorsWays {
    public static int ways1(int n, int m){
        return process(new int[n], new boolean[m+1], 0, n, m);
    }

    public static int process(int[] path, boolean[] used, int i, int n, int m){
        if(i == n){
            Arrays.fill(used, false);
            int colors = 0;
            for(int c: path){
                if(!used[c]){
                    used[c] = true;
                    colors++;
                }
            }
            return colors == m? 1:0;
        }else{
            int ans = 0;
            for(int j=1; j<=m; j++){
                path[i] = j;
                ans += process(path, used, i+1, n,m);
            }
            return ans;
        }
    }


    public static int maxn = 5001;
    public static int[][] dp = new int[maxn][maxn];
    public static int mod = 1000000007;
    public static int ways2(int n, int m){


        return f(n, m, n, m);
    }

    public static int f(int n, int m, int i, int j){
        if(i == 1){
            return j == 1? m:0;
        }
        //不用新颜色 + 用新颜色
        int p1 =(int) (((long) f(n,m,i-1, j))*j%mod) ;
        int p2 = (int)(((long) f(n,m,i-1, j-1)*(m-j+1))%mod);

        return (int) (((long) p1+p2)%mod);
    }


    public static void main(String[] args){
        int N = 9;
        int M = 9;
        System.out.println("功能测试开始");
        for (int n = 1; n <= N; n++) {
            for (int m = 1; m <= M; m++) {
                int ans1 = ways1(n, m);
                int ans2 = ways2(n, m);
                if (ans1 != ans2) {
                    System.out.println("出错了!");
                }
            }
        }
        System.out.println("功能测试结束");


    }
}
