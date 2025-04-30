package interviewPrepare;

public class validWordAbbreviationVariant_408 {
    // Given a string `word` and an abbreviation `abbr`, return whether the abbreviation is valid.
    // Abbreviation can include:
    //  - Numbers (indicating how many characters to skip in `word`, no leading zeros allowed)
    //  - Asterisk '*' as a wildcard that matches any sequence of characters (including empty string)
    //
    // Example 1:
    // Input: word = "abc", abbr = "*"
    // Output: true  ('*' matches the entire word)
    //
    // Example 2:
    // Input: word = "tadpoletech", abbr = "*pole*ie"
    // Output: false (the sequence "ie" does not appear in the word after matching both '*')

    // Example 3:
    // Input: word = "internationalization", abbr = "i12iz4n"
    // Output: true

    public static boolean validWordAbbreviationVariant(String word, String abbr){
        char[] str1 = word.toCharArray();
        char[] str2 = abbr.toCharArray();
        return f(str1, str2, 0,0);
    }

    public static boolean f(char[] str1, char[] str2, int i, int j){
        if(i == str1.length && j == str2.length){
            return true;
        }

        if(j == str2.length){
            return false;
        }

        if(i == str1.length){
            for(; j<str2.length; j++){
                if(str2[j] != '*'){
                    return false;
                }
            }
            return true;
        }

        if(str2[j] == '0'){
            return false;
        }else if(str2[j]>'0' && str2[j]<='9'){
            int cnt = 0;
            while(j<str2.length && str2[j]>='0' && str2[j]<='9'){
                cnt = cnt*10 + str2[j++]-'0';
            }
            return f(str1, str2, i+cnt, j);
        }

        if(str2[j] == '*'){
            return f(str1, str2, i+1, j) || f(str1, str2, i, j+1);
        }

        if(str2[j] == str1[i]){
            return f(str1, str2, i+1, j+1);
        }

        return false;
    }


    public static void main(String[] args){
        System.out.println(validWordAbbreviationVariant("internationalization", "i12iz4n"));
        System.out.println(validWordAbbreviationVariant("abc", "*"));
        System.out.println(validWordAbbreviationVariant("abc", "*abc***"));
        System.out.println(validWordAbbreviationVariant("tadpoletech", "*pole*ie"));
    }
}
