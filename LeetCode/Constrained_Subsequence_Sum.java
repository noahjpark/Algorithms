/* Noah Park

Given an integer array nums and an integer k, return the maximum sum of a non-empty subsequence of that array such that for every two consecutive integers in the subsequence, nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.

A subsequence of an array is obtained by deleting some number of elements (can be zero) from the array, leaving the remaining elements in their original order.

*/

class Solution {
    
    // Intuition: Same as Jump Game VI except we don't have to maintain the sum from the beginning and we don't have to hit the end of the array. The modifications just involve either taking the sum of a previous maximum we've seen so far only if positive and maintaining the maximum value so far.
    public int constrainedSubsetSum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        
        int n = nums.length, max = nums[0];
        Deque<Integer> q = new LinkedList<>();
        int[] mem = new int[n];
        for (int i = 0; i < n; i++) mem[i] = nums[i];
        q.addLast(0);
        
        for (int i = 1; i < n; i++) {
            if (q.peek() + k < i) q.removeFirst();
            
            if (mem[q.getFirst()] > 0) mem[i] += mem[q.getFirst()];
            max = Math.max(mem[i], max);
            while (!q.isEmpty() && mem[i] >= mem[q.getLast()]) q.removeLast();
            q.addLast(i);
        }
        
        return max;
    }
}
