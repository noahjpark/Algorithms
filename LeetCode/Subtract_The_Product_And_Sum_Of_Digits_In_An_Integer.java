/* Noah Park

Given an integer number n, return the difference between the product of its digits and the sum of its digits.

*/

class Solution {
    
    // Intuition: Single pass to sum and multiply digits. Return the difference.
    // Time: O(log n) to count digits.
    // Space: O(1) constant.
    public int subtractProductAndSum(int n) {
        int prod = 1, sum = 0;
        
        for (; n > 0; n /= 10) {
            prod *= n % 10;
            sum += n % 10;
        }
        
        return prod - sum;
    }
}
