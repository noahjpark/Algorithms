/* Noah Park

Given a string s and a character c that occurs in s, return an array of integers answer where answer.length == s.length and answer[i] is the distance from index i to the closest occurrence of character c in s.

The distance between two indices i and j is abs(i - j), where abs is the absolute value function.

*/

class Solution {
    
    // Intuition: Go from each side and maintain the closest occurrence of c from either side. One pass from the left to the right will update all occurrences after the first one of c. Opposite for the right to the left.
    // Time: O(n) two passes.
    // Space: O(n) for the result/O(1) not including the result.
    public int[] shortestToChar(String s, char c) {
        int n = s.length(), prev = -10001;
        int[] res = new int[n];
        
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) prev = i;
            res[i] = i - prev;
        }
        
        prev = 10001;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == c) prev = i;
            res[i] = Math.min(res[i], prev - i);
        }
        
        
        return res;
    }
    
    // Intuition: BFS from all occurrences of c in s. Shortest path from each will be updated as we visit them.
    // Time: O(n) two passes.
    // Space: O(n) for the queue.
    public int[] shortestToChar2(String s, char c) {
        int n = s.length(), dis = 0;
        int[] res = new int[n];
        boolean[] visited = new boolean[n];
        Deque<Integer> q = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) {
                q.addLast(i);
                visited[i] = true;
            }
        }
        
        for (; !q.isEmpty(); dis++) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                int cur = q.removeFirst();
                res[cur] = dis;
                
                if (cur - 1 >= 0 && !visited[cur - 1]) {
                    q.addLast(cur - 1);
                    visited[cur - 1] = true;
                }
                if (cur + 1 < n && !visited[cur + 1]) {
                    q.addLast(cur + 1);
                    visited[cur + 1] = true;
                }
            }
        }
        
        return res;
    }
    
    // Intuition: Maintain the indices of c in s. Iterate over s again and find the minimum distance from the indices. Slight optimizations if the character is c or the distance starts increasing.
    // Time: O(n*i) where n is the length of s and i is the length of indices.
    // Space: O(n) to maintain indices.
    public int[] shortestToChar3(String s, char c) {
        List<Integer> indices = new ArrayList<>();
        int n = s.length();
        int[] res = new int[n];
        
        for (int i = 0; i < n; i++) 
            if (s.charAt(i) == c) indices.add(i);
        
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == c) {
                res[i] = 0;
                continue;
            }
            
            int dis = Integer.MAX_VALUE;
            
            for (int j : indices) {
                int diff = i > j ? i - j : j - i;
                if (diff > dis) break;
                else dis = diff;
            }
            
            res[i] = dis;
        }
        
        return res;
    }
}
