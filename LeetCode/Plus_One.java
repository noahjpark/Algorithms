/* Noah Park

Given a non-empty array of decimal digits representing a non-negative integer, increment one to the integer.

The digits are stored such that the most significant digit is at the head of the list, and each element in the array contains a single digit.

You may assume the integer does not contain any leading zero, except the number 0 itself.

*/

class Solution {
    
    // Intuition: Update all digits in place. Take into account if we have all 9s and need to add a 1 to the start of a newly sized array.
    // Time: O(n) to update digits.
    // Space: O(n) if we need a new digits array.
    public int[] plusOne(int[] digits) {
        boolean carry = false;
        
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            
            if (digits[i] == 10) { digits[i] = 0; carry = true; }
            else { carry = false; break; }
        }
        
        if (!carry) return digits;
        
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        
        for (int i = 0; i < digits.length; i++)
            res[i + 1] = digits[i];
        
        return res;
    }
}
