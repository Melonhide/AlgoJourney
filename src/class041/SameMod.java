package class041;

import java.math.BigInteger;

public class SameMod {
    public static void main(String[] args){
        System.out.println("Test Start");
        int testTime = 10000;
        int mod = 100000007;
        for(int i = 0; i <testTime; i++){
            long a = random();
            long b = random();
            long c = random();
            long d = random();
            if(f1(a,b,c,d,mod)!=f2(a,b,c,d,mod)){
                System.out.println("ERROR!!!!!");
                break;
            }
        }

        System.out.println("Test End");

        System.out.println("===");
        long a = random();
        long b = random();
        long c = random();
        long d = random();
        System.out.println("a : " + a);
        System.out.println("b : " + b);
        System.out.println("c : " + c);
        System.out.println("d : " + d);
        System.out.println("===");
        System.out.println("f1 : " + f1(a, b, c, d, mod));
        System.out.println("f2 : " + f2(a, b, c, d, mod));

    }

    // Generate Testing Case
    public static long random(){
        return (long) (Math.random()*Long.MAX_VALUE);
    }

    //get the result of ((a+b)*(c-d)+(a*c-b*d))%mod
    public static int f1(long a, long b, long c, long d, int mod){
        BigInteger o1 = new BigInteger(String.valueOf(a));
        BigInteger o2 = new BigInteger(String.valueOf(b));
        BigInteger o3 = new BigInteger(String.valueOf(c));
        BigInteger o4 = new BigInteger(String.valueOf(d));
        BigInteger o5 = o1.add(o2);
        BigInteger o6 = o3.subtract(o4);
        BigInteger o7 = o1.multiply(o3);
        BigInteger o8 = o2.multiply(o4);
        BigInteger o9 = o5.multiply(o6);
        BigInteger o10 = o7.subtract(o8);
        BigInteger o11 = o9.add(o10);
        BigInteger o12 = o11.mod(new BigInteger(String.valueOf(mod)));
        if(o12.signum() == -1){
            return o12.add(new BigInteger(String.valueOf(mod))).intValue();
        }else{
            return o12.intValue();
        }
    }

    //get the result of ((a+b)*(c-d)+(a*c-b*d))%mod
    public static int f2(long a, long b, long c, long d, int mod){
        int o1 = (int) (a%mod);
        int o2 = (int) (b%mod);
        int o3 = (int) (c%mod);
        int o4 = (int) (d%mod);
        int o5 = (o1+o2)%mod;
        int o6 = (o3-o4+mod)%mod;
        int o7 = (int) (((long) o1*o3)%mod);
        int o8 = (int) (((long) o2*o4)%mod);
        int o9 = (int) (((long) o5*o6)%mod);
        int o10 = (o7-o8+mod)%mod;
        return (o9+o10)%mod;
    }

}
