/* Noah Park

The XOR total of an array is defined as the bitwise XOR of all its elements, or 0 if the array is empty.

For example, the XOR total of the array [2,5,6] is 2 XOR 5 XOR 6 = 1.
Given an array nums, return the sum of all XOR totals for every subset of nums. 

Note: Subsets with the same elements should be counted multiple times.

An array a is a subset of an array b if a can be obtained from b by deleting some (possibly zero) elements of b.

*/

class Solution {
    
    // Calculate the subsets without maintaining all values.
    // Time: O(2^n) to make the calculations.
    // Space: O(n) depth of recursion.
    public int subsetXORSum(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return subset(nums, 0, 0);
    }
    
    public int subset(int[] nums, int i, int val) {
        if (i == nums.length) return val;
        return subset(nums, i + 1, val ^ nums[i]) + subset(nums, i + 1, val);
    }
    
    // Intuition: Build all subsets and iterate over them to add the xor values.
    // Time: O(2^n) to build the subsets.
    // Space: O(2^n) to maintain the subsets.
    public int subsetXORSum2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int n = nums.length, res = 0;
        List<List<Integer>> s = new ArrayList<>();
        
        subset(nums, s, new ArrayList<>(), 0);
        
        for (List<Integer> sub : s) {
            int temp = 0;
            
            for (Integer i : sub)
                temp ^= i;
            
            res += temp;
        }
        
        return res;
    }
    
    public void subset(int[] nums, List<List<Integer>> s, List<Integer> temp, int idx) {
        s.add(new ArrayList<>(temp));
        
        for (int i = idx; i < nums.length; i++) {
            temp.add(nums[i]);
            subset(nums, s, temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}
