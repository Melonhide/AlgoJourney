package class091;

import java.util.HashMap;
import java.util.Map;

// 两个0和1数量相等区间的最大长度
// 给出一个长度为n的01串，现在请你找到两个区间
// 使得这两个区间中，1的个数相等，0的个数也相等
// 这两个区间可以相交，但是不可以完全重叠，即两个区间的左右端点不可以完全一样
// 现在请你找到两个最长的区间，满足以上要求
// 返回区间最大长度
// 来自真实大厂笔试，没有在线测试，对数器验证
public class LongestSameZerosOnes {
    public static int len1(int[] arr){
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for(int i = 0, one, zero; i < arr.length; i++){
            one = 0;
            zero = 0;
            for(int j = i; j < arr.length; j++){
                one += arr[j] == 1? 1:0;
                zero += arr[j] == 0? 1:0;
                map.putIfAbsent(zero, new HashMap<>());
                map.get(zero).put(one, map.get(zero).getOrDefault(one, 0)+1);
            }
        }
        int ans = 0;
        for(int z:map.keySet()){
            for(int o:map.get(z).keySet()){
                if(map.get(z).get(o)>1){
                    ans = Math.max(ans, z+o);
                }
            }
        }
        return ans;
    }
    public static int len2(int[] arr){
        int leftone = -1;
        int leftzero = -1;
        int rightone = -1;
        int rightzero = -1;

        for(int i = 0; i < arr.length; i++){
            if(leftone == -1 && arr[i] == 1){
                leftone = i;
            }

            if(leftzero == -1 && arr[i] == 0){
                leftzero = i;
            }

            if(leftzero != -1 && leftone != -1){
                break;
            }
        }

        for(int i = arr.length-1; i >= 0; i--){
            if(rightone == -1 && arr[i] == 1){
                rightone = i;
            }

            if(rightzero == -1 && arr[i] == 0){
                rightzero = i;
            }

            if(rightzero != -1 && rightone != -1){
                break;
            }
        }

        int ans1 = rightzero!=-1&&leftzero!=-1? rightzero-leftzero:0;
        int ans2 = rightone!=-1&&leftone!=-1? rightone-leftone:0;
        return Math.max(ans1, ans2);
    }

    public static int[] randomArray(int len) {
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            ans[i] = (int) (Math.random() * 2);
        }
        return ans;
    }

    // 为了验证
    public static void main(String[] args) {
        int N = 500;
        int testTimes = 2000;
        System.out.println("测试开始");
        for (int i = 1; i <= testTimes; i++) {
            int n = (int) (Math.random() * N) + 2;
            int[] arr = randomArray(n);
            int ans1 = len1(arr);
            int ans2 = len2(arr);
            if (ans1 != ans2) {
                System.out.println("出错了!");
            }
            if (i % 100 == 0) {
                System.out.println("测试到第" + i + "组");
            }
        }
        System.out.println("测试结束");
    }
}
