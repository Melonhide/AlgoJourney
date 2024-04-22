package class068;

public class MinimumDeleteBecomeSubstring {

    public static int minDelete1(String s1, String s2){
        return 0;
    }

    public static int minDelete2(String str1, String str2){
        return 0;
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
