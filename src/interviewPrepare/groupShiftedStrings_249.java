package interviewPrepare;

public class groupShiftedStrings_249 {
    // Given a string and a rotationalFactor (positive integer), rotate every alphanumeric character by:
    // - Lowercase letters ('a'-'z') → rotate forward by rotationalFactor within the alphabet, wrapping around from 'z' to 'a'
    // - Uppercase letters ('A'-'Z') → rotate forward by rotationalFactor within the alphabet, wrapping from 'Z' to 'A'
    // - Digits ('0'-'9') → rotate forward by rotationalFactor within the digits, wrapping from '9' to '0'
    // - Non-alphanumeric characters remain unchanged
    //
    // Example 1:
    // Input:  string = "minMerz-894", rotationalFactor = 5
    // Output: "rnsRjwe-349"
    //
    // Example 2:
    // Input:  string = "XYZ_abo_112288", rotationalFactor = 39
    // Output: "KLM_nob_001177"

    public static String RotationalCipher(String str, int rotationalFactor){
        char[] s = str.toCharArray();
        for(int i = 0; i < str.length(); i++){
            if(s[i]>='a' && s[i]<='z'){
                s[i] =(char)('a' + (s[i]-'a'+rotationalFactor)%26);
            }
            if(s[i]>='A' && s[i]<='Z'){
                s[i] =(char)('A' + (s[i]-'A'+rotationalFactor)%26);
            }
            if(s[i]>='0' && s[i]<='9') {
                s[i] = (char) ('0' + (s[i] - '0' + rotationalFactor) % 10);
            }
        }

        return String.valueOf(s);
    }

    public static void main(String[] args){
        System.out.println(RotationalCipher("mInMerz-894",5));
        System.out.println(RotationalCipher("XYZ_abo_112288",39));
    }
}
