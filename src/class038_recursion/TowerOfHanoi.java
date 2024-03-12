package class038_recursion;

import java.lang.String;
import java.util.ArrayList;
public class TowerOfHanoi {

    public static ArrayList<String> ans1 = new ArrayList<>();
    public static ArrayList<String> ans2 = new ArrayList<>();


    public static void hanoi(int n){
        //process(n, "left", "right", "mid");
        process(n, "左", "右", "中");
    }

    public static void process(int n, String left, String right, String mid){
        if(n == 1){
            System.out.println("Move 1 from "+left+" to "+right);
            String curans = "移动圆盘 1 从 " + left + " 到 " + right;
            ans1.add(curans);
        }else{
            process(n-1, left, mid, right);
            System.out.println("Move "+n+" from "+left+" to "+right);
            String curans = "移动圆盘 " + n + " 从 " + left + " 到 " + right;
            ans1.add(curans);
            process(n-1, mid, right, left);
        }
    }

    public static void hanoi2(int n) {
        if (n > 0) {
            f(n, "左", "右", "中");
        }
    }

    public static void f(int i, String from, String to, String other) {
        if (i == 1) {
//            System.out.println("移动圆盘 1 从 " + from + " 到 " + to);
            String curans = "移动圆盘 1 从 " + from + " 到 " + to;
            ans2.add(curans);
        } else {
            f(i - 1, from, other, to);
//            System.out.println("移动圆盘 " + i + " 从 " + from + " 到 " + to);
            String curans = "移动圆盘 " + i + " 从 " + from + " 到 " + to;
            ans2.add(curans);
            f(i - 1, other, to, from);
        }
    }

    public static void main(String[] args){
        int n  = 20;
        hanoi(n);
        hanoi2(n);

        for(int i =0; i < ans1.size(); i++){
            if(ans1.get(i).equals(ans2.get(i))){
                continue;
            }else{
                System.out.println("error!");
                System.out.println(ans1.get(i));
                System.out.println(ans2.get(i));
            }
        }
    }
}
