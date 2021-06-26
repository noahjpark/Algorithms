/* Noah Park

Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.

*/

class Solution {
    
    List<List<Integer>> res = new ArrayList<>();
    
    // Intuition: Exact same as below except we sort the array to help avoid duplicates. In this way, we check to see if at a duplicate value, we included the previous one, if so, we create the original permutation. Otherwise, we skip it.
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        permute(nums, used, new ArrayList<>());
        
        return res;
    }
    
    public void permute(int[] nums, boolean[] used, List<Integer> temp) {
        if (temp.size() == nums.length) { res.add(new ArrayList<>(temp)); return; }
        
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
                used[i] = true;
                temp.add(nums[i]);
                
                permute(nums, used, temp);
                
                temp.remove(temp.size() - 1);
                used[i] = false;
            }
        }
    }
    
    // Intuition: Same approach as the original permutations problem but keeps track of unique permutations by creating a string equivalent of the permutation and maintaining a set of these strings to ensure uniqueness. An optimization can be made to improve runtime fairly considerably by sorting the nums list and adding a clause to check if the previous number is matching and not used then we skip it. This avoids the creation of duplicate permutations. In this way we don't mix up duplicate values.
    // Time: O(n!) to create the permutations.
    // Space: O(n!) to maintain the output list.
    public List<List<Integer>> permuteUnique2(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        
        Set<String> set = new HashSet<>();
        boolean[] used = new boolean[nums.length];
        
        permute(nums, used, set, new ArrayList<>(), "");
        
        return res;
    }
    
    public void permute(int[] nums, boolean[] used, Set<String> set, List<Integer> temp, String s) {
        if (temp.size() == nums.length) { 
            if (!set.contains(s)) {
                set.add(s);
                res.add(new ArrayList<>(temp));
            }
            return; 
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                used[i] = true;
                temp.add(nums[i]);
                
                permute(nums, used, set, temp, s + nums[i]);
                
                temp.remove(temp.size() - 1);
                used[i] = false;
            }
        }
    }
    
}
