package class108;


// 树状数组范围增加、范围查询模版
// 测试链接 : https://www.luogu.com.cn/problem/P3372


import java.io.*;

public class IndexTreeIntervalAddIntervalQuery {
    public static int maxn = 100002;
    public static int n;
    public static int m;
    public static long[] t1 = new long[maxn];
    public static long[] t2 = new long[maxn];

    public static void add(long[] tree, int i, long v){
        while(i<=n){
            tree[i] += v;
            i += rightone(i);
        }
    }

    public static void add(int l, int r, long v){
        add(t1, l, v);
        add(t1, r+1, -v);
        add(t2, l, (l-1)*v);
        add(t2, r+1, -r*v);
    }

    public static long sum1(int i){
        long ans = 0;
        while(i>0){
            ans += t1[i];
            i -= rightone(i);
        }
        return ans;
    }

    public static long sum2(int i){
        long ans = 0;
        while(i>0){
            ans += t2[i];
            i -= rightone(i);
        }
        return ans;
    }

    public static long range(int l, int r){
        return r*sum1(r)-sum2(r) - ((l-1)*sum1(l-1)-sum2(l-1));
    }

    public static int rightone(int i){
        return i&(-i);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken() != StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            in.nextToken();
            long v;
            for(int i = 1; i <= n; i++){
                v = (long) in.nval;
                in.nextToken();
                add(i, i, v);
            }

            for(int i = 0, op, a, b; i < m; i++){
                op = (int) in.nval;
                in.nextToken();
                a = (int) in.nval;
                in.nextToken();
                b = (int) in.nval;
                in.nextToken();
                if(op == 1){
                    v = (long) in.nval;
                    in.nextToken();
                    add(a, b, v);
                }else{
                    out.println(range(a, b));
                }
            }

        }
        out.flush();
        out.close();
        br.close();
    }

}
