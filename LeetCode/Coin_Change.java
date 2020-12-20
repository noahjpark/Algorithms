/* Noah Park

You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.

*/

class Solution {
    
    // bottom up dynamic programming with memoization
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0; // base case
        
        int[] mem = new int[amount + 1]; // store best amounts so far
        int sum = 1; // 0 is always 0
        
        while (sum <= amount) { // compute best ways to make change at each point
            int min = -1;
            
            for (int coin : coins) { // check each coin
                if (sum >= coin && mem[sum - coin] != -1) { // if the sum is large enough, check the current sum - current coin but add the coin (+1)
                    int res = mem[sum - coin] + 1;
                    min = min == -1 ? res : Math.min(res, min); // ensure min is positive before using math.min
                }
            }
            
            mem[sum++] = min; // store result in the array
        }
        
        return mem[amount]; // minimum ways is the value at the amount
    }
    
    // top down dynamic programming with memoization
    public int coinChangeTopDown(int[] coins, int amount) {
        Integer[] mem = new Integer[amount + 1]; // memoized array
        int min = helper(coins, amount, mem);
        return min == Integer.MAX_VALUE ? -1 : min; // max value represents -1 for this
    }
    
    public int helper(int[] coins, int amount, Integer[] mem) {
        if (amount == 0) return 0; // base case
        else if (amount < 0) return Integer.MAX_VALUE; // can't make this amount
        else if (mem[amount] != null) return mem[amount]; // already computed
        
        int min = Integer.MAX_VALUE; // start each one with the largest value
        
        for (int coin : coins) {
            int res = helper(coins, amount - coin, mem);
            if (res == Integer.MAX_VALUE) { mem[amount] = res; continue; } // invalid, skip, store value so we don't need to recompute
            min = Math.min(min, ++res); // find the best path at this amount
        }
        
        mem[amount] = min; // store so no recomputation
        return min;
    }
}
