/* Noah Park

Given an array nums of integers and integer k, return the maximum sum such that there exists i < j with nums[i] + nums[j] = sum and sum < k. If no i, j exist satisfying this equation, return -1.

*/

class Solution {
    public int twoSumLessThanK(int[] nums, int k) {
        // sort nums to use the two pointer technique
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1, max = -1; // max will be the return value, -1 if nothing is found
        
        // iterate until left is larger than right
        while (left < right) {
            int sum = nums[left] + nums[right]; // current sum
            
            // if the sum is smaller than k, we can update max to be the larger of the two
            // since we have a small enough sum and we are looking for the largest small enough sum, we only move left to find a new potential larger sum
            if (sum < k) { max = Math.max(max, sum); left++; }
            else if (sum < max) left++; // sum is smaller than the current max and needs to be increased
            else if (sum >= k) right--; // sum is larger or equal to k and needs to be decreased
        }
        
        return max;
    }
}
