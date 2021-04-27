/* Noah Park

You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

*/

class Solution {
    
    // Intuition: Utilize a greedy approach to take the smallest value as we go and compare the difference of currentPrice - smallestVal to see if we have a new maximum profit.
    // Time: O(n) to loop through prices.
    // Space: O(1) constant.
    public int maxProfit(int[] prices) {
        int max = 0, min = Integer.MAX_VALUE;
        
        for (int price : prices) {
            if (min != Integer.MAX_VALUE) max = Math.max(price - min, max);
            min = Math.min(min, price);
        }
        
        return max;
    }
}
