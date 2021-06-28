/* Noah Park

You are given a license key represented as a string s that consists of only alphanumeric characters and dashes. The string is separated into n + 1 groups by n dashes. You are also given an integer k.

We want to reformat the string s such that each group contains exactly k characters, except for the first group, which could be shorter than k but still must contain at least one character. Furthermore, there must be a dash inserted between two groups, and you should convert all lowercase letters to uppercase.

Return the reformatted license key.

 

*/

class Solution {
    
    
    // Intuition: Add characters in uppercase from the back. Every kth sequence we insert a '-'. Take care of the edge case if there is a '-' at the end by removing it. Then return the reversed string since we build it from the back forward.
    // Time: O(n) to iterate over s.
    // Space: O(n) to maintain the result list.
    public String licenseKeyFormatting(String s, int k) {
        if (s == null || s.length() == 0) return null;
        
        int n = s.length(), cnt = 0;
        StringBuilder res = new StringBuilder();
        
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) != '-') {
                res.append(Character.toUpperCase(s.charAt(i)));
                cnt++;
                
                if (cnt == k) {
                    cnt = 0;
                    res.append("-");
                }
            }
        }
        
        if (res.length() > 0 && res.charAt(res.length() - 1) == '-') res.deleteCharAt(res.length() - 1);
        
        return res.reverse().toString();
    }
    
}
