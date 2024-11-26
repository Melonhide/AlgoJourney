package class091;

// 两个0和1数量相等区间的最大长度
// 给出一个长度为n的01串，现在请你找到两个区间
// 使得这两个区间中，1的个数相等，0的个数也相等
// 这两个区间可以相交，但是不可以完全重叠，即两个区间的左右端点不可以完全一样
// 现在请你找到两个最长的区间，满足以上要求
// 返回区间最大长度
// 来自真实大厂笔试，没有在线测试，对数器验证
public class LongestSameZerosOnes {
    public static int len1(int[] arr){

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
}
