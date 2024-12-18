package class097;

import java.io.IOException;

// 判断较大的数字是否是质数(Miller-Rabin测试)
// 测试链接 : https://www.luogu.com.cn/problem/U148828
// 如下代码无法通过所有测试用例
// 本文件可以解决10^9范围内数字的质数检查
// 时间复杂度O(s * (logn)的三次方)，很快
// 为什么不能搞定所有long类型的数字检查
// 原因在于long类型位数不够，乘法同余的时候会溢出，课上已经做了说明
public class LargeNumberIsPrime1 {

    public static void main(String[] args) throws IOException{

    }
}
