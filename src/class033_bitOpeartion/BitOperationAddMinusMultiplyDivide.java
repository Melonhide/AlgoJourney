package class033_bitOpeartion;

public class BitOperationAddMinusMultiplyDivide {
    public static void main(String[] args){
        for(int i = 0; i < 10000; i++){
            int a = (int)(Math.random()*(100000000));
            int b = (int)(Math.random()*(100000000));
            if((a+b)!=add(a,b)){
                System.out.println("Error!!!!!!!!!!!!");
                System.out.println(a);
                System.out.println(b);
            }
            System.out.println(a);
            System.out.println(b);

        }


    }

    public static int add(int a, int b){
        if((a&b)==0){
            return a^b;
        }
        return add(a^b, (a&b)<<1);
    }
}
