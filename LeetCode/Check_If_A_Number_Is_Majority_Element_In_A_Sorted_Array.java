/* Noah Park

Given an array nums sorted in non-decreasing order, and a number target, return True if and only if target is a majority element.

A majority element is an element that appears more than N/2 times in an array of length N.

*/

class Solution {
    public boolean isMajorityElement(int[] nums, int target) {
        int start = binarySearchStart(nums, target), end = start + nums.length / 2; // binary search the starting index, then check if there are more than n/2 occurrences by seeing if that index also contains the same index
        
        if (start == -1 || end >= nums.length) return false; // if target not in the array or the required end index for the target number occurrences can't fit in the array bounds, return false
        
        return nums[end] == target;
    }
    
    // modified binary search to find the starting index of the target number if it is in the array, else -1
    public int binarySearchStart(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] >= target) right = mid;
            else left = mid + 1;
        }
        
        return nums[left] == target ? left : -1;
    }
}
