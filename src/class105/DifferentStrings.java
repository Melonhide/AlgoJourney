package class105;

import java.io.*;
import java.util.Arrays;

// 统计有多少个不同的字符串
// 测试链接 : https://www.luogu.com.cn/problem/P3370
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class DifferentStrings {

    public static int maxn = 100001;
    public static long[] nums = new long[maxn];
    public static long base = 499;
    public static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        n = Integer.valueOf(br.readLine());
        for(int i = 0; i < n; i++){
            String cur = br.readLine();
            nums[i] = hash(cur.toCharArray());
        }
        out.println(cnt());
        out.flush();
        out.close();
        br.close();
    }

    public static long hash(char[] str){
        long ans = 0L;
        for(int i = 0, cur;  i < str.length; i++){
            if(str[i] >= '0' && str[i]<='9'){
                cur = str[i]-'0'+1;
            }else if(str[i] >= 'a' && str[i] <= 'z'){
                cur = str[i]-'a' + 11;
            }else{
                cur = str[i]-'A' + 37;
            }
            ans = ans*base + str[i];
        }
        return ans;
    }

    public static int cnt(){
        Arrays.sort(nums, 0, n);
        int ans = 1;
        for(int i = 1, pre = 0; i < n; i++){
            if(nums[i] == nums[pre]){
                continue;
            }else{
                ans++;
                pre = i;
            }
        }
        return ans;
    }
}
