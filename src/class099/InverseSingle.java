package class099;

// 单个除数求逆元
// 对数器验证
public class InverseSingle {
    public static void main(String[] args) {
        // 1) 必须保证a/b可以整除
        // 2) 必须保证mod是质数
        // 3) 必须保证b和mod的最大公约数为1
        int mod = 41;
        long b = 3671613L;
        long a = 67312L * b;
        System.out.println(compute1(a, b, mod));
        System.out.println(compute2(a, b, mod));
    }

    public static int compute1(long a, long b, int mod) {
        return (int) ((a / b) % mod);
    }

    public static int compute2(long a, long b, int mod){

        return (int)((a*power(b, mod-2, mod))%mod);
    }

    public static int power(long a, int p, int mod){
        int res = 1;
        while(p>0){
            if((p&1)==1){
                res = (int)(((long)res*a)%mod);
            }
            a = (a*a)%mod;
            p>>=1;
        }

        return res;
    }
}
