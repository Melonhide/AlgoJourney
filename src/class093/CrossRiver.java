package class093;


import java.io.*;
import java.util.Arrays;

// 过河问题
// 一共n人出游，他们走到一条河的西岸，想要过河到东岸
// 每个人都有一个渡河时间ti，西岸有一条船，一次最多乘坐两人
// 如果船上有一个人，划到对岸的时间，等于这个人的渡河时间
// 如果船上有两个人，划到对岸的时间，等于两个人的渡河时间的最大值
// 返回最少要花费多少时间，才能使所有人都过河
// 测试链接 : https://www.luogu.com.cn/problem/P1809
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class CrossRiver {
    public static int maxn = 100001;
    public static int n;
    public static int[] nums = new int[maxn];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            for(int i = 0; i < n; i++){
                nums[i] = (int) in.nval;
                in.nextToken();
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(){
        Arrays.sort(nums, 0, n);
        int[] dp = new int[n];
        if(n>=1){
            dp[0] = nums[0];
        }
        if(n>=2){
            dp[1] = nums[1];
        }
        if(n>=3){
            dp[2] = nums[2]+nums[0]+nums[1];
        }

        for(int i = 3; i < n; i++){
            dp[i] = Math.min(nums[i]+nums[0]+dp[i-1], 2*nums[1]+nums[i]+nums[0]+dp[i-2]);
        }

        return dp[n-1];
    }

}


