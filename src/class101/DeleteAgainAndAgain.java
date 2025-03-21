package class101;

import java.io.*;

// 不停删除之后剩下的字符串
// 给定一个字符串s1，如果其中含有s2字符串，就删除最左出现的那个
// 删除之后s1剩下的字符重新拼接在一起，再删除最左出现的那个
// 如此周而复始，返回最终剩下的字符串
// 测试链接 : https://www.luogu.com.cn/problem/P4824
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class DeleteAgainAndAgain {
    public static char[] s1;
    public static char[] s2;
    public static int[] next;
    public static int[] stack1 = new int[1000001];
    public static int[] stack2 = new int[1000001];
    public static int size;
    public static int m;
    public static int n;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        s1 = br.readLine().toCharArray();
        s2 = br.readLine().toCharArray();
        n = s1.length;
        m = s2.length;
        next = new int[m];
        out.println(f());
        out.flush();
        out.close();
        br.close();
    }

    public static String f(){
        getNext();
        size = 0;
        int i = 0;
        int j = 0;
        while(i<n){
            if(s1[i] == s2[j]){
                stack1[size] = i++;
                stack2[size++] = j++;

            }else if(j > 0){
                j = next[j];
            }else{
                stack1[size] = i++;
                stack2[size++] = -1;
            }

            if(j == m){
                size -= m;
                if(size>0){
                    j = stack2[size-1]+1;
                }else{
                    j = 0;
                }
            }
        }
        StringBuilder ans = new StringBuilder();
        for(int k = 0; k < size; k++){
            ans.append(s1[stack1[k]]);
        }

        return ans.toString();
    }
    public static void getNext(){
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while(i<m){
            if(s2[i-1] == s2[cn]){
                next[i++] = ++cn;
            }else if(cn>0){
                cn = next[cn];
            }else{
                next[i++] = 0;
            }
        }
    }
}
