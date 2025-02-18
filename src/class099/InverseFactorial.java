package class099;
// 连续阶乘逆元的线性递推
// 实现组合公式C(n,m)的计算
// 最终结果 % 1000000007后返回
// 0 <= m <= n <= 1000
// 对数器验证
public class InverseFactorial {
    public static int MOD = 1000000007;
    public static int LIMIT = 1000;

    public static long[] fac = new long[LIMIT+1];

    public static long[] inv = new long[LIMIT+1];

    public static void build(){
        fac[1] = 1;
        for(int i = 2; i <= LIMIT; i++){
            fac[i] = (fac[i-1] * i) % MOD;
        }


    }

    public long power(long x, int n){
        long ans = 1;
        while(n>0){
            if((n&1) == 1){
                ans = (ans * x)%MOD;
            }
            x = (x*x)%MOD;
            n>>=1;
        }

        return ans;
    }


    public static void main(String[] args){

    }
}
