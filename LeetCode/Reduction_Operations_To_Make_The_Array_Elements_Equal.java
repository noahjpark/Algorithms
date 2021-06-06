/* Noah Park

Given an integer array nums, your goal is to make all elements in nums equal. To complete one operation, follow these steps:

Find the largest value in nums. Let its index be i (0-indexed) and its value be largest. If there are multiple elements with the largest value, pick the smallest i.
Find the next largest value in nums strictly smaller than largest. Let its value be nextLargest.
Reduce nums[i] to nextLargest.
Return the number of operations to make all elements in nums equal.

*/

class Solution {
    
    // Intuition: Sort the array then we can linearly calculate how many updates we must do. For each largest number, we accumulate how many occurrences there are then add that total. Ex. 1122334455 -> we would add 2 for the 5s, then 4 to include the 4s, then 6 to include the 3s, then 8 to include the 2s, finally we ignore the 1s as they are what we are trying to reach. We add all of our values together for 2 + 4 + 6 + 8 = 20. This is due to the fact that once we add the 2 for the 5s, they become 4s and there are 4 4s. We do this without actually modifying the array.
    // Time: O(nlogn) for sorting the array.
    // Space: O(sort) for sorting.
    public int reductionOperations(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int res = 0, start = 0, n = nums.length;
        Arrays.sort(nums);
        while (start < n - 1 && nums[start] == nums[start + 1]) start++;
        
        for (int i = n - 1; i > start; i--) {
            int cur = nums[i];
            
            while (i - 1 >= 0 && nums[i - 1] == nums[i]) i--;
            
            res += (n - i);
        }
        
        return res;
    }
}
