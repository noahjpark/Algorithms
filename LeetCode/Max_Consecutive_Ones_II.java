/* Noah Park

Given a binary array nums, return the maximum number of consecutive 1's in the array if you can flip at most one 0.

*/

class Solution {
    
    // Intuition: Essentially modified sliding window. Maintain a maximum length, zero index, starting subsequence index, and flipped boolean. The flipped tells us if we have flipped any zeroes yet. On the first flip, we can update the zeroIdx to the first occurrence of a zero. Otherwise, on any other zero, we take the length of the subsequence where everything but 1 spot is a 1, then update the start to no longer include the old zeroIdx, and update the zeroIdx to the zero we are currently on. As a whole, each time we reach a zero, we are moving our sliding window forward.
    // Time: O(n) to iterate over nums.
    // Space: O(1) constant.
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, zeroIdx = -1, start = 0;
        boolean flipped = false;
        
        for (int i = 0; i < nums.length; i++) {
            if (!flipped && nums[i] == 0) {
                flipped = true;
                zeroIdx = i;
            } else if (flipped && nums[i] == 0) {
                max = Math.max(max, i - start);
                start = zeroIdx + 1;
                zeroIdx = i;
            }
        }
        
        return Math.max(max, nums.length - start);
    }
}
