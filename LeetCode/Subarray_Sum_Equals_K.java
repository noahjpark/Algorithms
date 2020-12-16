/* Noah Park

Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.

*/

class Solution {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0; // edge cases
        
        // store number of continuous subarrays that match a sum
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length, sum = 0, total = 0;
        
        // iterate over the numbers
        for (int i = 0; i < n; i++) {
            sum += nums[i]; // prefix sum
            
            if (sum == k) total++; // if the sum == k, we have a match
            if (map.containsKey(sum - k)) total += map.get(sum - k); // also check if we contain a sum that matches k but is in between the start and current value, add the number of contiguous subarrays matched to this sum
            map.put(sum, map.getOrDefault(sum, 0) + 1); // include this as a subarray to the map
        }
        
        return total;
    }
}
