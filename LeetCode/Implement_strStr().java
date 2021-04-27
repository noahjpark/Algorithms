/* Noah Park

Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().

*/

class Solution {
    
    // Intuition: Improves on the below solution by utilizing the built in equals rather than doing it manually.
    // Time: O((n - m) * m) to iterate over the portion of haystack that we compare to needle then use .equals().
    // Space: O(1) constant.
    public int strStr(String haystack, String needle) {
        if (haystack.equals(needle) || needle.equals("")) return 0;
        
        int n = haystack.length(), m = needle.length();
        
        for (int i = 0; i < n - m + 1; i++) 
            if (haystack.substring(i, i + m).equals(needle)) return i;
        
        return -1;
    }
    
    // Intuition: Utilizes same algorithm as above but compares manually. 
    // Time: O((n - m) * m) to iterate over haystack then compare to needle.
    // Space: O(1) constant.
    public int strStr2(String haystack, String needle) {
        if (haystack.equals(needle) || needle.equals("")) return 0;
        
        int n = haystack.length(), m = needle.length();
        
        for (int i = 0; i < n - m + 1; i++) {
            if (haystack.charAt(i) != needle.charAt(0)) continue;
            
            int j = i, k = 0;
            while (j < n && k < m && haystack.charAt(j) == needle.charAt(k)) { j++; k++; }
            if (k == m) return i;
        }
        
        return -1;
    }
}
