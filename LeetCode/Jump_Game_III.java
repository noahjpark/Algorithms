/* Noah Park

Given an array of non-negative integers arr, you are initially positioned at start index of the array. When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.

Notice that you can not jump outside of the array at any time.

*/

class Solution {
    
    // Intuition: Utilize a bfs traversal to visit the indices.
    // Time: O(n) to search through arr.
    public boolean canReach2(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            
            if (arr[cur] == 0) return true;
            visited[cur] = true;
            
            if (cur - arr[cur] >= 0 && !visited[cur - arr[cur]]) q.offer(cur - arr[cur]);
            if (cur + arr[cur] < arr.length && !visited[cur + arr[cur]]) q.offer(cur + arr[cur]);
        }
        
        return false;
    }
    
    // Intuition: Use top down dp with memoization to evaluate all paths.
    // Time: O(n)
    // Space: O(n) for the recursion call stack and memoized array.
    Boolean[] mem;
    
    public boolean canReachTopDownDP(int[] arr, int start) {
        mem = new Boolean[arr.length];
        return dp(arr, start);
    }
    
    public boolean dp(int[] arr, int idx) {
        if (idx < 0 || idx >= arr.length) return false;
        if (arr[idx] == 0) return true;
        if (mem[idx] != null) return mem[idx];
        
        mem[idx] = false; // mark as 'visited' so we don't infinitely loop
        mem[idx] = dp(arr, idx - arr[idx]) | dp(arr, idx + arr[idx]);
        return mem[idx];
    }
}
