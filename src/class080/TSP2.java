package class080;


import java.io.*;

// 售货员的难题 - TSP问题
// 某乡有n个村庄(1<=n<=20)，有一个售货员，他要到各个村庄去售货
// 各村庄之间的路程s(1<=s<=1000)是已知的
// 且A村到B村的路程，与B到A的路大多不同(有向带权图)
// 为了提高效率，他从商店出发到每个村庄一次，然后返回商店所在的村，
// 假设商店所在的村庄为1
// 他不知道选择什么样的路线才能使所走的路程最短
// 请你帮他选择一条最短的路
// 测试链接 : https://www.luogu.com.cn/problem/P1171
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class TSP2 {
    public static int maxn = 19;
    public static int n;

    public static int[] go = new int[maxn];
    public static int[] back = new int[maxn];
    public static int[][] graph = new int[maxn][maxn];
    public static int[][] dp = new int[1<<maxn][maxn];

    public static void build(){
        for(int i = 0; i<(1<<n); i++){
            for(int j = 0; j<n; j++){
                dp[i][j] = -1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            n = (int) in.nval-1;
            build();
            in.nextToken();
            for(int i = 0; i <n; i++){
                in.nextToken();
                go[i] = (int) in.nval;
            }

            for(int i = 0; i<n; i++){
                in.nextToken();
                back[i] = (int) in.nval;
                for(int j = 0; j<n; j++){
                    in.nextToken();
                    graph[i][j] = (int) in.nval;
                }
            }

            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(){
        int ans = Integer.MAX_VALUE;

        for(int i = 0; i<n; i++){
            ans = Math.min(ans, go[i]+f(1<<i,i));
        }

        return ans;
    }

    public static int f(int s, int i){
        if(dp[s][i] != -1){
            return dp[s][i];
        }

        if(s == ((1<<n)-1)){
            dp[s][i] = back[i];
            return back[i];
        }

        int ans = Integer.MAX_VALUE;
        for(int j = 0; j < n; j++){
            if((s&(1<<j))==0){
                ans = Math.min(ans, graph[i][j] + f(s|(1<<j), j));
            }
        }
        dp[s][i] = ans;
        return dp[s][i];
    }
}
