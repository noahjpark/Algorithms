/* Noah Park

You are given an array of positive integers nums and want to erase a subarray containing unique elements. The score you get by erasing the subarray is equal to the sum of its elements.

Return the maximum score you can get by erasing exactly one subarray.

An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).

*/

class Solution {
    
    // Intuition: Sliding window while maintaining a map of frequencies of each number and the current sum. If we add a duplicate, move the start of the window and update the sum. Maximize this sum at each unique sliding window.
    // Time: O(n) to iterate over nums.
    // Space: O(1) constant since the map is constant. If we used a hashmap, we would have O(n) space.
    public int maximumUniqueSubarray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int s = 0, e = 0, n = nums.length, max = 0, cur = 0;
        int[] map = new int[10001];
        
        while (e < n) {
            map[nums[e]]++;
            cur += nums[e];
            
            while (map[nums[e]] > 1) {
                cur -= nums[s];
                map[nums[s++]]--;
            }
            
            max = Math.max(cur, max);
                
            e++;
        }
        
        return max;
    }
}
