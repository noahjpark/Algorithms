/* Noah Park

Given an integer array arr, you should partition the array into (contiguous) subarrays of length at most k. After partitioning, each subarray has their values changed to become the maximum value of that subarray.

Return the largest sum of the given array after partitioning.

*/

class Solution {
    // Intuition: At each point we look back up to k places and find the largest value. At each potential largest value, we add it to the largest sum from the point we had stored previously (i - j). mem[i] is the largest sum we have been able to make up to mem[i] and including that value at i.
    // Time: O(n*k) We iterate k places for each position in the arr.
    // Space: O(n) for the memoized array.
    public int maxSumAfterPartitioning(int[] arr, int k) {
        if (arr == null || arr.length == 0) return 0;
        
        int n = arr.length;
        int[] mem = new int[n];
        
        for (int i = 0; i < n; i++) {
            int sMax = 0;
            for (int j = 1; j <= k && i - j + 1 >= 0; j++) {
                sMax = Math.max(sMax, arr[i - j + 1]);
                mem[i] = Math.max(mem[i], (i >= j ? mem[i - j] : 0) + sMax*j);
            }
        }
        
        return mem[n - 1];
    }
}
