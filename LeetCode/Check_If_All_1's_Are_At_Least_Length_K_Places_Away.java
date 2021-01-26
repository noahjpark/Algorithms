/* Noah Park

Given an array nums of 0s and 1s and an integer k, return True if all 1's are at least k places away from each other, otherwise return False.

*/

class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        int zeroes = k; // spaces between ones, initialize to k to begin with for the first one
        
        // iterate over nums
        for (int num : nums) {
            if (num == 0) zeroes++; // count a space
            else {
                if (zeroes < k) return false; // we have a one, if there weren't enough spaces, return false
                zeroes = 0; // reset spaces
            }
        }
        
        return true; // all 1s are k spaces away from one another
    }
}
