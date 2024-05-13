package class076;


// 布尔运算
// 给定一个布尔表达式和一个期望的布尔结果 result
// 布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成
// 布尔表达式一定是正确的，不需要检查有效性
// 但是其中没有任何括号来表示优先级
// 你可以随意添加括号来改变逻辑优先级
// 目的是让表达式能够最终得出result的结果
// 返回最终得出result有多少种不同的逻辑计算顺序
// 测试链接 : https://leetcode.cn/problems/boolean-evaluation-lcci/
public class BooleanEvaluation {
    public static int countEval(String s, int result) {
        char[] str = s.toCharArray();
        int n = str.length;
        int[][][] dp = new int[n][n][];
        return f(str, 0, str.length-1, dp)[result];
    }
    public static int[] f(char[] s, int l, int r, int[][][] dp){
        if(dp[l][r]!=null){
            return dp[l][r];
        }

        int f = 0;
        int t = 0;
        if(l == r){
            if(s[l] == '1'){
                t++;
            }else{
                f++;
            }
            dp[l][r] = new int[] {f, t};
            return dp[l][r];
        }
        for(int k = l+1, leftt,leftf,rightt,rightf; k < r; k+=2){
            int[] tmp = f(s, l, k-1, dp);
            leftt = tmp[1];
            leftf = tmp[0];
            tmp = f(s, k+1, r, dp);
            rightt = tmp[1];
            rightf = tmp[0];
            if(s[k] == '&'){
                t += leftt*rightt;
                f += leftt*rightf+leftf*rightt+leftf*rightf;
            }else if(s[k] == '|'){
                t += leftt*rightt + leftt*rightf + rightt*leftf;
                f += leftf*rightf;
            }else{
                t += leftt*rightf + leftf*rightt;
                f += leftf*rightf + rightt*leftt;
            }
        }

        dp[l][r] = new int[] {f, t};
        return dp[l][r];
    }

    public static void main(String[] args){
        System.out.println(countEval("1^0|0|1", 0));
    }
}
