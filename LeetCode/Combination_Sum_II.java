/* Noah Park

Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

Each number in candidates may only be used once in the combination.

Note: The solution set must not contain duplicate combinations.

*/

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) return new ArrayList<>(); // edge cases
        
        List<List<Integer>> res = new ArrayList<>(); // return list
        
        Arrays.sort(candidates); // sort so that we can skip duplicates easily
        helper(candidates, target, res, 0, new ArrayList<>()); // populate all unique combinations
        
        return res;
    }
    
    public void helper(int[] candidates, int target, List<List<Integer>> res, int idx, List<Integer> temp) {
        if (target == 0) res.add(new ArrayList<>(temp)); // found a combination, add a shallow copy
        else if (target < 0) return; // no valid combination
        else {
            for (int i = idx; i < candidates.length; i++) {
                if (i > idx && candidates[i] == candidates[i - 1]) continue; // skip duplicates
                temp.add(candidates[i]); // add current number
                helper(candidates, target - candidates[i], res, i + 1, temp); // go to the next number
                temp.remove(temp.size() - 1); // backtrack
            }
        }
    }
}
