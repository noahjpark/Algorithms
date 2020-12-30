/* Noah Park

Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

*/

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>(); // edge cases
        
        List<Integer> res = new ArrayList<>(); // return list
        
        for (int i = 0; i < nums.length; i++) {
            int idx = Math.abs(nums[i]) - 1; // idx + 1 is the number we are currently on
            if (nums[idx] < 0) res.add(Math.abs(idx + 1)); // if the number has already been marked, we have a duplicate
            nums[idx] = -nums[idx]; // mark the number as negative
        }
        
        return res;
    }
    
    public List<Integer> findDuplicatesSubOptimal(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>(); // edge cases
    
        List<Integer> res = new ArrayList<>(); // return list
        
        Arrays.sort(nums); // sort the array (n log n)
        
        // iterate through and keep elements that are not duplicates adjacently
        int i = 1;
        while (i < nums.length) {
            if (nums[i] == nums[i - 1]) {
                res.add(nums[i]);
                while (i < nums.length && nums[i] == nums[i - 1]) i++;
            }
            else i++;
        }
        
        return res;
    }
}
