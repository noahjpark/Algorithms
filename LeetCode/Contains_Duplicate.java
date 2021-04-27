/* Noah Park

Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.

*/

class Solution {
    
    // Intuition: Sort then compare adjacent elements.
    // Time: O(n log n) for sorting.
    // Space: O(1) since not objects?
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i = 1; i < nums.length; i++){
            if(nums[i - 1] == nums[i]) return true;
        }
        return false;
    }
    
}
