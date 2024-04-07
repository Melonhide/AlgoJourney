package class061;

import java.io.*;
import java.util.Arrays;

public class PrimStatic {
    public static int maxn = 5001, maxm = 400001;
    public static int m,n, cnt, heapsize, nodecnt;

    public static int[] heads = new int[maxn];
    public static int[] next = new int[maxm];
    public static int[] to = new int[maxm];
    public static int[] weights = new int[maxm];

    public static int[][] heap = new int[maxn][2];
    public static int[] where = new int[maxn];


    public static void heapify(int i){
        int left = i*2+1;
        while(left<heapsize){
            int best = left+1<heapsize && heap[left+1][1]<heap[left][1]? left+1:left;
            best = heap[i][1]<heap[best][1]? i:best;
            if(best == i){
                break;
            }

            swap(best, i);
            i = best;
            left = i*2+1;
        }
    }
    public static void heap_insert(int i){
        while(heap[i][1]<heap[(i-1)/2][1]){
            swap(i, (i-1)/2);
            i = (i-1)/2;
        }
    }

    public static void swap(int i, int j){
        int inode = heap[i][0];
        int jnode = heap[j][0];
        where[inode] = j;
        where[jnode] = i;
        int[] temp = heap[i];
        heap[i]  = heap[j];
        heap[j] = temp;
    }

    public static boolean isEmpty(){
        return heapsize == 0;
    }

    public static int u, w;
    public static void pop(){
        u = heap[0][0];
        w = heap[0][1];

        swap(0, --heapsize);
        heapify(0);
        where[u] = -2;
        nodecnt++;
    }

    public static void addOrUpdateOrIgnore(int e1){
        int v = to[e1];
        int w = weights[e1];
        if(where[v] == -1){
            heap[heapsize][0] = v;
            heap[heapsize][1] = w;
            where[v] = heapsize++;
            heap_insert(where[v]);
        }else if(where[v]>=0){
            heap[where[v]][1] = Math.min(w, heap[where[v]][1]);
            heap_insert(where[v]);
        }
    }



    public static void addEdge(int u, int v, int w){
        next[cnt] = heads[u];
        to[cnt] = v;
        weights[cnt] = w;
        heads[u] = cnt++;
    }

    public static void build(){
        cnt = 1;
        Arrays.fill(heads, 1, n+1, 0);
        heapsize = 0;
        Arrays.fill(where, 1, n+1, -1);
        nodecnt = 0;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            build();
            for(int i = 0, u, v, w; i<m; i++){
                in.nextToken();
                u = (int) in.nval;
                in.nextToken();
                v = (int) in.nval;
                in.nextToken();
                w = (int) in.nval;
                addEdge(u, v, w);
                addEdge(v, u, w);
            }
            int ans = prim();
            out.println(nodecnt==n? ans:"orz");
        }

        out.flush();
        out.close();
        br.close();
    }

    public static int prim(){
        nodecnt = 1;
        where[1] = -2;
        for(int e = heads[1]; e>0; e = next[e]){
            addOrUpdateOrIgnore(e);
        }

        int ans = 0;
        while(!isEmpty()){
            pop();
            ans += w;
            for(int e = heads[u]; e>0; e=next[e]){
                addOrUpdateOrIgnore(e);
            }
        }

        return ans;
    }

}
