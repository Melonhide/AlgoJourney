package class097;
// 判断较小的数字是否是质数
// 时间复杂度O(根号n)
public class SmallNumberIsPrime {

    public static boolean isPrime(long n){
        if(n<=1){
            return false;
        }

        for(long i = 2; i*i<=n; i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }
}
