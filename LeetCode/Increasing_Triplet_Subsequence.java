/* Noah Park

Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.

*/

class Solution {
    
    // Intuition: Keep track of smallest number and second smallest number so far. 
    // Time: O(n) to iterate over nums.
    // Space: O(1) constant.
    public boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        
        for (int num : nums) {
            if (num > second) return true;
            else if (num <= first) first = num; // need to include equals so that second is never the same as first
            else if (num <= second) second = num; 
        }
        
        return false;
    }
    
    // Intuition: min represents the current minimum, second represents the second number in the sequence, also potentially a previous sequence. count represents the current count of values in a sequence.
    // Time: O(n) to iterate over nums.
    // Space: O(1) constant.
    public boolean increasingTriplet2(int[] nums) {
        int min = Integer.MAX_VALUE, count = 0, second = Integer.MAX_VALUE;
        
        for (int num : nums) {
            if (second != Integer.MAX_VALUE && num > second) return true;
            else if (num < min) { min = num; count = 0; }
            else if (num > min) { 
                if (second != Integer.MAX_VALUE && num > second) count++; 
                second = num; 
            }
            
            if (count == 2) return true;
        }
        
        return false;
    }
}
