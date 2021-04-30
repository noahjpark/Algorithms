/* Noah Park

Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

Follow up: Could you write an algorithm with O(log n) runtime complexity?

*/

class Solution {
    
    // Intuition: Utilize modified binary search to find both the start and end of the target range.
    // Time: O(log n) two passes.
    // Space: O(1) constant.
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[]{ -1, -1 };
        
        int start = findStart(nums, target), end = findEnd(nums, target);
        
        return nums[start] == target ? new int[]{ start, end } : new int[]{ -1, -1 }; // if start is not a index of target, target wasn't in nums.
    }
    
    // Modified binary search to find the end of the range of target. Try to move left over until it reaches the first instance of not target (assuming target is in the array). If left is 0 because the array is size 1 or target is not in the array we don't want to return a negative value. If the array only contains target values, left will be on the leftmost target value at the end. In these two cases we maintain left and return it as the last index. Otherwise, we are one in front of the last index and return left - 1.
    public int findEnd(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] <= target) left = mid + 1;
            else right = mid;
        }
        
        return (left == 0 || nums[left] == target) ? left : left - 1;
    }
    
    // Straightforward modified binary search to find the front of a range of targets.
    public int findStart(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] < target) left = mid + 1;
            else right = mid;
        }
        
        return left;
    }
}
