/* Noah Park

You are given a 0-indexed integer array nums and an integer k.

You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries of the array. That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.

You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for each index j you visited in the array.

Return the maximum score you can get.

*/

class Solution {
    
    // Intuition: Any normal dp n^2 solution TLE's on this problem. The only way to approach it is to maintain a maximum of a sliding window of size k as we move through the array. At each point we maximize the score by adding the reachable maximum from the start to the current position. This is achieved through the use of a monotonically decreasing queue.
    // Time: O(n) to iterate over nums.
    // Space: O(k) for the queue.
    public int maxResult(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        
        int n = nums.length;
        Deque<Integer> q = new LinkedList<>();
        int[] mem = new int[n];
        mem[0] = nums[0];
        q.addLast(0);
        
        for (int i = 1; i < n; i++) {
            if (q.peek() + k < i) q.removeFirst();
            
            mem[i] += mem[q.getFirst()] + nums[i];
            while (!q.isEmpty() && mem[i] >= mem[q.getLast()]) q.removeLast();
            q.addLast(i);
        }
        
        return mem[n - 1];
    }
}
