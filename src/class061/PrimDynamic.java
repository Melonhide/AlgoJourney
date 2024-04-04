package class061;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class PrimDynamic {

    public static int m, n;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;

            ArrayList<ArrayList<int[]>> g = new ArrayList<>();
            PriorityQueue<int[]> heap = new PriorityQueue<>((a,b) -> (a[1]-b[1]));

            for(int i = 0; i <=n; i++){
                g.add(new ArrayList<>());
            }

            for(int i = 0, u, v, w; i < m; i++){
                in.nextToken();
                u = (int) in.nval;
                in.nextToken();
                v = (int) in.nval;
                in.nextToken();
                w = (int) in.nval;
                g.get(u).add(new int[]{v,w});
                g.get(v).add(new int[]{u,w});
            }

//            for(int[] edge: g.get(1)){
//                heap.add(edge);
//            }

            heap.addAll(g.get(1));

            boolean[] set = new boolean[n+1];
            set[1] = true;
            int nodecnt = 1;
            int ans = 0;
            while(!heap.isEmpty()){
                int[] curedge = heap.poll();
                if(!set[curedge[0]]){
                    set[curedge[0]] = true;
                    nodecnt++;
                    ans += curedge[1];
                    heap.addAll(g.get(curedge[0]));
                }
            }
            out.println(nodecnt == n? ans:"orz");
        }
        out.flush();
        out.close();
        br.close();
    }
}
