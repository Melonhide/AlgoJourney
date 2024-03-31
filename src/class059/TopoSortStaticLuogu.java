package class059;

import java.io.*;

public class TopoSortStaticLuogu {
    public static int m, n, maxm=100001, maxn=100001;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            for(int i = 0, f, t; i < m; i++){
                in.nextToken();
                f = (int) in.nval;
                in.nextToken();
                t = (int) in.nval;

            }
        }


    }
}
