/* Noah Park

Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.

Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

*/

class Solution {
    
    // Intuition: Find the degree of the array and maintain the leftmost/rightmost index of each value in the array. Then we do a final pass to minimize the difference between the right/left index of each value in the array.
    // Time: O(n) three passes.
    // Space: O(1) constant but would be O(n) if different constraints.
    public int findShortestSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int[] map = new int[50000];
        Integer[] left = new Integer[50000], right = new Integer[50000];
        int deg = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            map[nums[i]]++;
            deg = Math.max(deg, map[nums[i]]);
            if (left[nums[i]] == null) left[nums[i]] = i;
        }
        
        for (int i = n - 1; i >= 0; i--)
            if (right[nums[i]] == null) right[nums[i]] = i;
        
        int res = n;
        
        for (int i = 0; i < n; i++)
            if (map[nums[i]] == deg) res = Math.min(res, right[nums[i]] - left[nums[i]] + 1);
        
        return res;
    }
}
