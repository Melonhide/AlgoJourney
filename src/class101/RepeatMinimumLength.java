package class101;

import java.io.*;

// 最短循环节的长度
// 给你一个字符串s，它一定是由某个循环节不断自我连接形成的
// 题目保证至少重复2次，但是最后一个循环节不一定完整
// 现在想知道s的最短循环节是多长
// 测试链接 : https://www.luogu.com.cn/problem/P4391
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class RepeatMinimumLength {
    public static int maxn = 1000001;
    public static int n;
    public static int[] next = new int[maxn];
    public static char[] str;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        n = Integer.valueOf(br.readLine());
        str = br.readLine().toCharArray();
        out.println(compute());
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(){
        next[0] = -1;
        int i = 2;
        int cn = 0;
        while(i<=n){
            if(str[i-1] == str[cn]){
                next[i++] = ++cn;
            }else if(cn>0){
                cn = next[cn];
            }else{
                i++;
            }
        }

        return n - next[n];
    }
}
