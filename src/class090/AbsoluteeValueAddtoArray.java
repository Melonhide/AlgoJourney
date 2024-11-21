package class090;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

// 加入差值绝对值直到长度固定
// 给定一个非负数组arr，计算任何两个数差值的绝对值
// 如果arr中没有，都要加入到arr里，但是只加一份
// 然后新的arr继续计算任何两个数差值的绝对值，
// 如果arr中没有，都要加入到arr里，但是只加一份
// 一直到arr大小固定，返回arr最终的长度
// 来自真实大厂笔试，没有在线测试，对数器验证
public class AbsoluteeValueAddtoArray {
    public static int len1(int[] arr){
        ArrayList<Integer> nums = new ArrayList<>();
        HashSet<Integer> added = new HashSet<>();
        for(int i:arr){
            nums.add(i);
            added.add(i);
        }

        while(!finish(nums,added));

        return nums.size();
    }

    public static boolean finish(ArrayList<Integer> nums, HashSet<Integer> added){
        int len = nums.size();
        for(int i = 0; i < len; i++){
            for(int j = i+1, cur; j< len; j++){
                cur = Math.abs(nums.get(i)-nums.get(j));
                if(!added.contains(cur)){
                    added.add(cur);
                    nums.add(cur);
                }
            }
        }
        return len == nums.size();
    }

    public static int len2(int[] arr){
        int max = 0;
        int gcd = 0;
        for(int num:arr){
            max = Math.max(max, num);
            if(num != 0){
                gcd = num;
            }
        }

        if(max == 0){
            return arr.length;
        }
        HashMap<Integer, Integer> count = new HashMap<>();
        for(int num: arr){
            count.put(num, count.getOrDefault(num, 0)+1);
            if(num!=0){
                gcd = gcd(num, gcd);
            }
        }
        int ans = max/gcd;
        int maxcnt = 1;
        for(int num:count.keySet()){
            if(num != 0){
                maxcnt = Math.max(maxcnt, count.get(num));
                ans += count.get(num)-1;
            }
        }
        ans += count.containsKey(0)? count.get(0):(maxcnt>1? 1:0);
        return ans;
    }

    public static int gcd(int a, int b){
        return b == 0? a:gcd(b, a%b);
    }

    public static int[] randomArray(int n, int v) {
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = (int) (Math.random() * v);
        }
        return ans;
    }
    public static void main(String[] args){
        int N = 50;
        int V = 100;
        int testTimes = 20000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            int n = (int) (Math.random() * N) + 1;
            int[] nums = randomArray(n, V);
            int ans1 = len1(nums);
            int ans2 = len2(nums);
            if (ans1 != ans2) {
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");
    }
}
