/* Noah Park

Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.

Return the maximum product you can get.

*/

class Solution {
    
    // Intuition: After writing out combinations of numbers, the ideal sum takes 3s or 2s. Simply put, we want to try to take 3s whenever we can with the numbers. The edge cases are 4: should multiply by 4 instead of 3 and (1, 2, 3) all return 1/n-1/n-1 respectively since we must break into at least 2 positive nums.
    // Time: O(n)
    // Space: O(1)
    public int integerBreak(int n) {
        if (n == 1) return 1;
        if (n == 2 || n == 3) return n - 1;
        
        int prod = 1;
        
        while (n > 0) {
            if (n > 4) { prod *= 3; n -= 3; }
            else if (n == 4) { prod *= 4; break; }
            else if (n == 3 || n == 2) { prod *= n; break; }
            else if (n == 1) break;
        }
        
        return prod;
    }
}
