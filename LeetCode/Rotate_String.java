/* Noah Park

We are given two strings, s and goal.

A shift on s consists of taking string s and moving the leftmost character to the rightmost position. For example, if s = 'abcde', then it will be 'bcdea' after one shift on s. Return true if and only if s can become goal after some number of shifts on s.

*/

class Solution {
    
    // Intuition: Rolling sliding window. Could append two occurrences of s together and use a literal sliding window.
    // Time: O(n) to iterate over all rotations of s.
    // Space: O(n) for the string builder.
    public boolean rotateString(String s, String goal) {
        if (s == null || goal == null || s.length() != goal.length()) return false;
        if (s.equals(goal)) return true;
        
        int n = s.length();
        StringBuilder temp = new StringBuilder();
        temp.append(s);
        
        for (int i = 0; i < n; i++) {
            if (temp.toString().equals(goal)) return true;
            temp.append(temp.charAt(0));
            temp.deleteCharAt(0);
        }
        
        return false;
    }
}
