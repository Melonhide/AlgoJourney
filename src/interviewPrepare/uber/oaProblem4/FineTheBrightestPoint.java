package interviewPrepare.uber.oaProblem4;

import java.util.TreeMap;

public class FineTheBrightestPoint {
    // You are given a list of lights, where each light is represented by a pair of integers [pos, range].
    // Each light illuminates a segment on the number line from pos - range to pos + range (inclusive).
    //
    // The brightness of an integer point is defined as the number of lights that illuminate that point.
    //
    // Your task is to find the point on the number line with the highest brightness, and among all such points,
    // return the smallest index (i.e., the leftmost one).

    //Input: lights = [[-2, 3], [1, 2]]
    //Output: -1
    //
    //Explanation:
    //Light 1: covers [-5, 1]
    //Light 2: covers [-1, 3]
    //
    //Combined coverage:
    //- Point -1 is covered by both lights (brightness = 2)
    //- No other point is covered by 2 lights
    //=> The brightest point is -1

    public static int findBrightestPoint(int[][] lights){
        TreeMap<Integer, Integer> arr = new TreeMap<>();

        for(int[] cur: lights){
            int left = cur[0]-cur[1];
            int right = cur[0]+cur[1];
            arr.put(left, arr.getOrDefault(left, 0)+1);
            arr.put(right+1, arr.getOrDefault(right+1, 0)-1);
        }
        int curLight = 0;
        int maxLight = 0;
        int res = 0;
        for(int key: arr.keySet()){
            curLight+=arr.get(key);
            if(curLight>maxLight){
                maxLight = curLight;
                res = key;
            }
        }

        return res;
    }

    public static void main(String[] args){
        int[][] lights = new int[][]{{-2, 3},{1, 2}};
        System.out.println(findBrightestPoint(lights));
    }


}
