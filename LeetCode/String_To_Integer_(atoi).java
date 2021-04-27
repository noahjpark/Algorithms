/* Noah Park

Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).

The algorithm for myAtoi(string s) is as follows:

Read in and ignore any leading whitespace.
Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
Read in next the characters until the next non-digit charcter or the end of the input is reached. The rest of the string is ignored.
Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.
Return the integer as the final result.
Note:

Only the space character ' ' is considered a whitespace character.
Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.

*/

class Solution {
    
    // Intuition: Follow algorithm instructions: trim whitespace, check for -/+, then scan from left to right. I built in an optimization to break early in case we are too large or small early on.
    public int myAtoi(String s) {
        s = s.trim();
        int n = s.length();
        
        if (n == 0) return 0;
        
        long res = 0;
        int i = 0;
        boolean negative = s.charAt(0) == '-' ? true : false;
        if (s.charAt(i) == '-' || s.charAt(i) == '+') i++;
        
        for (; i < n; i++) {
            if (!Character.isDigit(s.charAt(i))) break;
            else if (Character.isDigit(s.charAt(i))) res = (res + Character.getNumericValue(s.charAt(i))) * 10;
            
            if (res / 10 > Integer.MAX_VALUE) return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            if (res / 10 < Integer.MIN_VALUE) return negative ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        
        res /= 10;
        if (negative) res *= -1;
        
        return res < Integer.MIN_VALUE ? Integer.MIN_VALUE : res > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) res;
    }
}
