/* Noah Park

Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.

Follow up: Could you implement a solution with a linear runtime complexity and without using extra memory?

*/

class Solution {
    
    // Intuition: Bit manipulation. Any number xor'd with itself becomes 0. The only bits remaining will be the value that didn't appear twice.
    // Time: O(n) to check all of nums.
    // Space: O(1) constant.
    public int singleNumber(int[] nums) {
        int res = 0;
        
        for (int num : nums)
            res ^= num;
        
        return res;
    }
}
