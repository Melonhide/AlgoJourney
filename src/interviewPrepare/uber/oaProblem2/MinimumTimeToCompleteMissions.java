package interviewPrepare.uber.oaProblem2;

public class MinimumTimeToCompleteMissions {
    //You are given two lists of integers A and B, representing departure times from location A to B and from B back to A, respectively.
    //
    //Each mission consists of a round trip:
    //
    //Depart from location A at the earliest available time t1 ≥ current_time.
    //
    //Travel from A to B takes exactly 100 units of time.
    //
    //Depart from B at the earliest available time t2 ≥ arrival_at_B.
    //
    //Travel from B to A takes exactly 100 units of time.
    //
    //You are given an integer k, the number of missions to complete. Each mission starts immediately after the previous one ends (i.e., when you arrive back at A).
    //
    //Return the minimum possible time to complete all k missions, starting from time 0.

//    Input:
//    A = [0, 99, 300, 500],
//    B = [101, 220, 440, 900],
//    k = 2
//
//    Output: 540
//
//    Explanation:
//            - Mission 1:
//            - Depart A at 0, arrive B at 100
//            - Depart B at 101, arrive A at 201
//            - Mission 2:
//            - Depart A at 300, arrive B at 400
//            - Depart B at 440, arrive A at 540
//            - Total time = 540

    public static int minimumTimeToCompleteMissions(int[] a, int[] b, int k){
        int cur = 0;
        int aind = 0;
        int bind = 0;
        while(k>0){
            aind = f(a, cur, aind);
            cur = a[aind]+100;
            bind = f(b, cur, bind);
            cur = b[bind]+100;
            k--;
        }
        return cur;
    }

    public static int f(int[] a, int target, int start){
        int l = start;
        int r = a.length-1;
        int find = r;
        while(l<=r){
            int m = (l+r)/2;
            if(a[m]>=target){
                find = m;
                r = m-1;
            }else{
                l = m+1;
            }
        }

        return find;
    }

    public static void main(String[] args){
        int[] a = new int[]{0, 99, 300, 500};
        int[] b = new int[]{101, 220, 440, 900};
        int k = 2;
        System.out.print(minimumTimeToCompleteMissions(a, b, k));
    }

}
