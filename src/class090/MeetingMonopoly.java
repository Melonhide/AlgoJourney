package class090;

import java.util.Arrays;

// 会议必须独占时间段的最大会议数量
// 给定若干会议的开始、结束时间
// 你参加某个会议的期间，不能参加其他会议
// 返回你能参加的最大会议数量
// 来自真实大厂笔试，没有在线测试，对数器验证
public class MeetingMonopoly {

    public static int maxMeeting1(int[][] meeting){
//        Arrays.sort(meeting, (int[] a, int[] b) -> (a[0]-b[0]));
//        return dp(meeting, 0, -1);
        return f(meeting, meeting.length, 0);
    }

    public static int dp(int[][] meeting, int i, int pre){
        if(i == meeting.length){
            return 0;
        }
        int ans = f(meeting, i+1, pre);
        if(pre<=meeting[i][0]){
            ans = Math.max(ans, 1+f(meeting, i+1, meeting[i][1]));
        }
        return ans;
    }

    public static int f(int[][] meeting, int n, int i) {
        int ans = 0;
        if (i == n) {
            for (int j = 0, cur = -1; j < n; j++) {
                if (cur <= meeting[j][0]) {
                    ans++;
                    cur = meeting[j][1];
                }
            }
        } else {
            for (int j = i; j < n; j++) {
                swap(meeting, i, j);
                ans = Math.max(ans, f(meeting, n, i + 1));
                swap(meeting, i, j);
            }
        }
        return ans;
    }

    public static void swap(int[][] meeting, int i, int j) {
        int[] tmp = meeting[i];
        meeting[i] = meeting[j];
        meeting[j] = tmp;
    }

    public static int maxMeeting2(int[][] meeting){
        Arrays.sort(meeting, (int[] a, int[] b) -> (a[1]-b[1]));
        int ans = 0;
        for(int i= 0, curend = -1; i < meeting.length; i++){
            if(curend<=meeting[i][0]){
                ans++;
                curend = meeting[i][1];
            }
        }
        return ans;
    }

    public static int[][] randomMeeting(int n, int m) {
        int[][] ans = new int[n][2];
        for (int i = 0, a, b; i < n; i++) {
            a = (int) (Math.random() * m);
            b = (int) (Math.random() * m);
            if (a == b) {
                ans[i][0] = a;
                ans[i][1] = a + 1;
            } else {
                ans[i][0] = Math.min(a, b);
                ans[i][1] = Math.max(a, b);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int N = 10;
        int M = 12;
        int testTimes = 2000;
        System.out.println("测试开始");
        for (int i = 1; i <= testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[][] meeting = randomMeeting(n, M);
            int ans1 = maxMeeting1(meeting);
            int ans2 = maxMeeting2(meeting);
            if (ans1 != ans2) {
                // 如果出错了
                // 可以增加打印行为找到一组出错的例子
                // 然后去debug
                System.out.println("出错了！");
            }
            if (i % 100 == 0) {
                System.out.println("测试到第" + i + "组");
            }
        }
        System.out.println("测试结束");
    }
}
