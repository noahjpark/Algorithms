/* Noah Park

Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

You may assume the input array always has a valid answer.

*/

class Solution {
    
    // Intuition: The array is in descending order from odd indices to the end then even indices to the end. Notice that to avoid duplicates for adjacent elements, we always need to put the next largest element in the spot in front of the adjacent spot. As we move through the array (assuming we have valid input), we will reach the start again with smaller numbers to ensure we don't have duplicates next to each other or break the rules.
    // Time: O(n) to iterate over nums since map is constant.
    // Space: O(1) map is constant.
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 0) return;
        
        int[] map = new int[5001];
        int max = nums[0], n = nums.length;
        
        for (int num : nums) { map[num]++; max = Math.max(num, max); }
        
        for (int i = 1; i < n; i += 2) {
            nums[i] = max;
            map[max]--;
            while (max > -1 && map[max] == 0) max--;
        }
        
        for (int i = 0; i < n; i += 2) {
            nums[i] = max;
            map[max]--;
            while (max > -1 && map[max] == 0) max--;
        }
    }
 
}
