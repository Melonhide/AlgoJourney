package class059;

import java.util.Arrays;

public class ForwardStar {

    //点的数量
    public static int maxn = 11;

    //边的数量
    public static int maxm = 21;

    public static int[] head = new int[maxn];
    public static int[] next = new int[maxm];
    public static int[] to = new int[maxm];
    public static int[] weight = new int[maxm];
    public static int cnt;

    public static void build(int n){
        cnt = 1;
        Arrays.fill(head, 1, n+1, 0);
    }

    public static void addEdge(int u, int v, int w){
        next[cnt] = head[u];
        head[u] = cnt;
        to[cnt] = v;
        weight[cnt] = w;
        cnt++;
    }

    public static void directGraph(int[][] edges){
        for(int[] edge: edges){
            addEdge(edge[0], edge[1], edge[2]);
        }
    }


    public static void undirectGraph(int[][] edges){
        for(int[] edge: edges){
            addEdge(edge[0], edge[1], edge[2]);
            addEdge(edge[1], edge[0], edge[2]);
        }
    }

    public static void traversal(int n){
        for(int i = 1, cur; i <= n; i++){
            System.out.print(i+"(next, weight) : ");
            cur = head[i];
            while(cur != 0){
                System.out.print("("+to[cur]+","+weight[cur]+") ");
                cur = next[cur];
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n1 = 4;
        int[][] edges1 = { { 1, 3, 6 }, { 4, 3, 4 }, { 2, 4, 2 }, { 1, 2, 7 }, { 2, 3, 5 }, { 3, 1, 1 } };
        build(n1);
        directGraph(edges1);
        traversal(n1);
        System.out.println("==============================");
        int n2 = 5;
        int[][] edges2 = { { 3, 5, 4 }, { 4, 1, 1 }, { 3, 4, 2 }, { 5, 2, 4 }, { 2, 3, 7 }, { 1, 5, 5 }, { 4, 2, 6 } };
        build(n2);
        undirectGraph(edges2);
        traversal(n2);
    }
}
