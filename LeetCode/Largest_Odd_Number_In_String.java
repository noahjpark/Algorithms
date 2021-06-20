/* Noah Park

You are given a string num, representing a large integer. Return the largest-valued odd integer (as a string) that is a non-empty substring of num, or an empty string "" if no odd integer exists.

A substring is a contiguous sequence of characters within a string.

*/

class Solution {
    
    // Intuition: The rightmost odd digit is the end of the substring that we want since it would maximize the total number and is odd since the rightmost digit is odd.
    // Time: O(n) to iterate over num.
    // Space: O(1) constant.
    public String largestOddNumber(String num) {
        if (num == null || num.length() == 0) return "";
        
        int idx = -1, n = num.length();
        
        for (int i = n - 1; i >= 0; i--) {
            int cur = (num.charAt(i) - '0');
            if (cur % 2 == 1) { idx = i; break; }
        }
        
        return idx == -1 ? "" : num.substring(0, idx + 1);
    }
}
