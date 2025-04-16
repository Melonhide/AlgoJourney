package class108;

import java.io.*;

// 树状数组单点增加、范围查询模版
// 测试链接 : https://www.luogu.com.cn/problem/P3374

public class IndexTreeSingleAddIntervalQuery {
    public static int maxn = 500002;
    public static int n;
    public static int m;
    public static int[] tree = new int[maxn];

    public static void add(int i, int v){
        while(i<=n){
            tree[i]+=v;
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

    public static int range(int l, int r){
        return sum(r)-sum(l-1);
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
            for(int i = 1, v; i <= n; i++){
                v = (int) in.nval;
                add(i, v);
                in.nextToken();
            }
            for(int i = 0, op, a, b; i < m; i++){
                op = (int) in.nval;
                in.nextToken();
                a = (int) in.nval;
                in.nextToken();
                b = (int) in.nval;
                in.nextToken();
                if(op == 1){
                    add(a, b);
                }else {
                    out.println(range(a, b));
                }
            }
        }
        out.flush();
        out.close();
        br.close();
    }
}
