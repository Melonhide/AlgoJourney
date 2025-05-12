package interviewPrepare;

public class bestTimeToBuyAndSellStockVariant_121 {
    //Given two arrays departures and returns where departures[i] and returns[i] are ticket prices for departing  and
    // returning flights on the ith day. Minimize your cost by choosing a single day to buy a departure flight and
    // choosing a different day in the future to by a returning flight;
    // Example:
    // Input: departures = [1,2,3,4], returns = [4,3,2,1]
    // Output: 2

    public static int BestTimeToBuyFlightTickets(int[] departures, int[] returns){
        int ans = Integer.MAX_VALUE;
        int start = departures[0];
        for(int i = 1; i < departures.length; i++){
            ans = Math.min(ans, start+returns[i]);
            start = Math.min(start, departures[i]);
        }
        return ans;
    }

    public static void main(String[] args){
        int[] departures = new int[]{4,3,5,11,2};
        int[] returns = new int[]{1,6,10,2,9};
        System.out.println(BestTimeToBuyFlightTickets(departures, returns));
    }
}
