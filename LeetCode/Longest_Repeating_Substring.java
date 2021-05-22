/* Noah Park

Given a string s, find out the length of the longest repeating substring(s). Return 0 if no repeating substring exists.



*/

class Solution {
    
    public int longestRepeatingSubstring(String s) {
        
    }
    
    // Intuition: Brute force try all substring combinations of each size to see if we have a repeat.
    // Time: O(n^2) to make all comparisons.
    // Space: O(n) to maintain all substrings of s at each length.
    public int longestRepeatingSubstring2(String s) {
        Set<String> set = new HashSet<>();
        int n = s.length();
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j + i <= n; j++) {
                String sub = s.substring(j, j + i);
                if (set.contains(sub)) return i;
                set.add(sub);
            }
            set.clear();
        }
        
        return 0;
    }
}
