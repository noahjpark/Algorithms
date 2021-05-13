/* Noah Park

Given a group of values, the entropy of the group is defined as the formula as following:



where P(x) is the probability of appearance for the value x.

The exercise is to calculate the entropy of a group. Here is one example.

the input group:  [1, 1, 2, 2]

the probability of value 1 is  2/4 = 1/2
the probability of value 2 is  2/4 = 1/2

As a result, its entropy can be obtained by:  - (1/2) * log2(1/2) - (1/2) * log2(1/2) = 1/2 + 1/2 = 1

Note: the precision of result would remain within 1e-6.

*/

class Solution {
    
    // Intuition: Map frequencies to get the probabilities to utilize the formula.
    // Time: O(n) to iterate through the inputs then to iterate over the map.
    // Space: O(n) to maintain the frequencies.
    public double calculateEntropy(int[] input) {
        if (input == null || input.length == 0) return 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        int n = input.length;
        double res = 0;
        
        for (int num : input) 
            map.put(num, map.getOrDefault(num, 0) + 1);
        
        for (Integer key : map.keySet()) {
            double p = (double) map.get(key) / (double) n;
            res += -(p * (Math.log(p) / Math.log(2)));
        }
        
        return res;
    }
}
