package class061;

// Kruskal算法模版（洛谷）
// 静态空间实现
// 测试链接 : https://www.luogu.com.cn/problem/P3366
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下所有代码，把主类名改成Main，可以直接通过

import java.io.*;
import java.util.Arrays;

public class Kruskal  {
    public static int maxn = 5001;
    public static int maxm = 200001;
    public static int[] nodes = new int[maxn];
    public static int[][] edges = new int[maxm][3];
    public static int m, n;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken() != StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            in.nextToken();
            for(int i = 0; i < m; i++){
                edges[i][0] = (int) in.nval;
                in.nextToken();
                edges[i][1] = (int) in.nval;
                in.nextToken();
                edges[i][2] = (int) in.nval;
                in.nextToken();
            }
            int ans = compute();
            out.println(ans ==-1? "orz":ans);
        }
        out.flush();
        out.close();
        br.close();

    }

    public static void build(){
        for(int i = 1; i<=n; i++){
            nodes[i] = i;
        }
    }

    public static int find(int a){
        if(a==nodes[a]){
            return a;
        }
        nodes[a] = find(nodes[a]);
        return nodes[a];
    }

    public static boolean union(int a, int b){

        int fa = find(a);
        int fb = find(b);
        if(fa == fb){
            return false;
        }

        nodes[fa] = fb;
        return true;
    }

    public static int compute(){
        build();
        Arrays.sort(edges, 0, m, (int[] a, int[] b)->(a[2]-b[2]));
        int ans = 0;
        int cnt = 0;
        for(int i = 0; i < m; i++){
            if(union(edges[i][0], edges[i][1])){
                cnt++;
                ans+= edges[i][2];
            }

            if(cnt == n-1){
                break;
            }
        }

        return cnt == n-1? ans:-1;
    }
}
