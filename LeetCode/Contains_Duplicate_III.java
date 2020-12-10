/* Noah Park

Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.

*/

class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {   
        if (k == 0 || nums.length == 0) return false; // edge cases
        
        int n = nums.length;
        TreeSet<Long> set = new TreeSet<>(); // maintain sorted order for all windows k size
        
        // iterate over nums
        for (int i = 0; i < n; i++) {
            Long greatest = set.floor((long) (nums[i])); // lg k operation to get the largest value >= nums[i]
            Long least = set.ceiling((long) (nums[i])); // lg k operation to get the smallest value <= nums[i]
            
            // if within the bounds, return true
            if ((greatest != null && nums[i] - greatest <= t) || (least != null && least - nums[i] <= t)) return true;
            
            // add the new number to the set
            set.add((long) nums[i]);
            if (i >= k) set.remove((long) nums[i - k]); // remove the old one
        }
        
        // no nums valid to the duplicate
        return false;
    }
}
