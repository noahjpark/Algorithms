/* Noah Park

A peak element is an element that is strictly greater than its neighbors.

Given an integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.

You may imagine that nums[-1] = nums[n] = -∞.

*/

class Solution {
    // binary search
    public int findPeakElement(int[] nums) {
        if (nums.length == 1) return 0; // edge case
        
        // two pointers
        int left = 0, right = nums.length - 1;
        
        // iterate until left == right
        // we always try to move towards a local maximum this will result in either finding a peak or continuing to iterate until we get to the left or right edge
        // which will then be the peak
        while (left < right) {
            int mid = left + (right - left) / 2; // get the midpoint
            if (nums[mid] < nums[mid + 1]) left = mid + 1; // if we find a local max to the right, go right
            else right = mid; // else go left
        }
        
        return left;
    }
    
    public int findPeakElementLinear(int[] nums) {
        if (nums.length == 1) return 0; // edge case
        
        // iterate through the numbers
        for (int i = 0; i < nums.length; i++) {
            // if we are at the start and the next number is smaller or at the end and the previous number is smaller, we have our first peak
            if ((i == 0 && i + 1 < nums.length && nums[i + 1] < nums[i]) || (i == nums.length - 1 && i - 1 > -1 && nums[i - 1] < nums[i])) return i;
            else if (i > 0 && i < nums.length - 1 && nums[i - 1] < nums[i] && nums[i + 1] < nums[i]) return i; // if we find a peak return it
        }
        
        return -1; // no peak found
    }
}
