/* Noah Park

Given an integer n, return a string with n characters such that each character in such string occurs an odd number of times.

The returned string must contain only lowercase English letters. If there are multiples valid strings, return any of them.  

*/

class Solution {
    
    // Intuition: If n is odd, simply append 'a' n times which results in an odd occurence of the characters in the result. Otherwise, add 'a' n - 1 times (which would be odd) then add 'b' once. All characters are then odd.
    // Time: O(n) to iterate over n.
    // Space: O(n) for the return string.
    public String generateTheString(int n) {
        StringBuilder res = new StringBuilder();
        
        if (n % 2 == 1) {
            for (; n > 0; n--)
                res.append('a');
        } else {
            for (; n > 1; n--)
                res.append('a');
            res.append('b');
        }
        
        return res.toString();
    }
}
