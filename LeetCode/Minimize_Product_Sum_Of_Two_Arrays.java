/* Noah Park

The product sum of two equal-length arrays a and b is equal to the sum of a[i] * b[i] for all 0 <= i < a.length (0-indexed).

For example, if a = [1,2,3,4] and b = [5,2,3,1], the product sum would be 1*5 + 2*2 + 3*3 + 4*1 = 22.
Given two arrays nums1 and nums2 of length n, return the minimum product sum if you are allowed to rearrange the order of the elements in nums1. 

*/

class Solution {
    
    // Intuition: To minimize the product sum, we want to pair the smallest elements from nums1 with the largest in nums2. Sort the numbers in each array and pair accordingly.
    // Time: O(nlogn) to sort both arrays then do the single pass.
    // Space: O(sort) for sorting.
    public int minProductSum(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length == 0) return 0;
        
        int res = 0, n = nums1.length;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        for (int i = 0; i < n; i++)
            res += (nums1[i] * nums2[n - 1 - i]);
        
        return res;
    }
}
