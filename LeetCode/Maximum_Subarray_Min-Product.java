/* Noah Park

The min-product of an array is equal to the minimum value in the array multiplied by the array's sum.

For example, the array [3,2,5] (minimum value is 2) has a min-product of 2 * (3+2+5) = 2 * 10 = 20.
Given an array of integers nums, return the maximum min-product of any non-empty subarray of nums. Since the answer may be large, return it modulo 109 + 7.

Note that the min-product should be maximized before performing the modulo operation. Testcases are generated such that the maximum min-product without modulo will fit in a 64-bit signed integer.

A subarray is a contiguous part of an array.

*/

class Solution {
    
    // Intuition: Maintain the smallest index to the left of each index that is smaller than the current index and the smallest index to the right of each index that is the current index. This tells us what the bound for our sum is. Take advantage of a prefix sum and we have our answer. This way we maintain each current index as the smallest in the subarray.
    // Time: O(n) 4 passes.
    // Space: O(n) for the left/right arrays and stack.
    public int maxSumMinProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0; 
        
        int n = nums.length, i = 0, j = n - 1;
        
        Stack<Integer> s1 = new Stack<>(), s2 = new Stack<>();
        int[] left = new int[n], right = new int[n];
        
        for (; i < n && j >= 0; i++, j--) {
            while (!s1.isEmpty() && nums[s1.peek()] >= nums[i]) s1.pop();
            while (!s2.isEmpty() && nums[s2.peek()] >= nums[j]) s2.pop();
            
            left[i] = s1.isEmpty() ? -1 : s1.peek();
            right[j] = s2.isEmpty() ? n : s2.peek();
            s1.push(i);
            s2.push(j);
        }
        
        long maxmin = 0;
        long[] prefix = new long[n];
        prefix[0] = nums[0];
        
        for (i = 1; i < n; i++)
            prefix[i] = prefix[i - 1] + nums[i];
        
        for (i = 0; i < n; i++) {
            long sum = left[i] == -1 ? prefix[right[i]-1] : prefix[right[i]-1] - prefix[left[i]];
            long cur = (long)nums[i] * sum;
            maxmin = Math.max(cur, maxmin);
        }
        
        
        return (int) (maxmin % 1000000007);
    }
    
}
