/* Noah Park

You are given two non-increasing 0-indexed integer arrays nums1​​​​​​ and nums2​​​​​​.

A pair of indices (i, j), where 0 <= i < nums1.length and 0 <= j < nums2.length, is valid if both i <= j and nums1[i] <= nums2[j]. The distance of the pair is j - i​​​​.

Return the maximum distance of any valid pair (i, j). If there are no valid pairs, return 0.

An array arr is non-increasing if arr[i-1] >= arr[i] for every 1 <= i < arr.length.

*/

class Solution {
    
    // Intuition: Since the arrays are sorted, we can utilize a two pointer solution. When j is too small, we increase j, if the value at i is too small we increase i, otherwise we attempt to update max and move j forward.
    // Time: O(n) to iterate over the longer of nums1 or nums2.
    // Space: O(1) constant.
    public int maxDistance(int[] nums1, int[] nums2) {
        int i = 0, j = 0, n = nums1.length, m = nums2.length, max = 0;
        
        while (i < n && j < m) {
            if (j < i) j++;
            else if (nums2[j] < nums1[i]) i++;
            else if (nums2[j] >= nums1[i]) max = Math.max(max, j++ - i);
        }
        
        return max;
    }
}
