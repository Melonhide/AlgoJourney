package class085;

// windy数
// 不含前导零且相邻两个数字之差至少为2的正整数被称为windy数
// windy想知道[a,b]范围上总共有多少个windy数
// 测试链接 : https://www.luogu.com.cn/problem/P2657
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过

import java.io.*;

public class WindyNumber {
    public static int a, b;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken() != StreamTokenizer.TT_EOF){
            a = (int) in.nval;
            in.nextToken();
            b = (int) in.nval;
            out.println(compute());

        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(){
        return cnt(b) - cnt(a-1);
    }

    public static int cnt(int n){
        int tmp = n/10;
        int len = 1;
        int offset = 1;
        while(tmp != 0){
            tmp /= 10;
            len++;
            offset *= 10;
        }

        return f(n, len, offset, 0, 10);
    }

    public static int f(int n, int len, int offset, int free, int pre){
        if(len == 0){
            return 1;
        }

        int cur = (n/offset % 10);
        int ans = 0;
        if(free == 0){
            if(pre == 10){
                ans += f(n, len-1, offset/10, 1, pre);
                for(int i = 1; i < cur; i++){
                    ans += f(n, len-1, offset/10, 1, i);
                }
                ans += f(n, len-1, offset/10, 0, cur);
            }else{
                for(int i = 0; i < cur; i++){
                    if(i-pre >= 2 || pre-i >= 2){
                        ans += f(n, len-1, offset/10, 1, i);
                    }
                }
                if(cur-pre >= 2||pre-cur >= 2){
                    ans += f(n, len-1, offset/10, 0, cur);
                }
            }
        }else{
            if(pre == 10){
                for(int i = 1; i <= 10; i++){
                    ans += f(n, len-1, offset/10, 1, i);
                }
            }else{
                for(int i = 0; i <= 9; i++){
                    if(i-pre >= 2 || pre-i >= 2){
                        ans += f(n, len-1, offset/10, 1, i);
                    }
                }
            }
        }
        return ans;
    }
}
