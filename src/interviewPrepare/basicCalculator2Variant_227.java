package interviewPrepare;

public class basicCalculator2Variant_227 {
    // Basic calculator without '-" and '/';
    public static int basicCalculatorVariant(String s){
        char[] str = s.toCharArray();
        int[] nums = new int[str.length];
        char[] ops = new char[str.length];
        int s1 = 0;
        int s2 = 0;
        int i = 0;
        int cur = 0;
        while(i<str.length){
            if(str[i]<='9' && str[i]>='0'){
                cur = cur *10 + (str[i]-'0');
            }else if(str[i] == '+' || str[i] == '*') {
                if(s2>0 && ops[s2-1] == '*'){
                    nums[s1-1] *= cur;
                    ops[s2-1] = str[i];
                }else{
                    nums[s1++] = cur;
                    ops[s2++] = str[i];
                }
                cur = 0;
            }
            i++;
        }
        if(s2>0 && ops[s2-1] == '*'){
            nums[s1-1] *= cur;
        }else{
            nums[s1++] = cur;
        }
        i = 0;
        cur = nums[0];
        for(;i<s2; i++){
            cur += nums[i+1];
        }
        return cur;
    }


    public static void main(String[] args){
        System.out.println(basicCalculatorVariant("3*5 * 2"));
    }
}
