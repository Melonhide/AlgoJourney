package class097;

// 数字n拆分质数因子
public class  PrimeFactors {
    public static void main(String[] args) {
        int n = 4012100;
        f(n);
    }

    public static void f(int n){
        for(int i = 2; i*i<=n; i++){
            if(n%i==0){
                System.out.println(i);
                while(n%i==0){
                    n /= i;
                }
            }
        }
        if(n>1){
            System.out.println(n);
        }
    }
    // 按公因数计算最大组件大小
    // 给定一个由不同正整数的组成的非空数组 nums
    // 如果 nums[i] 和 nums[j] 有一个大于1的公因子，那么这两个数之间有一条无向边
    // 返回 nums中最大连通组件的大小。
    // 测试链接 : https://leetcode.cn/problems/largest-component-size-by-common-factor/
}
