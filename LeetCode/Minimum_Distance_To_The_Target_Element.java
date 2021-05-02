/* Noah Park

Given an integer array nums (0-indexed) and two integers target and start, find an index i such that nums[i] == target and abs(i - start) is minimized. Note that abs(x) is the absolute value of x.

Return abs(i - start).

It is guaranteed that target exists in nums.

*/

class Solution {
    
    // Intuition: Move outward from the starting index to greedily find the shortest path.
    // Time: O(n) to go over nums.
    // Space: O(1) constant.
    public int getMinDistance(int[] nums, int target, int start) {
        if (nums[start] == target) return 0;
        
        int left = start - 1, right = start + 1;
        
        while (left >= 0 || right < nums.length) {
            if (left >= 0 && nums[left] == target) return Math.abs(left - start);
            if (right < nums.length && nums[right] == target) return Math.abs(right - start);
            
            left--;
            right++;
        }
        
        return -1;
    }
}
