/* Noah Park

There are 3n piles of coins of varying size, you and your friends will take piles of coins as follows:

In each step, you will choose any 3 piles of coins (not necessarily consecutive).
Of your choice, Alice will pick the pile with the maximum number of coins.
You will pick the next pile with maximum number of coins.
Your friend Bob will pick the last pile.
Repeat until there are no more piles of coins.
Given an array of integers piles where piles[i] is the number of coins in the ith pile.

Return the maximum number of coins which you can have.

*/

class Solution {
    public int maxCoins(int[] piles) {
        if (piles == null || piles.length == 0) return piles; // edge cases
        
        Arrays.sort(piles); // sort piles
        
        int p1 = 0, p2 = piles.length - 2, coins = 0; // two pointers until we have chosen all triplets and coin accumulator
        
        // iterate until we have chosen all triplets always choosing the second greatest of the triplet
        while (p1 < p2) {
            coins += piles[p2];            
            p1++;
            p2 -= 2;
        }
        
        return coins;
    }
}
