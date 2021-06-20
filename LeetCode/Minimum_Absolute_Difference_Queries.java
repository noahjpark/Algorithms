/* Noah Park

The minimum absolute difference of an array a is defined as the minimum value of |a[i] - a[j]|, where 0 <= i < j < a.length and a[i] != a[j]. If all elements of a are the same, the minimum absolute difference is -1.

For example, the minimum absolute difference of the array [5,2,3,7,2] is |2 - 3| = 1. Note that it is not 0 because a[i] and a[j] must be different.
You are given an integer array nums and the array queries where queries[i] = [li, ri]. For each query i, compute the minimum absolute difference of the subarray nums[li...ri] containing the elements of nums between the 0-based indices li and ri (inclusive).

Return an array ans where ans[i] is the answer to the ith query.

A subarray is a contiguous sequence of elements in an array.

The value of |x| is defined as:

x if x >= 0.
-x if x < 0.

*/

class Solution {
    
    // Intuition: Maintain each indices past counts of all values in nums (counting sort + prefix sum). This way we can check the upper and lower bound of the 2D array to utilize the pre processing we did and find the minimum difference between two unique numbers in this range.
    // Time: O(n + q) where n is the length of nums and q is the length of queries.
    // Space: O(n) for the 2D array.
    public int[] minDifference(int[] nums, int[][] queries) {
        if (nums == null || queries == null || nums.length == 0 || queries.length == 0) return new int[]{};
        
        int n = nums.length, q = queries.length;
        int[] res = new int[q];
        int[][] dp = new int[n + 1][101];
        
        for (int i = 0; i < n; i++)
            for (int j = 1; j < 101; j++) 
                dp[i + 1][j] = (nums[i] == j) ? dp[i][j] + 1 : dp[i][j];
        
        for (int i = 0; i < q; i++) {
            int l = queries[i][0], r = queries[i][1] + 1, min = Integer.MAX_VALUE;
            
            int s = 1, e = 1;
            while (s < 101 && dp[r][s] - dp[l][s] == 0) s++;
            e = s + 1;
            while (e < 101) {
                while (e < 101 && dp[r][e] - dp[l][e] == 0) e++;
                if (e == 101) break;
                min = Math.min(min, e - s);
                s = e++;
            }
            
            res[i] = min == Integer.MAX_VALUE ? -1 : min;
        }
        
        return res;
    }
}
