/* Noah Park

A perfect number is a positive integer that is equal to the sum of its positive divisors, excluding the number itself. A divisor of an integer x is an integer that can divide x evenly.

Given an integer n, return true if n is a perfect number, otherwise return false.

*/

class Solution {
    
    // Intuition: Optimize the below solution to only go to the square root point. In the example of 28, whatever the bottom multiple is (2 for example) will multiply 14 which we have num / 2. So we only need to go through the square root of the number.
    // Time: O(sqrt(n)) to iterate over the square root of the num.
    // Space: O(1) constant.
    public boolean checkPerfectNumber(int num) {
        if (num == 1) return false; // edge case
        
        int res = 1, n = (int) Math.sqrt(num);
        
        for (int i = 2; i <= n; i++)
            if (num % i == 0) res += (i + num / i);
        
        return res == num;
    }
    
    // Intuition: Brute force add all possibilities.
    // Time: O(n) to iterate over all nums up to num.
    // Space: O(1) constant.
    public boolean checkPerfectNumber2(int num) {
        int res = 0;
        
        for (int i = 1; i < num; i++)
            if (num % i == 0) res += i;
        
        return res == num;
    }
}
