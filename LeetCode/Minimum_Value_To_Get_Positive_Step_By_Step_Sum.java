/* Noah Park

Given an array of integers nums, you start with an initial positive value startValue.

In each iteration, you calculate the step by step sum of startValue plus elements in nums (from left to right).

Return the minimum positive value of startValue such that the step by step sum is never less than 1.

*/

class Solution {
    public int minStartValue(int[] nums) {
        for(int i = 1; i < nums.length; i++)
            nums[i] = nums[i - 1] + nums[i];
        
        int min = Integer.MAX_VALUE;
        for(int num : nums)
            min = Math.min(min, num);
        
        if(min >= 0) return 1;
        
        return 1 - min;
    }
}
