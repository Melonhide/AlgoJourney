package interviewPrepare;

import java.util.HashMap;
import java.util.Map;

public class randomPickWeightVariant_528 {
    // Given a list of (city, population), implement pickIndex()
    // to randomly return a city name with probability proportional to its population.
    //
    // Example:
    // Input: [["Seattle", 500], ["New York", 900], ["Los Angeles", 400]]
    // Output (random): "New York" with 900/(500+900+400), etc.\

    public static class RandomPickCity{

        String[] cities;
        int[] nums;
        int total;

        public RandomPickCity(String[] c, int[] pop){
            cities = c;
            nums = new int[pop.length];
            for(int i = 0, cur = 0; i < pop.length; i++){
                nums[i] = cur + pop[i];
                cur = nums[i];
            }
            total = nums[nums.length-1];
        }

        public String randomPick(){
            double randomNumber = Math.random()*total;
            return cities[find(randomNumber)];
        }

        public int find(double target){
            int l = 0;
            int r = nums.length-1;
            int find = -1;
            while(l<=r){
                int m = (l+r)/2;
                if(nums[m]>=target){
                    find = m;
                    r = m-1;
                }else{
                    l = m+1;
                }
            }
            return find;
        }
    }

    public static void main(String[] args) {
        String[] c = new String[] {"Seattle", "New York", "Los Angeles"};

        int[] p  = new int[] {500, 900, 400};

        RandomPickCity sol = new RandomPickCity(c, p);

        Map<String, Integer> freq = new HashMap<>();
        int trials = 1_000_000;

        for (int i = 0; i < trials; i++) {
            String city = sol.randomPick();
            freq.put(city, freq.getOrDefault(city, 0) + 1);
        }

        System.out.println("Sampling Distribution:");
        for (String city : freq.keySet()) {
            double probability = freq.get(city) * 100.0 / trials;
            System.out.printf("%-12s: %.2f%%\n", city, probability);
        }
    }

}
