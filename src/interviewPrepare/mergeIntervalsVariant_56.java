package interviewPrepare;

import java.util.ArrayList;
import java.util.List;

public class mergeIntervalsVariant_56 {
    // Merge two sorted lists
    // Input [[1,3],[5,8],[11,15]]
    // Output

    public static List<int[]> mergeIntervals(int[][] interval1, int[][] interval2){
        List<int[]> ans = new ArrayList<>();
        int i = 0;
        int j = 0;
        while(i<interval1.length && j < interval2.length){
            int[] cur;
            if(interval1[i][0]<interval2[j][0]){
                cur = interval1[i++];
            }else{
                cur = interval2[j++];
            }

            if(ans.size() == 0){
                ans.add(cur);
            }else{
                int[] prev = ans.get(ans.size()-1);
                if(prev[1]<cur[0]){
                    ans.add(cur);
                }else{
                    ans.get(ans.size()-1)[1] = Math.max(prev[1], cur[1]);
                }
            }
        }

        while(i<interval1.length){
            int[] cur = interval1[i++];

            if(ans.size() == 0){
                ans.add(cur);
            }else{
                int[] prev = ans.get(ans.size()-1);
                if(prev[1]<cur[0]){
                    ans.add(cur);
                }else{
                    ans.get(ans.size()-1)[1] = Math.max(prev[1], cur[1]);
                }
            }
        }

        while(j<interval2.length){
            int[] cur = interval2[j++];

            if(ans.size() == 0){
                ans.add(cur);
            }else{
                int[] prev = ans.get(ans.size()-1);
                if(prev[1]<cur[0]){
                    ans.add(cur);
                }else{
                    ans.get(ans.size()-1)[1] = Math.max(prev[1], cur[1]);
                }
            }
        }


        return ans;
    }


    public static void main(String[] args){
        int[][] a = {
                {3, 11}, {14,15}, {18,22}, {23, 24}, {25,26}
        };

        int[][] b = {
                {2, 8}, {13, 20}
        };

        List<int[]> res = mergeIntervals(a,b);
        for(int[] interval: res){
            System.out.println(interval[0] + "-" + interval[1]);
        }
    }
}
