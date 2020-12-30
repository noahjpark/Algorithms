/* Noah Park

Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.

It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.

*/

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) return new ArrayList<>(); // edge cases
        
        List<List<Integer>> res = new ArrayList<>(); // return list
         
        helper(candidates, target, res, 0, new ArrayList<>()); // add all combinations
        
        return res;
    }
    
    public void helper(int[] candidates, int target, List<List<Integer>> res, int idx, List<Integer> temp) {
        if (target == 0) res.add(new ArrayList<>(temp)); // we have a combination that adds to target
        else if (target < 0) return; // stop if we don't add exactly to the target
        else {
            for (int i = idx; i < candidates.length; i++) { // iterate over valid choices
                temp.add(candidates[i]); // choose
                helper(candidates, target - candidates[i], res, i, temp); // continue choosing combinations
                temp.remove(temp.size() - 1); // backtrack
            }
        }
    }
}
