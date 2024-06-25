package class079;

import java.io.*;
import java.util.Arrays;

// 选课
// 在大学里每个学生，为了达到一定的学分，必须从很多课程里选择一些课程来学习
// 在课程里有些课程必须在某些课程之前学习，如高等数学总是在其它课程之前学习
// 现在有 N 门功课，每门课有个学分，每门课有一门或没有直接先修课
// 若课程 a 是课程 b 的先修课即只有学完了课程 a，才能学习课程 b
// 一个学生要从这些课程里选择 M 门课程学习
// 问他能获得的最大学分是多少
// 测试链接 : https://www.luogu.com.cn/problem/P2014
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class CourseSelection2 {
    public static int maxn = 301;

    public static int[] nums = new int[maxn];
    public static int m, n, edgecnt, dfncnt;
    public static int[] head = new int[maxn];
    public static int[] next = new int[maxn];
    public static int[] to = new int[maxn];

    public static int[] val = new int[maxn+1];
    public static int[] size = new int[maxn+1];

    public static void build(){
        edgecnt = 0;
        dfncnt = 0;
        Arrays.fill(head, 0, n+1, 0);
    }

    public static void addedge(int u, int v){
        next[edgecnt] = head[u];
        to[edgecnt] = v;
        head[u] = edgecnt++;
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            build();
            for(int i = 1; i <= n; i++){
                in.nextToken();
                addedge((int) in.nval, i);
                in.nextToken();
                nums[i] = (int) in.nval;
            }
            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(){

    }

    public static int f(){

    }
}
