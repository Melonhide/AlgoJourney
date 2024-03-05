package class038_recursion;

import java.util.Stack;

public class ReverseStackWithRecursive {

    public static void reverse(Stack<Integer> s){
        if(s.empty()){
            return;
        }else{
            int last = bottomOut(s);
            reverse(s);
            s.push(last);
        }
    }

    public static int bottomOut(Stack<Integer> s){
        int ans = s.pop();
        if(!s.empty()){
            int last = bottomOut(s);
            s.push(ans);
            return last;
        }else{
            return ans;
        }
    }

    public static void main(String[] args){
        Stack<Integer> s = new Stack<>();

        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);
        s.push(6);
        reverse(s);
        while(!s.isEmpty()){
            System.out.println(s.pop());
        }
    }
}
