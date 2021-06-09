/* Noah Park

Given an array of n integers nums and an integer target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

*/

class Solution {
    
    // Intuition: Misleading problem statement. Any triplet that is smaller than target in the array is valid.
    // Time: O(n^2 + nlogn) for sorting and searching -> O(n^2).
    // Space: O(sort) for sorting.
    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        
        int res = 0, n = nums.length;
        Arrays.sort(nums);
        
        for (int i = 0; i < n - 2; i++) {
            int j = i + 1, k = n - 1;
            
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] < target) { res += k - j; j++; } // the number of triplets we can make that are less than target is all values in the range left + 1 through right (inclusive) thus right-left
                else k--; // too large, we must decrease our value.
            }
        }
        
        return res;
    }
    
    // Intuition: Brute force calculation of all triplets.
    // Time: O(n^3) to make comparisons.
    // Space: O(1) constant.
    public int threeSumSmaller2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        
        int res = 0, n = nums.length;
        
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] < target) res++;
                }
            }
        }
        
        return res;
    }
}
