package class087;

// 选择k个数字使得两集合累加和相差不超过1
// 给定一个正数n，表示1~n这些数字都可以选择
// 给定一个正数k，表示要从1~n中选择k个数字组成集合A，剩下数字组成集合B
// 希望做到集合A和集合B的累加和相差不超过1
// 如果能做到，返回集合A选择了哪些数字，任何一种方案都可以
// 如果不能做到，返回长度为0的数组
// 2 <= n <= 10^6
// 1 <= k <= n
// 来自真实大厂笔试，没有测试链接，用对数器验证
public class PickNumbersClosedSum {
    public static int[] pick(int n, int k){
        long target = (1+n)*n/2;
        int sumMin = 0;
        int sumMax = 0;
        for(int i = 1, j = n; i <= k; i++, j--){
            sumMin += i;
            sumMax += j;
        }

        if(sumMin>target||sumMax<target){
            return new int[0];
        }
    }

    public static int[] generate(long sum, int n, int k){

    }


    public static void main(String[] args) {
        int N = 60;
        int testTime = 5000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int n = (int) (Math.random() * N) + 2;
            int k = (int) (Math.random() * n) + 1;
            int[] ans = pick(n, k);
            if (!pass(n, k, ans)) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }
}
