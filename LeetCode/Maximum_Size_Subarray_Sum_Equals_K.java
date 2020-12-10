/* Noah Park

Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

Note:
The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

*/

class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        // edge case checking
        if (nums == null || nums.length == 0) return 0;
        
        int max = 0, sum = 0; // max is the max length subarray, sum is a prefix sum
        Map<Integer, Integer> map = new HashMap<>(); // maps the current sum to the corresponding index
        
        // iterate over nums
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i]; // prefix sum
            if (sum == k) max = i + 1; // if the sum from 0 to i matches k, our max subarray would be i + 1 since we can go from the start
            else if (map.containsKey(sum - k)) max = Math.max(i - map.get(sum - k), max); // else if we have seen a point that was exactly -k from our sum, we already have a smaller array to take, we can use that as our potential new subarray
            if (!map.containsKey(sum)) map.put(sum, i); // put the sum if we have not seen it yet as to only keep the closest to the start of any given sum in the map - ensures largeness
        }
        
        return max;
    }
}
