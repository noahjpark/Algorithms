/* Noah Park

You are given a string s that consists of only digits.

Check if we can split s into two or more non-empty substrings such that the numerical values of the substrings are in descending order and the difference between numerical values of every two adjacent substrings is equal to 1.

For example, the string s = "0090089" can be split into ["0090", "089"] with numerical values [90,89]. The values are in descending order and adjacent values differ by 1, so this way is valid.
Another example, the string s = "001" can be split into ["0", "01"], ["00", "1"], or ["0", "0", "1"]. However all the ways are invalid because they have numerical values [0,1], [0,1], and [0,0,1] respectively, all of which are not in descending order.
Return true if it is possible to split s​​​​​​ as described above, or false otherwise.

A substring is a contiguous sequence of characters in a string.

*/

class Solution {
    
    // Intuition: Maintain the previous value in the sequence. Each recursive call, build the current value by looping through the valid characters passed in by the substring. optimize by breaking when this value becomes too large. Otherwise, we check a few cases. Either prev is null (first case before we have a prev), we look to see if a split call with the increasing current is possible and return true if so. Otherwise, we check the difference between prev and cur and ensure it is 1 and descending. If this is the case we just need to ensure we are at the end of the string (no more characters we need to include), or attempt to call recursion on remaining characters which will return true due to the prior statement if true.
    // Time: O(n^2) since we are making nested comparisons.
    // Space: O(n) depth for the recursive calls.
    public boolean splitString(String s) {
        return split(s, null);
    }
                
    public boolean split(String s, Long prev) {
        long cur = 0;
        
        for (int i = 0; i < s.length(); i++) {
            cur = (cur * 10) + s.charAt(i) - '0'; // create the number instead of parsing
            
            if (prev != null && cur >= prev) break; // optimize
            
            if (prev == null) { // if we are at the start we try each call to split
                if (split(s.substring(i + 1), cur)) return true;
            } else if (prev - cur == 1 && (i == s.length() - 1 || split(s.substring(i + 1), cur))) return true; // otherwise we are only returning true if the difference between prev and cur is descending and 1 + at the end of the string or recursion to the end.
        }
        
        return false;
    }
}
