/* Noah Park

Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.

*/

class Solution {
    
    // Intuition: Focused bucket sort. I originally attempted counting sort but naturally MLE'd on wide cases since we would store many useless values as well. Instead, we can store all values in a set then bfs outwards from each number removing from the set on each visit to find the longest sequence.
    // Time: O(n) two passes.
    // Space: O(n) to maintain the set.
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int res = 0;
        Set<Integer> set = new HashSet<>();
        
        for (int num : nums)
            set.add(num);
        
        for (int num : nums) {
            int l = num - 1, r = num + 1;
            
            while (set.contains(l)) set.remove(l--);
            while (set.contains(r)) set.remove(r++);
            
            res = Math.max(res, r - l - 1);
            if (set.size() == 0) break;
        }
        
        return res;
    }
}
