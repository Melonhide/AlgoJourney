package class068;

import java.util.ArrayList;
import java.util.List;

public class MinimumDeleteBecomeSubstring {

    public static int minDelete1(String s1, String s2){
        List<String> list = new ArrayList<>();
        f(s1.toCharArray(), 0 , "", list);

        list.sort((a,b)->b.length()-a.length());
        for(String str: list){
            if(s2.indexOf(str)!=-1){
                return s1.length()-str.length();
            }
        }
        return s1.length();
    }

    public static void f(char[] s1, int i, String path, List<String> list){
        if(i == s1.length){
            list.add(path);
        }else{
            f(s1, i+1, path, list);
            f(s1, i+1, path+s1[i], list);
        }
    }

    public static int minDelete2(String str1, String str2){
        int m = str1.length();
        int n = str2.length();
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int[][] dp = new int[m+1][n+1];

        for(int i = 1; i <= m; i++){
            dp[i][0] = i;
            for(int j = 1; j<= n; j++){
                if(s1[i-1] == s2[j-1]){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = 1+dp[i-1][j];
                }
            }
        }
        int ans = dp[m][n];
        for(int j = 0; j<=n; j++){
            ans = Math.min(dp[m][j], ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        // 测试的数据量比较小
        // 那是因为数据量大了，暴力方法过不了
        // 但是这个数据量足够说明正式方法是正确的
        int n = 12;
        int v = 3;
        int testTime = 20000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len1 = (int) (Math.random() * n) + 1;
            int len2 = (int) (Math.random() * n) + 1;
            String s1 = randomString(len1, v);
            String s2 = randomString(len2, v);
            int ans1 = minDelete1(s1, s2);
            int ans2 = minDelete2(s1, s2);
            if (ans1 != ans2) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }

    public static String randomString(int n, int v) {
        char[] ans = new char[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (char) ('a' + (int) (Math.random() * v));
        }
        return String.valueOf(ans);
    }
}
