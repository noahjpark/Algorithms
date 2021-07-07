/* Noah Park

Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9. 
X can be placed before L (50) and C (100) to make 40 and 90. 
C can be placed before D (500) and M (1000) to make 400 and 900.
Given an integer, convert it to a roman numeral.

*/

class Solution {
    
    int[] vals = new int[]{ 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
    String[] s = new String[]{ "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
    
    // Intuition: Modularized code that could be extended to more values.
    // Time and Space: O(1) for this problem. // O(n) if we extended to larger values.
    public String intToRoman(int num) {
        StringBuilder res = new StringBuilder();
        int i = 0;
        
        while (num > 0) {
            while (num >= vals[i]) {
                res.append(s[i]);
                num -= vals[i];
            }
            i++;
        }
        
        return res.toString();
    }
    
    String[] hundreds = new String[]{ "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
    String[] tens = new String[]{ "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
    String[] ones = new String[]{ "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
    
    // Intuition: Hardcode associated values.
    // Time and Space: O(1).
    public String intToRoman2(int num) {
        StringBuilder res = new StringBuilder();
        
        // thousands place
        for (; num >= 1000; num -= 1000)
            res.append("M");
        
        // hundreds place
        res.append(hundreds[num / 100]);
        num %= 100;
        
        // tens place
        res.append(tens[num / 10]);
        num %= 10;
        
        // ones place
        res.append(ones[num]);
        
        return res.toString();
    }
}
