package class041;

public class GcdAndLcm {

    public static void main(String[] args){
        for(int i = 0; i<10000;i++){
            long a = (long)(Math.random()*Math.pow(2,60));
            long b = (long)(Math.random()*Math.pow(2,60));
            if(gcd(a,b)!=gcd_zuo(a,b)){
                System.out.println("GCD Error");
                System.out.println(a);
                System.out.println(b);
                System.out.println(gcd(a,b));
                System.out.println(gcd_zuo(a,b));
                break;
            }
            if(lcm(a,b)!=lcm_zuo(a,b)){
                System.out.println("LCM Error");
                System.out.println(a);
                System.out.println(b);
                System.out.println(lcm(a,b));
                System.out.println(lcm_zuo(a,b));
                break;
            }
        }
        System.out.println("Done");

    }
    public static long gcd(long a, long b){
        return b==0? a:gcd(b, a%b);
    }

    public static long lcm(long a, long b){
        return a/gcd(a,b)*b;
    }

    public static long gcd_zuo(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static long lcm_zuo(long a, long b) {
        return (long) a / gcd(a, b) * b;
    }

}
