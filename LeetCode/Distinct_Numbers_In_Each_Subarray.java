/* Noah Park

Given an integer array nums and an integer k, you are asked to construct the array ans of size n-k+1 where ans[i] is the number of distinct numbers in the subarray nums[i:i+k-1] = [nums[i], nums[i+1], ..., nums[i+k-1]].

Return the array ans.

*/

class Solution {
    
    // Intuition: Utilize counting sort to maintain the frequencies of the numbers in our current window. Distinct is maintained as follows: increased if the map at that position was 0, decreased if it becomes 0. Distinct will maintain the number of distinct numbers in the window.
    // Time: O(n) to iterate through the numbers.
    // Space: O(n) for the resulting list otherwise O(1) excluding the result since map is constant.
    public int[] distinctNumbers(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[]{};
        
        int n = nums.length, s = 0, e = 0, i = 0, dis = 0;
        int[] res = new int[n - k + 1], map = new int[100001];
        
        for (; e < nums.length; e++) {
            if (map[nums[e]] == 0) dis++;
            map[nums[e]]++;
            
            if (e - s == k) {
                map[nums[s]]--;
                if (map[nums[s]] == 0) dis--;
                s++;
            }
            
            if (e - s == k - 1) res[i++] = dis;
        }
        
        return res;
    }
    
    // Intuition: Same as the optimal above but replaces the constant int map with a hash map.
    // Time: O(n) to iterate through the numbers.
    // Space: O(n) for the resulting list/hash map.
    public int[] distinctNumbers2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new int[]{};
        
        int n = nums.length, s = 0, e = 0, i = 0;
        int[] res = new int[n - k + 1];
        Map<Integer, Integer> map = new HashMap<>();
        
        for (; e < nums.length; e++) {
            map.put(nums[e], map.getOrDefault(nums[e], 0) + 1);
            
            if (e - s == k) {
                map.put(nums[s], map.getOrDefault(nums[s], 0) - 1);
                if (map.get(nums[s]) == 0) map.remove(nums[s]);
                s++;
            }
            
            if (e - s == k - 1) res[i++] = map.size();
        }
        
        return res;
    }
}
