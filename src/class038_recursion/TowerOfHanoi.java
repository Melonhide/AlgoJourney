package class038_recursion;

public class TowerOfHanoi {
    public static void hanoi(int n){
        process(n, "left", "mid", "right");
    }

    public static void process(int n, String from, String other, String to){
        if(n == 1){
            System.out.println("Move "+n+" from "+ from + " to " + to);
            return;
        }
        process(n-1, from, to ,other);
        System.out.println("Move "+n+" from "+ from + " to " + to);
        process(n-1, other, from , to);
    }

    public static void main(String[] args){
        hanoi(3);
    }
}
