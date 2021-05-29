/* Noah Park

Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.

*/

class Solution {
    
    // Intuition: Utilize dp to maintain the longest substring at each given point. I optimized my initial 2D dp array (nxm) since we only need the most recent row to use less space overall. If at the given point, we have a match between two numbers, we check the past row at the past column to see if we have a continuing sequence or a new one.
    // Time: O(n*m) iterating over each number in nums2 for each number in nums1.
    // Space: O(min(m, n)) call findLength to ensure nums1 is the longer array so that we minimize the space usage.
    public int findLength(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) return 0;
        if (nums1.length < nums2.length) return findLength(nums2, nums1);
        
        int res = 0, n = nums1.length, m = nums2.length;
        int[] prev = new int[m], cur = new int[m];
        
        for (int i = 0; i < m; i++)
            if (nums1[0] == nums2[i]) prev[i] = 1;
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int toAdd = j == 0 ? 0 : prev[j - 1], val = nums1[i] == nums2[j] ? 1 + toAdd : 0;
                cur[j] = val;
                res = Math.max(cur[j], res);
            }
            
            prev = cur;
            cur = new int[m];
        }
        
        return res;
    }
}
