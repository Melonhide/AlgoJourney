package class090;

import java.util.Arrays;

// 会议必须独占时间段的最大会议数量
// 给定若干会议的开始、结束时间
// 你参加某个会议的期间，不能参加其他会议
// 返回你能参加的最大会议数量
// 来自真实大厂笔试，没有在线测试，对数器验证
public class MeetingMonopoly {

    public static int maxMeeting2(int[][] meeting){
        Arrays.sort(meeting, (int[] a, int[] b) -> (a[1]-b[1]));


    }
}
