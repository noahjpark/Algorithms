/* Noah Park

From any string, we can form a subsequence of that string by deleting some number of characters (possibly no deletions).

Given two strings source and target, return the minimum number of subsequences of source such that their concatenation equals target. If the task is impossible, return -1.

*/

class Solution {
    
    // Intuition: Greedy approach to attempt to form longest ordered sequence from the source in target.
    // Time: O(n*m) iterating over source for each character in target.
    // Space: O(1) constant.
    public int shortestWay(String source, String target) {
        int res = 0, n = target.length(), m = source.length(), i = 0;
        
        while (i < n) {
            boolean match = false;
            
            for (int j = 0; j < m; j++) {
                if (target.charAt(i) == source.charAt(j)) { i++; match = true; }
                if (i == n) break;   
            }
            
            if (!match) return -1;
            
            res++;
        }
        
        return res;
    }
}
