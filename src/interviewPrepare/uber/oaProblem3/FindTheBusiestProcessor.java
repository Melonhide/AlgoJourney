package interviewPrepare.uber.oaProblem3;

public class FindTheBusiestProcessor {
    //You are given:
    //
    //A list of integers capacities, where capacities[i] represents the maximum number of instructions processor i can handle before requiring a reset.
    //
    //A list of strings instructions, where each instruction is either:
    //
    //"package": One instruction to be processed by the next available processor.
    //
    //"closure X": Permanently disables processor X (0-indexed).
    //
    //Each time a "package" instruction is issued:
    //
    //It is assigned to the current processor (starting from processor 0).
    //
    //If the current processor is closed, or its remaining capacity is 0, skip to the next processor.
    //
    //If all processors are either closed or full, reset all non-closed processors back to their original capacity and start from processor 0 again.
    //
    //Your task is to return the index of the processor that processed the most instructions. If there is a tie, return the one with the smallest index.

    public static int findBusiestProcessor(int[] capacity, String[] instructions){
        int n = capacity.length;
        boolean[] close = new boolean[n];
        int[] cnt = new int[n];
        int[] current = new int[n];
        int total = 0;
        int cur = 0;
        for(int i = 0; i < n; i++){
            total += capacity[i];
        }
        int available = total;

        for(String i: instructions){
            if(i.charAt(0) == 'p'){
                if(available>0){
                    while(cur<n && (close[cur] || current[cur] == capacity[cur])){
                        cur++;
                    }

                }else{
                    cur = 0;
                    current = new int[n];
                    available = total;
                    while(cur < n && close[cur]) {
                        cur++;
                    }
                }

                cnt[cur]++;
                current[cur]++;
                available--;
            }else{
                StringBuilder ind = new StringBuilder();
                int j = 0;
                while(j<i.length() && i.charAt(j) != ' '){
                    j++;
                }
                for(int k = j+1; k < i.length(); k++){
                    ind.append(i.charAt(k));
                }

                int index = Integer.valueOf(ind.toString());
                if(!close[index]){
                    close[index] = true;
                    total -= capacity[index];
                    available -= capacity[index] - current[index];
                }
            }
        }
        int res = 0;
        int curmax = 0;
        for(int i = 0; i < n; i++){
            if(cnt[i]>curmax){
                curmax = cnt[i];
                res = i;
            }
        }
        return res;
    }


    public static void main(String[] args){
        int[] capacity = new int[]{1, 3, 1, 3, 4};
        String[] instructions = new String[]{"package", "package", "closure 2", "package"};
        System.out.println(findBusiestProcessor(capacity, instructions));
    }
}
