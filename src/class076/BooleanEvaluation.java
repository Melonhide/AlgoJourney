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
    public int countEval(String s, int result) {
        char[] str = s.toCharArray();



    }
    public static int[] f(char[] s, int l, int r){
        int f = 0;
        int t = 0;
        if(l == r){
            if(s[l] == 1){
                t++;
            }else{
                f++;
            }
            return new int[] {f, t};
        }
        if(s[l+1] == '')
    }

    public static void main(String[] args){

    }
}
