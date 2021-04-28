/* Noah Park

Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

*/

class Solution {
    
    // Generic backtracking to add and remove values of a permutation.
    // Time and Space: O(n!) to maintain the permutations.
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        helper(nums, ans, new ArrayList<>());
        return ans;
    }
    
    public void helper(int[] nums, List<List<Integer>> perms, List<Integer> temp){
        if(temp.size() == nums.length) perms.add(new ArrayList<>(temp));
        else{
            for(int i = 0; i < nums.length; i++){
                if(temp.contains(nums[i])) continue;
                temp.add(nums[i]);
                helper(nums, perms, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
