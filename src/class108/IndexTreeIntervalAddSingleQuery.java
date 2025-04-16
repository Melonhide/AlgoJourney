package class108;


// 树状数组范围增加、单点查询模版
// 测试链接 : https://www.luogu.com.cn/problem/P3368

import java.io.*;

public class IndexTreeIntervalAddSingleQuery {
    public static int maxn = 500002;
    public static int[] tree = new int[maxn];
    public static int n;
    public static int m;

    public static void add(int i, int v){
        while(i<=n){
            tree[i] += v;
            i += rightone(i);
        }
    }

    public static int sum(int i){
        int ans = 0;
        while(i>0){
            ans += tree[i];
            i -= rightone(i);
        }
        return ans;
    }

    public static int rightone(int i){
        return i&(-i);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken() != StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            in.nextToken();
            for(int i = 1, prev = 0, v; i <= n; i++){
                v = (int) in.nval;
                in.nextToken();
                add(i, v-prev);
                prev = v;
            }

            for(int i = 0, op, a, b, v; i < m; i++){
                op = (int) in.nval;
                in.nextToken();
                if(op == 1){
                    a = (int) in.nval;
                    in.nextToken();
                    b = (int) in.nval;
                    in.nextToken();
                    v = (int) in.nval;
                    in.nextToken();
                    add(a, v);
                    add(b+1, -v);
                }else{
                    a = (int) in.nval;
                    in.nextToken();
                    out.println(sum(a));
                }
            }
        }
        out.flush();
        out.close();
        br.close();
    }
}
