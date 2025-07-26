package interviewPrepare.microsoft;

public class ArrayGenerator {

    //An array generator service takes in a single integer k and a sum s. It returns an array with a sum s where the iᵗʰ element is (k + i + 1).
    //Thus, for the parameters k = 6 and s = 30, the service returns [6, 7, 8, 9].
    //Note that it is not always possible to generate a valid array for some pair of k and s.
    //Given an integer s, find the number of valid values of k for which it is possible to generate a valid array using the service.
    //Example
    //Suppose s = 10.
    //For k = 1, [1, 2, 3, 4] sums to 10
    //For k = 10, [10] sums to 10
    //There are 2 values of k for s = 10.

    static int array_generator(int s){
        int ans = 1;
        for(int k = 1, l, r, m, sum; k < s/2; k++){
            l = k+1;
            r = s-1;
            while(l<=r){
                m = (l+r)/2;
                sum = (k+m)*(m-k+1)/2;
                if(sum == s){
                    System.out.println(k + ":" + m);
                    ans++;
                    break;
                }else if(sum<s){
                    l = m+1;
                }else{
                    r = m-1;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args){
        System.out.println(array_generator(30));
    }
}
