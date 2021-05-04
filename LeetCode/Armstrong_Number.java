/* Noah Park

Given an integer n, return true if and only if it is an Armstrong number.

The k-digit number n is an Armstrong number if and only if the kth power of each digit sums to n.

*/

class Solution {
    
    // Intuition: Two pass solution to count the digits in n then sum them up while powering them kth times.
    // Time: O(n) to count the number of digits.
    // Space: O(1) constant.
    public boolean isArmstrong(int n) {
        int m = n, sum = 0, k = 0;
        
        for (; m > 0; m /= 10)
            k++;
        
        for (m = n; m > 0; m /= 10)
            sum += Math.pow(m % 10, k);
        
        return sum == n;
    }
}
