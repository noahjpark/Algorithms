/* Noah Park

Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

*/

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> h = new HashMap<>(); // Map complements of numbers to indexes so we know what number will add to target
        for(int i = 0; i < nums.length; i++){ // iterate over all nums
            if(h.containsKey(target - nums[i])) return new int[] { i, h.get(target - nums[i]) }; // found the match
            else h.put(nums[i], i); // map the value
        }
        return new int[]{}; // instead of breaking, return nothing; this will never be hit
    }
}
