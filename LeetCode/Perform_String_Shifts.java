/* Noah Park

You are given a string s containing lowercase English letters, and a matrix shift, where shift[i] = [directioni, amounti]:

directioni can be 0 (for left shift) or 1 (for right shift).
amounti is the amount by which string s is to be shifted.
A left shift by 1 means remove the first character of s and append it to the end.
Similarly, a right shift by 1 means remove the last character of s and add it to the beginning.
Return the final string after all operations.

*/

class Solution {
    
    // Intuition: Maintain the priority shift (left or right). Whichever is larger determines the direction we shift. Take the difference between the two and make sure it is within the length of the string using modular arithmetic. From here we just break the string at the point we must shift from the start for the left or from the end for the right.
    // Time: O(n) to iterate over shift.
    // Space: O(1) constant.
    public String stringShift(String s, int[][] shift) {
        int left = 0, right = 0, total = 0, n = s.length();
        boolean l = false;
        
        for (int[] sh : shift) {
            if (sh[0] == 0) left += sh[1];
            else right += sh[1];
        }
        
        if (left > right) {
            l = true;
            total = left - right;
        } else total = right - left;
        
        total %= n;
        if (total == 0) return s;
        StringBuilder res = new StringBuilder();
        
        if (l) res.append(s.substring(total) + s.substring(0, total));
        else res.append(s.substring(n - total) + s.substring(0, n - total));
        
        return res.toString();
    }
}
