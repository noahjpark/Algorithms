/* Noah Park

You are given coins of different denominations and a total amount of money. Write a function to compute the number of combinations that make up that amount. You may assume that you have infinite number of each kind of coin.

*/

class Solution {  
    public int change(int amount, int[] coins) {
        int[] mem = new int[amount + 1]; // memoized array
        mem[0] = 1; // there is 1 way to make 0
        
        // we must iterate over the coins first; iterating over the amount then coins yields all possible combinations of coins for that amount which results in double counting sums such as (1 + 2) and (2 + 1) as two different ways to make the change
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++)
                if (j >= coins[i]) mem[j] += mem[j - coins[i]]; // take the number of ways to make the change from the subtracted sum and add it to that position
        }
        
        return mem[amount];
    }
}
