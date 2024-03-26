package OA;

public class URLHashing {

//    Implement an algorithm to hash a URL as described.
//
//    Suppose the given URL url of length n is to be hashed with a string hash_string of length m. Given an integer k, run the url through the following algorithm:
//
//    Divide the URL into blocks of size k starting from the left. The last block can be smaller than k. For example, if url = "https://xyz.com" and k = 4, the blocks are ["http", "s://", "xyz.", "com"].
//    The values of the English characters 'a', 'b', ..., 'z' are 0, 1, ..., 25 respectively, and that of ':', '/' and '.' are 26, 27, and 28 respectively. Thus the has value of the block "s://" will be 19 + 26 + 27 + 27 = 98.
//    For each URL, find the hash value of each block. The hash value is the sum of the values of each character.
//    Replace the block with the (hash value of the block modulo m)th character of the string hash_string.
//    Given the string url, hash_string, and an integer k, find the hashed string.
//
    // input: "https://xyz.com", "pqrst", 4
    // output: psps
    public static void main(String[] args){
        System.out.println(hashUrl("https://xyz.com", "pqrst", 4));
    }
    public static String hashUrl(String url, String hashString, int k) {
        StringBuilder ans = new StringBuilder();
        int curhashsum = 0;
        int curlength = 0;
        for(int i = 0; i<url.length(); i++, curlength++){
            if(curlength<k){
                curhashsum+=getHashValue(url.charAt(i));
            }else{
                ans.append(hashString.charAt(curhashsum%hashString.length()));
                curlength = 0;
                curhashsum = getHashValue(url.charAt(i));
            }
        }
        if(curlength>0){
            ans.append(hashString.charAt(curhashsum%hashString.length()));
        }


        return ans.toString();
    }

    public static int getHashValue(char cur){
        if(cur>='a'&&cur<='z'){
            return cur-'a';
        }

        if(cur == ':'){
            return 26;
        }else if(cur == '/'){
            return 27;
        }else{
            return 28;
        }
    }


}
