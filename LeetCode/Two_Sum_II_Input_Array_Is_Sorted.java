/* Noah Park

Given an array of integers numbers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

Return the indices of the two numbers (1-indexed) as an integer array answer of size 2, where 1 <= answer[0] < answer[1] <= numbers.length.

You may assume that each input would have exactly one solution and you may not use the same element twice.

*/

class Solution {

    // Intuition: Utilize two pointers.
    // Time: O(n) to iterate over nums.
    // Space: O(1) constant.
    public int[] twoSum(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            if (nums[left] + nums[right] == target) return new int[]{ left + 1, right + 1 };
            else if (nums[left] + nums[right] < target) left++;
            else right--;
        }
        
        return new int[]{ -1, -1 };
    }
}
