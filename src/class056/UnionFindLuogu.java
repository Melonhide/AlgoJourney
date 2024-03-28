package class056;

import java.io.*;

// 并查集模版(洛谷)
// 本实现用递归函数实现路径压缩，而且省掉了小挂大的优化，一般情况下可以省略
// 测试链接 : https://www.luogu.com.cn/problem/P3367
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过

public class UnionFindLuogu {
    public static int n, m, maxsize = 10001;
    public static int[] father = new int[maxsize];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;
            build();

            for(int i = 0, op, x, y; i < m; i++){
                in.nextToken();
                op = (int) in.nval;
                in.nextToken();
                x = (int) in.nval;
                in.nextToken();
                y = (int) in.nval;

                if(op == 1){
                    union(x,y);
                }else{
                    out.println(isSameSet(x,y)? "Y":"N");
                }
            }
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int find(int a){
        if(father[a] == a){
            return a;
        }
        father[a] = find(father[a]);
        return father[a];
    }

    public static boolean isSameSet(int a, int b){
        return find(a) == find(b);
    }

    public static void union(int a, int b){
        father[find(a)] = find(b);
    }


    public static void build(){
        for(int i = 0; i<n; i++){
            father[i]=i;
        }
    }

}
