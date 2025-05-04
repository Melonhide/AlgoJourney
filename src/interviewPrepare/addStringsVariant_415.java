package interviewPrepare;

public class addStringsVariant_415 {
    // Add Decimal Strings

    public static String addDecimalStrings(String num1, String num2){
        char[] str1 = num1.toCharArray();
        char[] str2 = num2.toCharArray();
        int p1 = str1.length;
        int p2 = str2.length;
        for(int i = 0; i < str1.length; i++){
            if(str1[i] == '.'){
                p1 = i;
                break;
            }
        }

        for(int i = 0; i < str2.length; i++){
            if(str2[i] == '.'){
                p2 = i;
                break;
            }
        }

        StringBuilder ans = new StringBuilder();
        int i = str1.length-1;
        int j = str2.length-1;
        while(i-p1 != j-p2){
            if(i-p1>j-p2){
                if(str1[i] == '.'){
                    i--;
                }else{
                    ans.append(str1[i--]);
                }

            }else{
                if(str2[j] == '.'){
                    j--;
                }else{
                    ans.append(str2[j--]);
                }
            }
        }
        int progress = 0;
        while(i > p1 && j > p2){
            int cur = str1[i--]-'0'+str2[j--]-'0' + progress;
            progress = cur/10;
            cur %= 10;
            ans.append((char) (cur+'0'));
        }
        ans.append('.');
        if(i==p1){
            i--;
        }
        if(j==p2){
            j--;
        }

        while(i>=0 && j >= 0){
            int cur = str1[i--]-'0'+str2[j--]-'0' + progress;
            progress = cur/10;
            cur %= 10;
            ans.append((char) (cur+'0'));
        }

        while(i>=0){
            int cur = str1[i--]-'0' + progress;
            progress = cur/10;
            cur %= 10;
            ans.append((char) (cur+'0'));
        }

        while(j>=0){
            int cur = str2[j--]-'0' + progress;
            progress = cur/10;
            cur %= 10;
            ans.append((char) (cur+'0'));
        }

        if(progress > 0){
            ans.append((char) (progress+'0'));
        }

        return ans.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(addDecimalStrings("1231232.12313", "12.3212312312"));
    }
}
