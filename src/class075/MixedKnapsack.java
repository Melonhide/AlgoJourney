package class075;


import java.io.*;
import java.util.Arrays;

// 混合背包 + 多重背包普通窗口优化
// 能成功找零的钱数种类
// 每一种货币都给定面值val[i]，和拥有的数量cnt[i]
// 想知道目前拥有的货币，在钱数为1、2、3...m时
// 能找零成功的钱数有多少
// 也就是说当钱数的范围是1~m
// 返回这个范围上有多少可以找零成功的钱数
// 比如只有3元的货币，数量是5张
// m = 10
// 那么在1~10范围上，只有钱数是3、6、9时，可以成功找零
// 所以返回3表示有3种钱数可以找零成功
// 测试链接 : http://poj.org/problem?id=1742
// 请同学们务必参考如下代码中关于输入、输出的处理
// 这是输入输出处理效率很高的写法
// 提交以下的code，提交时请把类名改成"Main"，可以直接通过
public class MixedKnapsack{

    public static int maxn = 101;
    public static int maxm = 100001;
    public static int[] cnt = new int[maxn];
    public static int[] val = new int[maxn];
    public static boolean[] dp = new boolean[maxm];

    public static int n,m,l,r;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in= new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            n = (int) in.nval;
            in.nextToken();
            m = (int) in.nval;

            if(n!=0 || m!=0){
                for(int i = 1; i <= n; i++){
                    in.nextToken();
                    val[i] = (int) in.nval;
                }

                for(int i = 1; i <= n; i++){
                    in.nextToken();
                    cnt[i] = (int) in.nval;
                }
            }

            out.println(compute());
        }
        out.flush();
        out.close();
        br.close();
    }

    public static int compute(){
        Arrays.fill(dp, 1, m+1, false);
        dp[0] = true;
        for(int i = 1; i<= n; i++){
            if(cnt[i] == 1){
                for(int j = m; j>=val[i]; j--){
                    dp[j]|=dp[j-val[i]];

                }
            }else if(cnt[i]*val[i]>=m){
                for(int j = val[i]; j<=m; j++){
                    dp[j] |= dp[j-val[i]];

                }
            }else{
                for(int mod = 0; mod < val[i]; mod++){
                    int truecnt = 0;
                    for(int j = m-mod, k = 0; j>=0 && k<=cnt[i]; j -= val[i], k++){
                        truecnt += dp[j]? 1:0;
                    }
                    for(int j = m-mod, l = j-val[i]*(cnt[i]+1); j>=1; j-=val[i],l-=val[i]){
//                        if(truecnt!=0){
//                            dp[j] = true;
//                        }
//
//                        if(dp[j]){
//                            truecnt--;
//                        }
//
//                        if(l>=0){
//                            truecnt += dp[l]? 1:0;
//                        }

                        if (dp[j]) {
                            truecnt--;
                        } else {
                            if (truecnt != 0) {
                                dp[j] = true;
                            }
                        }
                        if (l >= 0) {
                            truecnt += dp[l] ? 1 : 0;
                        }
                    }
                }
            }
        }


        int ans = 0;
        for(int i = 1; i <= m; i++){
            if(dp[i]){
                ans++;
            }
        }
        return ans;
    }
}
