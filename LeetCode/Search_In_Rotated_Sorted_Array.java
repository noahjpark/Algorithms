/* Noah Park

There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

Given the array nums after the rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

*/

class Solution {
    
    // Intuition: Two part binary search. First finds the minimum element so we have a baseline for where our array begins. Then we utilize a typical binary search from that starting point while taking into account modular arithmetic to maintain the bounds of the array.
    // Time: O(log n) two binary searches.
    // Space: O(1) constant.
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > nums[right]) left = mid + 1;
            else right = mid;
        }
        
        int start = left; // actual minimum in the sorted array
        left = 0;
        right = nums.length - 1;
        
        while (left <= right) {
            int mid = (left + (right - left) / 2), actual = (start + mid) % nums.length;
            
            if (nums[actual] == target) return actual;
            else if (nums[actual] < target) left = mid + 1;
            else right = mid - 1;
        }
        
        return -1;
    }
}
