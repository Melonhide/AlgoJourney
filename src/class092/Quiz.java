package class092;

import java.io.*;
import java.util.Arrays;

// 知识竞赛
// 最近部门要选两个员工去参加一个需要合作的知识竞赛，
// 每个员工均有一个推理能力值ai，以及一个阅读能力值bi
// 如果选择第i个人和第j个人去参加竞赛，
// 两人在推理方面的能力为X = (ai + aj)/2
// 两人在阅读方面的能力为Y = (bi + bj)/2
// 现在需要最大化他们表现较差一方面的能力
// 即让min(X,Y) 尽可能大，问这个值最大是多少
// 测试链接 : https://www.nowcoder.com/practice/2a9089ea7e5b474fa8f688eae76bc050
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class Quiz {
    public static int n;
    public static int maxn = 200001;
    public static int[][] nums = new int[maxn][2];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            for(int i = 0; i < n; i++){
                nums[i][0] = (int) in.nval;
                in.nextToken();
                nums[i][1] = (int) in.nval;
                in.nextToken();
            }
            out.println((double) compute()/2);
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(){
        Arrays.sort(nums, 0, n, (int[] a, int[] b)->(Math.abs(a[0]-a[1]) - Math.abs(b[0]-b[1])));
        int max0 = nums[0][0];
        int max1 = nums[0][1];
        int ans = 0;
        for(int i = 1, cur; i < n; i++){
            cur = nums[i][0]<nums[i][1]? nums[i][0]+max0:nums[i][1]+max1;
            ans = Math.max(ans, cur);
            max0 = Math.max(max0, nums[i][0]);
            max1 = Math.max(max1, nums[i][1]);
        }
        return ans;
    }
}
