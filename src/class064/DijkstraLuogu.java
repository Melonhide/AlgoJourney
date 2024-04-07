package class064;

import java.io.*;
import java.util.Arrays;

public class DijkstraLuogu {

    public static int n,m,s, cnt, heapsize;

    public static int maxm = 200001, maxn = 100001;

    public static int[] heap = new int[maxn];
    public static int[] where = new int[maxn];
    public static int[] distance = new int[maxn];
    public static int[] heads = new int[maxn];
    public static int[] next = new int[maxm];
    public static int[] to = new int[maxm];
    public static int[] weight = new int[maxm];


    public static void build(){
        heapsize = 0;
        cnt = 1;
        Arrays.fill(heads, 1, n+1, 0);
        Arrays.fill(where, 1, n+1, -1);
        Arrays.fill(distance, 1, n+1, Integer.MAX_VALUE);
    }

    public static void heap_insert(int i){
        while(distance[heap[i]] < distance[heap[(i-1)/2]]){
            swap(i, (i-1)/2);
            i = (i-1)/2;
        }
    }

    public static void heapify(int i){
        int left = i*2+1;
        while(left<heapsize){
            int best = left+1<heapsize && distance[heap[left+1]]<distance[heap[left]]? left+1:left;
            best = distance[heap[i]]<distance[heap[best]]? i:best;
            if(best==i){
                break;
            }

            swap(best, i);
            i = best;
            left = i*2+1;
        }
    }

    public static boolean isEmpty(){
        return heapsize == 0;
    }

    public static int pop(){
        int ans = heap[0];
        swap(0, --heapsize);
        heapify(0);
        where[ans] = -2;
        return ans;
    }

    public static void swap(int i, int j){
        int x = heap[i];
        int y = heap[j];
        where[y] = i;
        where[x] = j;

        int tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    public static void addEdge(int u, int v, int w){
        next[cnt] = heads[u];
        to[cnt] = v;
        weight[cnt] = w;
        heads[u] = cnt++;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            in.nextToken();
            s = (int) in.nval;

            build();

            for(int i = 0, u, v, w; i<m; i++){
                in.nextToken();
                u = (int)in.nval;
                in.nextToken();
                v = (int)in.nval;
                in.nextToken();
                w = (int)in.nval;
                addEdge(u,v,w);
            }

            dijkstra();

            for(int i = 1; i<n; i++){
                out.print(distance[i] + " ");
            }
            out.println(distance[n]);
        }
        out.flush();
        out.close();
        br.close();
    }

    public static void addOrUpdateOrIgnore(int v, int w){
        if(where[v] == -1){
            distance[v] = w;
            heap[heapsize] = v;
            where[v] = heapsize++;
            heap_insert(heapsize-1);
        }else if(where[v]>=0){
            distance[v] = Math.min(distance[v], w);
            heap_insert(where[v]);
        }
    }
    public static void dijkstra(){


        addOrUpdateOrIgnore(s, 0);
        while(!isEmpty()){
            int cur = pop();
            for(int e = heads[cur]; e!=0; e = next[e]){
                addOrUpdateOrIgnore(to[e], weight[e] + distance[cur]);
            }
        }
    }
}
