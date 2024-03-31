package class059;

import java.util.ArrayList;
import java.util.Arrays;

public class CreateGraph {
    public static void main(String[] args) {
        // 理解了带权图的建立过程，也就理解了不带权图
        // 点的编号为1...n
        // 例子1自己画一下图，有向带权图，然后打印结果
        int n1 = 4;
        int[][] edges1 = { { 1, 3, 6 }, { 4, 3, 4 }, { 2, 4, 2 }, { 1, 2, 7 }, { 2, 3, 5 }, { 3, 1, 1 } };
        build(n1);
        directGraph(edges1);
        traversal(n1);
        System.out.println("==============================");
        // 例子2自己画一下图，无向带权图，然后打印结果
        int n2 = 5;
        int[][] edges2 = { { 3, 5, 4 }, { 4, 1, 1 }, { 3, 4, 2 }, { 5, 2, 4 }, { 2, 3, 7 }, { 1, 5, 5 }, { 4, 2, 6 } };
        build(n2);
        undirectGraph(edges2);
        traversal(n2);
    }

    //点的数量
    public static int maxn = 11;

    //边的数量
    public static int maxm = 21;

    //邻接矩阵
    public static int[][] graph1 = new int[maxn][maxn];


    //邻接表
    // 不带权
//    public ArrayList<ArrayList<Integer>> graph2 = new ArrayList<>();

    //带权
    public static ArrayList<ArrayList<int[]>> graph2 = new ArrayList<>();


    //链式前向星
    public static int cnt = 1;
    public static int[] head = new int[maxn];
    public static int[] next = new int[maxm];
    public static int[] to = new int[maxm];
    public static int[] weight = new int[maxm];



    public static void build(int n) {
        // 邻接矩阵清空
        for(int i = 0; i < n; i++){
            for(int j = 0; j<n; j++){
                graph1[i][j] = 0;
            }
        }

        //邻接表清空
        graph2.clear();
        for(int i = 0; i<n+1; i++){
            graph2.add(new ArrayList<>());
        }

        // 链式前向星清空
        cnt = 1;
        Arrays.fill(head, 1, n+1, 0);

    }

    // 链式前向星加边
    public static void addEdge(int u, int v, int w) {
        next[cnt] = head[u];
        to[cnt] = v;
        weight[cnt] = w;
        head[u] = cnt++;
    }

    // 三种方式建立有向图带权图
    public static void directGraph(int[][] edges) {
        //邻接矩阵
        for(int[] edge: edges){
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            graph1[from][to] = weight;
        }


        //邻接表
        for(int[] edge: edges){
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            graph2.get(from).add(new int[]{to, weight});
        }


        //链式前向星
        for(int[] edge: edges){
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            addEdge(from, to, weight);
        }

    }

    // 三种方式建立无向图带权图
    public static void undirectGraph(int[][] edges) {
        //邻接矩阵
        for(int[] edge: edges){
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            graph1[from][to] = weight;
            graph1[to][from] = weight;
        }


        //邻接表
        for(int[] edge: edges){
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            graph2.get(from).add(new int[]{to, weight});
            graph2.get(to).add(new int[]{from, weight});
        }


        //链式前向星
        for(int[] edge: edges){
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            addEdge(from, to , weight);
            addEdge(to, from ,weight);
        }

    }

    public static void traversal(int n) {
        System.out.println("邻接矩阵遍历 :");
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=n; j++){
                System.out.print(graph1[i][j] + " ");
            }
            System.out.println();
        }



        System.out.println("邻接表遍历 :");
        for (int i = 1; i <= n; i++) {
            System.out.print(i + "(邻居、边权) : ");
            for (int[] edge : graph2.get(i)) {
                System.out.print("(" + edge[0] + "," + edge[1] + ") ");
            }
            System.out.println();
        }

        System.out.println("链式前向星 :");
        for (int i = 1; i <= n; i++) {
            System.out.print(i + "(邻居、边权) : ");
            for (int e1 = head[i]; e1>0; e1 = next[e1]) {
                System.out.print("(" + to[e1]+ "," + weight[e1] + ")  ");
            }
            System.out.println();
        }
    }
}
