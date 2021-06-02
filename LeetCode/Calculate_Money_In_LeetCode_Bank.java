/* Noah Park

Hercy wants to save money for his first car. He puts money in the Leetcode bank every day.

He starts by putting in $1 on Monday, the first day. Every day from Tuesday to Sunday, he will put in $1 more than the day before. On every subsequent Monday, he will put in $1 more than the previous Monday.
Given n, return the total amount of money he will have in the Leetcode bank at the end of the nth day.

*/

class Solution {
    
    // Intuition: Optimize the bottom solution by calculating each week as a whole instead of doing each day.
    // Time: O(log n) to calculate each week (divide by 7).
    // Space: O(1) constant.
    public int totalMoney(int n) {
        int weeks = n / 7, rem = n % 7, res = 0, start = weeks + 1, idx = 4;
        
        for (int i = 0; i < weeks; i++) 
            res += 7*idx++;
        
        for (int i = 1; i <= rem; i++)
            res += start++;
        
        return res;
    }
    
    // Intuition: Straightforward calculation of the total money.
    // Time: O(n) to iterate over n.
    // Space: O(1) constant.
    public int totalMoney2(int n) {
        int pastMon = 1, res = 0, cur = 1, count = 0;
        
        for (int i = 0; i < n; i++) {
            res += cur++;
            count++;
            if (count == 7) {
                count = 0;
                cur = ++pastMon;
            }
        }
        
        return res;
    }
}
