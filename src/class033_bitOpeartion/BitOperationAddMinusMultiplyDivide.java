package class033_bitOpeartion;

public class BitOperationAddMinusMultiplyDivide {
    public static int min = Integer.MIN_VALUE;
    public static void main(String[] args){

        System.out.println("Test Add function");
        for(int i = 0; i < 10000; i++){
            int a = (int)(Math.random()*(100000000));
            int b = (int)(Math.random()*(100000000));
            if((a+b)!=add(a,b)) {
                System.out.println("Error!!!!!!!!!!!!");
                System.out.println(a);
                System.out.println(b);
            }
        }
        System.out.println("Add Function Test completed;");
        System.out.println("No Errors Identified");
        System.out.println();

        System.out.println("Test Minus Function");
        for(int i = 0; i < 10000; i++){
            int a = (int)(Math.random()*(100000000));
            int b = (int)(Math.random()*(100000000));
            if((a-b)!=minus(a,b)) {
                System.out.println("Error!!!!!!!!!!!!");
                System.out.println(a);
                System.out.println(b);
            }
        }
        System.out.println("Minus Function Test completed;");
        System.out.println("No Errors Identified");
        System.out.println();

        System.out.println("Test Multiply Function");
        for(int i = 0; i < 10000; i++){
            int a = (int)(Math.random()*(10000));
            int b = (int)(Math.random()*(10000));
            if((a*b)!=multiply(a,b)) {
                System.out.println("Error!!!!!!!!!!!!");
                System.out.println(a);
                System.out.println(b);
                break;
            }
        }
        System.out.println("Multiply Function Test completed;");
        System.out.println("No Errors Identified");
        System.out.println();

        System.out.println("Test Divide Function");
        for(int i = 0; i < 10000; i++){
            int a = (int)(Math.random()*(min)) * (int)Math.pow(-1, (int)(Math.random()*(min)));
            int b = (int)(Math.random()*(min)) * (int)Math.pow(-1, (int)(Math.random()*(min)));

            if((a/b)!=divide(a,b)) {
                System.out.println("Error!!!!!!!!!!!!");
                System.out.println(a);
                System.out.println(b);
                break;
            }
        }
        System.out.println("Divide Function Test completed;");
        System.out.println("No Errors Identified");
        System.out.println();
        System.out.println(divide(-2147483648, -1));

    }

    public static int add(int a, int b){
        if((a&b)==0){
            return a^b;
        }
        return add(a^b, (a&b)<<1);
    }

    public static int minus(int a, int b){
        return add(a, negative(b));
    }

    public static int negative(int a){
        return (add(~a,1));
    }

    public static int multiply(int a, int b){
        int ans = 0;

        while(b!=0){
            if((b&1)!=0){
                ans = add(ans, a);
            }
            b = b>>>1;
            a = a<<1;

        }
        return ans;
    }

    public static int divide(int a, int b){
        if(b==min && a!=min){
            System.out.println(b);
            return 0;
        }

        if(b==min && a==min){
            System.out.println(b);
            System.out.println(a);
            return 1;
        }

        if(a!=min && b!=min){
            return div(a,b);
        }

        if(b == negative(1)){
            return Integer.MAX_VALUE;
        }

        if(b>0){
            System.out.println(a);
            return minus(div(add(a,b), b), 1);
        }else{
            return add(div(minus(a,b),b), 1);
        }
    }

    public static int div(int a, int b){
        int x = a>0? a:negative(a);
        int y = b>0? b:negative(b);
        int ans = 0;

        for(int i=30; i>=0; i = minus(i, 1)){
            if(x>>>i >= y){
                ans |= 1<<i;
                x = minus(x, y<<i);
            }
        }

        return a>0^b>0? negative(ans):ans;
    }
}
