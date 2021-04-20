/* Noah Park

It is a sweltering summer day, and a boy wants to buy some ice cream bars.

At the store, there are n ice cream bars. You are given an array costs of length n, where costs[i] is the price of the ith ice cream bar in coins. The boy initially has coins coins to spend, and he wants to buy as many ice cream bars as possible. 

Return the maximum number of ice cream bars the boy can buy with coins coins.

Note: The boy can buy the ice cream bars in any order.



*/

class Solution {
    
    // Intuition: Greedy approach. Once costs of the bars are sorted, the boy can buy them from cheapest to most expensive. Once he runs out of coins, he will have purchased the largest number of bars he could have.
    // Time: O(n log n) for the sort.
    // Space: O(n) Quicksort? Average O(1)
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int i = 0;
        
        while (i < costs.length) {
            if (coins < costs[i]) break;
            coins -= costs[i];
            i++;
        }
        
        return i;
    }
}
