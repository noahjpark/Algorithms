/* Noah Park

An array A is larger than some array B if for the first index i where A[i] != B[i], A[i] > B[i].

For example, consider 0-indexing:

[1,3,2,4] > [1,2,2,4], since at index 1, 3 > 2.
[1,4,4,4] < [2,1,1,1], since at index 0, 1 < 2.
A subarray is a contiguous subsequence of the array.

Given an integer array nums of distinct integers, return the largest subarray of nums of length k.

*/

class Solution {
    // Time: O(n) two pass solution
    // Space: O(1) not including the output array
    public int[] largestSubarray(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k > nums.length) return new int[]{}; // edge cases
        if (k == nums.length) return nums; // optimize if k is the size of nums, the maximum possible array is simply nums
        
        int[] res = new int[k];
        int idx = -1, n = nums.length;
        
        // since all numbers in nums are distinct, we simply need to find the largest starting value that allows k elements from nums
        for (int i = 0; i < n - k + 1; i++) {
            if (idx == -1) idx = i;
            else if (nums[i] > nums[idx]) idx = i;
        }
        
        // populate the resulting array
        for (int i = idx; i < idx + k; i++) 
            res[i - idx] = nums[i];
        
        return res;
    }
}
