/* Noah Park

Given the array nums consisting of n positive integers. You computed the sum of all non-empty continous subarrays from the array and then sort them in non-decreasing order, creating a new array of n * (n + 1) / 2 numbers.

Return the sum of the numbers from index left to index right (indexed from 1), inclusive, in the new array. Since the answer can be a huge number return it modulo 10^9 + 7.

*/

class Solution {
    
    // Intuition: Brute force sum all values and put them in an array then sort and sum the range. Could optimize with heap but not worth it lol.
    // Time: O(n^2 log n^2) since we are sorting n^2 elements now 
    // Space: O(n^2) to store all sums
    public int rangeSum(int[] nums, int n, int left, int right) {
        int[] sum = new int[n * (n + 1) / 2];
        int p = 0;
        
        for (int i = 0; i < n; i++) {
            int s = 0;
            for (int j = i; j < n; j++) {
                s += nums[j];
                sum[p++] = s;
            }
        }
        
        Arrays.sort(sum);
        
        long res = 0;
        for (int i = left - 1; i < right; i++) 
            res += sum[i];
        
        return (int) (res % 1000000007);
    }
}
