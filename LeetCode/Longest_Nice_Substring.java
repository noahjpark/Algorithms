/* Noah Park

A string s is nice if, for every letter of the alphabet that s contains, it appears both in uppercase and lowercase. For example, "abABB" is nice because 'A' and 'a' appear, and 'B' and 'b' appear. However, "abA" is not because 'b' appears, but 'B' does not.

Given a string s, return the longest substring of s that is nice. If there are multiple, return the substring of the earliest occurrence. If there are none, return an empty string.

*/

class Solution {
    
    // Intuition: Divide and conquer the string. We must treat each point in the string that doesn't have both uppercase and lowercase as a break point to split the problem in half without including that character and solve each side. 
    public String longestNiceSubstring(String s) {
        if (s.length() < 2) return "";
        
        Set<Character> set = new HashSet<>();
        char[] t = s.toCharArray();
        
        for (char c : t)
            set.add(c);
        
        for (int i = 0; i < t.length; i++) {
            if (set.contains(Character.toLowerCase(t[i])) && set.contains(Character.toUpperCase(t[i]))) continue;
            String s1 = longestNiceSubstring(s.substring(0, i)), s2 = longestNiceSubstring(s.substring(i + 1));
            return s1.length() >= s2.length() ? s1 : s2;
        }
        
        return s;
    }
    
    // Intuition: Brute force comparisons of all substrings.
    // Time: O(n^2) to make all comparisons.
    // Space: O(1) constant.
    public String longestNiceSubstring2(String s) {
        String res = "";
        int n = s.length();
        
        for (int i = 0; i < n - 1; i++) {
            boolean[] upper = new boolean[26], lower = new boolean[26];
            StringBuilder t = new StringBuilder();
            
            for (int j = i; j < n; j++) {
                char c = s.charAt(j);
                t.append(c);
                
                if (c - 'A' < 26) upper[c - 'A'] = true;
                else lower[c-  'a'] = true;
                
                boolean match = true;
                for (int k = 0; k < 26; k++) 
                    if (lower[k] != upper[k]) match = false;
                
                if (match && t.length() > res.length()) res = t.toString();
            }
        }
        
        return res;
    }
}
