/* Noah Park

Given a string num which represents an integer, return true if num is a strobogrammatic number.

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

*/

class Solution {
    
    // Intuition: Flip any flippable numbers. If a number is not flippable, return false. Otherwise, we can append the reversal and compare the strings due to integer overflow.
    // Time: O(n) to iterate over num.
    // Space: O(n) to reverse the string.
    public boolean isStrobogrammatic(String num) {
        int m = num.length();
        StringBuilder res = new StringBuilder();
        
        for (int i = m - 1; i >= 0; i--) {
            int f = flip(num.charAt(i) - '0');
            if (f < 0) return false;
            res.append(f);
        }
        
        return num.equals(res.toString());
    }
    
    public int flip(int num) {
        if (num == 2 || num == 3 || num == 4 || num == 5 || num == 7) return -1;
        if (num == 0 || num == 1 || num == 8) return num;
        if (num == 6) return 9;
        return 6;
    }
}
