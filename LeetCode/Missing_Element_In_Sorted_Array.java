/* Noah Park

Given an integer array nums which is sorted in ascending order and all of its elements are unique and given also an integer k, return the kth missing number starting from the leftmost number of the array.



*/

class Solution {
    
    // Intuition: Modified binary search. Reduce the problem space and k while shifting the left and right sides of our problem inwards. Whenever we don't include the left, we must decrease k since we are excluding missing values then. Otherwise we continuously bring left and right inwards to the midpoint. We have to use right - 1 as the loop bound to avoid infinite looping.
    // Time: O(log n) for binary search.
    // Space: O(1) constant.
    public int missingElement(int[] nums, int k) {
        int n = nums.length, range = nums[n - 1] - nums[0] + 1, missing = range - n, left = 0, right = nums.length - 1;
        if (missing < k) return nums[n - 1] + k - missing;
        
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            range = nums[mid] - nums[left] + 1;
            missing = range - (mid - left + 1);
            
            if (missing < k) { k -= missing; left = mid; }
            else right = mid;
        }
        
        return nums[left] + k;
    }
}
