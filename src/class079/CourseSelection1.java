package class079;


import java.io.*;
import java.util.ArrayList;

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
public class CourseSelection1 {
    public static int n, m;
    public static int maxn = 301;
    public static ArrayList<ArrayList<Integer>> graph;
    public static int[] nums = new int[maxn];
    public static int[][][] dp = new int[maxn][][];

    static {
        graph = new ArrayList<>();
        for(int i = 0; i < maxn; i++){
            graph.add(new ArrayList<>());
        }
    }

    public static void build(int n){
        for(int i = 0; i<=n; i++){
            graph.get(i).clear();
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval+1;
            build(n);
            for(int i = 1, pre, score; i <= n; i++){
                in.nextToken();
                pre = (int) in.nval;
                graph.get(pre).add(i);

                in.nextToken();
                score = (int) in.nval;
                nums[i] = score;
            }
            out.println(compute());

        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(){
        for(int i = 0; i <= n; i++){
            dp[i] = new int[graph.get(i).size()+1][m+1];
        }

        for(int i = 0; i <= n; i++){
            for(int j = 0; j < dp[i].length; j++){
                for(int k = 0; k <= m ; k++){
                    dp[i][j][k] = -1;
                }
            }
        }

        return f(0,graph.get(0).size(), m);
    }

    public static int f(int i, int j, int k){
        if(dp[i][j][k] !=-1){
            return dp[i][j][k];
        }

        if(k == 0){
            dp[i][j][k] = 0;
            return dp[i][j][k];
        }

        if(j == 0 || k == 1){
            dp[i][j][k] = nums[i];
            return dp[i][j][k];
        }

        int ans = f(i, j-1, k);
        int next = graph.get(i).get(j-1);
        for(int s = 1; s<k; s++){
            ans = Math.max(ans,f(i, j-1, k-s)+f(next, graph.get(next).size(), s));
        }

        dp[i][j][k] = ans;
        return ans;
    }
}
