/* Noah Park

You are given an integer array nums where the largest integer is unique.

Determine whether the largest element in the array is at least twice as much as every other number in the array. If it is, return the index of the largest element, or return -1 otherwise.

*/

class Solution {
    
    // Intuition: Store the max/maxIdx through an initial pass utilizing counting sort. Then look from max - 1 until we find the second largest number and ensure max is at least twice this.
    // Time: O(n) to iterate over nums.
    // Space: O(1) constant.
    public int dominantIndex(int[] nums) {
        int[] freq = new int[101];
        int max = -1, maxIdx = -1;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) { max = nums[i]; maxIdx = i; }
            freq[nums[i]]++;
        }
        
        if (freq[max] != 1) return -1;
        
        int i = max - 1;
        while (i >= 0 && freq[i] == 0) i--;
        
        return (i == -1 || i * 2 <= max) ? maxIdx : -1;
    }
}
