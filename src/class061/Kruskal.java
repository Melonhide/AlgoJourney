package class061;

import java.io.*;
import java.util.Arrays;

public class Kruskal  {
    public static int n, m;
    public static int maxn = 5001, maxm = 200001;

    public static int[] father = new int[maxn];
    public static int[][] edges = new int[maxm][3];
    public static void build(){
        for(int i = 1; i <= n; i++){
            father[i] = i;
        }
    }

    public static int find(int a){
        if(father[a]!=a){
            father[a] = find(father[a]);
        }
        return father[a];
    }

    public static boolean isSameSet(int a, int b){
        return find(a) == find(b);
    }

    public static void union(int a, int b){
        int fa = find(a);
        int fb = find(b);
        if(fa!=fb){
            father[fa] = fb;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            build();
            for(int i = 0; i < m; i++){
                in.nextToken();
                edges[i][0] = (int) in.nval;
                in.nextToken();
                edges[i][1] = (int) in.nval;
                in.nextToken();
                edges[i][2] = (int) in.nval;
            }

            Arrays.sort(edges, 0, m, (a,b) -> (a[2]-b[2]));
            int ans = 0;
            int edgecnt = 0;

            for(int i = 0; i < m; i++){
                if(!isSameSet(edges[i][0], edges[i][1])){
                    union(edges[i][0], edges[i][1]);
                    edgecnt++;
                    ans += edges[i][2];
                }
            }
            out.println(edgecnt == n-1? ans:"orz");

        }

        out.flush();
        out.close();
        br.close();
    }
}
