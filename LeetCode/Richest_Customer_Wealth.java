/* Noah Park

You are given an m x n integer grid accounts where accounts[i][j] is the amount of money the ith customer has in the jth bank. Return the wealth that the richest customer has.

A customer's wealth is the amount of money they have in all their bank accounts. The richest customer is the customer that has the maximum wealth.

*/

class Solution {
    public int maximumWealth(int[][] accounts) {
        int max = 0; // max wealth
        
        for (int[] account : accounts) { // iterate over all accounts
            int total = 0; 
            
            for (int amount : account) total += amount; // total the amount in each account
            
            max = Math.max(max, total); // take maximum wealth
        }
        
        return max;
    }
}
