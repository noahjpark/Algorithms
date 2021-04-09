/* Noah Park

Alice has n candies, where the ith candy is of type candyType[i]. Alice noticed that she started to gain weight, so she visited a doctor.

The doctor advised Alice to only eat n / 2 of the candies she has (n is always even). Alice likes her candies very much, and she wants to eat the maximum number of different types of candies while still following the doctor's advice.

Given the integer array candyType of length n, return the maximum number of different types of candies she can eat if she only eats n / 2 of them.

*/

class Solution {
    
    // Intuition: Add all candies to a set. Take the smaller of candies.length / 2 and the size of the set. Optimize to stop searching when we reach a point where the set size is candies.length / 2.
    // Time: O(n) single pass through candies
    // Space: O(n) to put the candies in the set
    public int distributeCandies(int[] candies) {
        int n = candies.length / 2;
        Set<Integer> set = new HashSet<>();
        
        for (int candy : candies) {
            set.add(candy);
            if (set.size() == n) return n;   
        }
        
        return set.size();
    }
    
    // Intuition: map candy frequencies. Take the smaller of candies.length / 2 and the size of the map. Optimize to stop searching when we reach a point where the map size is candies.length / 2.
    // Time: O(n) single pass through candies
    // Space: O(n) to map the candies
    public int distributeCandies2(int[] candies) {
        int n = candies.length / 2;
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int candy : candies) {
            map.put(candy, map.getOrDefault(candy, 0) + 1);
            if (map.size() == n) return n;
        }
        
        return map.size();
    }
}
