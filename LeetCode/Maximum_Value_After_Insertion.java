/* Noah Park

You are given a very large integer n, represented as a string,​​​​​​ and an integer digit x. The digits in n and the digit x are in the inclusive range [1, 9], and n may represent a negative number.

You want to maximize n's numerical value by inserting x anywhere in the decimal representation of n​​​​​​. You cannot insert x to the left of the negative sign.

For example, if n = 73 and x = 6, it would be best to insert it between 7 and 3, making n = 763.
If n = -55 and x = 2, it would be best to insert it before the first 5, making n = -255.
Return a string representing the maximum value of n​​​​​​ after the insertion

*/

class Solution {
    
    // Intuition: For a positive integer string, we want to find the leftmost character that is smaller than our x value and insert x in place of that value. For a negative integer string, we want to find the largest leftmost character and insert x before that one. We can simply find that target index (whether positive or negative) then utilize the substring operation to easily append to the stringbuilder.
    // Time: O(n) to iterate over n.
    // Space: O(n) for the return string.
    public String maxValue(String n, int x) {
        StringBuilder res = new StringBuilder();
        boolean negative = n.charAt(0) == '-';
        int l = n.length(), idx = -1;
        
        for (int i = 0; i < l; i++) {
            if (!negative && (n.charAt(i) - '0') < x) { idx = i; break; }
            if (negative && i > 0 && (n.charAt(i) - '0') > x) { idx = i; break; }
        }
        
        if (idx == -1) idx = l;
        
        res.append(n.substring(0, idx)).append(x).append(n.substring(idx));
        
        return res.toString();
    }
}
