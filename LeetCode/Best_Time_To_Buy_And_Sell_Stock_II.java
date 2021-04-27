/* Noah Park

You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

*/

class Solution {
    
    // Intuition: just add every increase we can find. Any time we are going up would have added up to the total increase anyways.
    // Time: O(n) to check all prices.
    // Space: O(1) constant.
    public int maxProfit(int[] prices) {
        int profit = 0;
        
        for (int i = 1; i < prices.length; i++) 
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        
        return profit;
    }
    
    // Intuition: Store the bottom of any valley then find its climbing peak and add it to the profits.
    // Time: O(n) to check all prices once.
    // Space: O(1) constant.
    public int maxProfit2(int[] prices) {
        int profit = 0, i = 0;
        
        // Find first valley - this will be mins start
        while (i < prices.length - 1 && prices[i] > prices[i + 1]) i++;
        int min = i;
        
        // check the rest. If we find a new peak, add the profits and update the new potential valley.
        for (; i < prices.length - 1; i++)
            if (prices[i] > prices[i + 1]) { profit += prices[i] - prices[min]; min = i + 1; }
        
        // edge case at the end if the peak is the last element
        if (min != -1 && prices[min] < prices[prices.length - 1]) profit += prices[prices.length - 1] - prices[min];
        
        return profit;
    }
}
