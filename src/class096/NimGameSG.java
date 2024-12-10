package class096;

import java.util.Arrays;

// 尼姆博弈(SG定理简单用法展示)
// 一共有 n 堆石头，两人轮流进行游戏
// 在每个玩家的回合中，玩家需要 选择任一 非空 石头堆，从中移除任意 非零 数量的石头
// 如果不能移除任意的石头，就输掉游戏
// 返回先手是否一定获胜
// 对数器验证
public class NimGameSG {
    public static String nim1(int[] arr){
        int eor = 0;
        for(int num:arr){
            eor ^= num;
        }

        return eor == 0? "Last":"First";
    }

    public static String nim2(int[] arr){
        int max = 0;
        for(int a:arr){
            max=Math.max(max, a);
        }
        int[] sg = new int[max+1];
        boolean[] seen = new boolean[max+1];
        for(int i = 1; i <= max; i++){
            Arrays.fill(seen, false);
            for(int j = 0; j <= i; j++){
                seen[sg[j]] = true;
            }
            for(int x = 0; x<=max; x++){
                if(!seen[x]){
                    sg[i] = x;
                    break;
                }
            }
        }
        int eor = 0;
        for(int num:arr){
            eor ^= sg[num];
        }
        return eor == 0? "Last":"First";
    }

    public static int[] randomArray(int n, int v) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * v);
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 200;
        int V = 1000;
        int testTimes = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[] arr = randomArray(n, V);
            String ans1 = nim1(arr);
            String ans2 = nim2(arr);
            if (!ans1.equals(ans2)) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }

}
