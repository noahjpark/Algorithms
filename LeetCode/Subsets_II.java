/* Noah Park

Given an integer array nums that may contain duplicates, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

*/

class Solution {
    
    List<List<Integer>> res = new ArrayList<>();
    
    // Intuition: Once the list is sorted, we can avoid duplicating subsets by maintaining a used array. This is an optimization over maintaining a building string in terms of literal runtime. Otherwise, approach with typical subset approach.
    // Time: O(2^n) length of nums.
    // Space: O(2^n) length of nums for result list.
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null || nums.length == 0) return res;
        
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        subsets(nums, new ArrayList<>(), 0, used);
        
        return res;
    }
    
    public void subsets(int[] nums, List<Integer> temp, int cur, boolean[] used) {
        res.add(new ArrayList<>(temp));
        
        for (int i = cur; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            
            used[i] = true;
            temp.add(nums[i]);
            subsets(nums, temp, i + 1, used);
            temp.remove(temp.size() - 1);
            used[i] = false;
        }
    }
}
