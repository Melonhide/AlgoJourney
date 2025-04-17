package class108;

// 二维数组上范围增加、范围查询，使用树状数组的模版(java)
// 测试链接 : https://www.luogu.com.cn/problem/P4514

import java.io.*;

public class TwoDimensionIntervalAddIntervalQuery1 {
    public static int maxn = 2050;
    public static int maxm = 2050;
    public static int m;
    public static int n;

    // d[i][j]
    public static int[][] t1 = new int[maxn][maxm];

    //d[i][j]*i
    public static int[][] t2 = new int[maxn][maxm];

    //d[i][j]*j
    public static int[][] t3 = new int[maxn][maxm];
    public static int[][] t4 = new int[maxn][maxm];

    //d[i][j]*i*j
    public static void add(int x, int y, int v){
        int v2 = x*v;
        int v3 = y*v;
        int v4 = x*y*v;
        for(int i = x; i<=n; i += rightone(i)){
            for(int j = y; j<=m; j += rightone(j)){
                t1[i][j] += v;
                t2[i][j] += v2;
                t3[i][j] += v3;
                t4[i][j] += v4;
            }
        }
    }

    public static int range(int a, int b, int c, int d){
        return sum(c, d) - sum(c, b-1) - sum(a-1, d) + sum(a-1, b-1);
    }
    public static int sum(int x, int y){
        int ans = 0;
        for(int i = x; i > 0; i-=rightone(i)){
            for(int j = y; j > 0; j-=rightone(j)){
                ans += (x+1)*(y+1)*t1[i][j] - (y+1)*t2[i][j] - (x+1)*t3[i][j] + t4[i][j];
            }
        }
        return ans;
    }

    public static int rightone(int i){
        return i&(-i);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        String op;
        int a, b, c, d, v;
        while(in.nextToken() != StreamTokenizer.TT_EOF){
            op = in.sval;
            if(op.equals("X")){
                in.nextToken();
                n = (int) in.nval;
                in.nextToken();
                m = (int) in.nval;
            }else if(op.equals("L")){
                in.nextToken();
                a = (int) in.nval;
                in.nextToken();
                b = (int) in.nval;
                in.nextToken();
                c = (int) in.nval;
                in.nextToken();
                d = (int) in.nval;
                in.nextToken();
                v = (int) in.nval;
                add(a, b, v);
                add(c+1,d+1, v);
                add(a, d+1, -v);
                add(c+1, b, -v);
            }else{
                in.nextToken();
                a = (int) in.nval;
                in.nextToken();
                b = (int) in.nval;
                in.nextToken();
                c = (int) in.nval;
                in.nextToken();
                d = (int) in.nval;
                System.out.println(range(a,b,c,d));
            }

        }
        br.close();
    }
}
