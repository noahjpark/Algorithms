/* Noah Park

Given an array nums with n integers, your task is to check if it could become non-decreasing by modifying at most one element.

We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for every i (0-based) such that (0 <= i <= n - 2).

*/

class Solution {
    
    // Intuition: Greedy approach -> We only care about two numbers that are out of order. When we reach this out of order problem, we greedily try to change the earlier appeearing element that is failing the order to the smaller value in front of it as long as that doesn't break the order of the preceding element before it. If it does, we must increase the value of the smaller element. If we have already modified an element and attempt to modify another, we can break from the loop.
    public boolean checkPossibility(int[] nums) {
        int order = 0;
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                order++;
                
                if (i - 2 < 0 || (i - 2 >= 0 && nums[i - 2] <= nums[i])) nums[i - 1] = nums[i];
                else nums[i] = nums[i - 1];
            }
            
            if (order > 1) break;
        }
        
        return order < 2;
    }
}
