/* Noah Park

The chess knight has a unique movement, it may move two squares vertically and one square horizontally, or two squares horizontally and one square vertically (with both forming the shape of an L). The possible movements of chess knight are shown in this diagaram:

A chess knight can move as indicated in the chess diagram below:


We have a chess knight and a phone pad as shown below, the knight can only stand on a numeric cell (i.e. blue cell).


Given an integer n, return how many distinct phone numbers of length n we can dial.

You are allowed to place the knight on any numeric cell initially and then you should perform n - 1 jumps to dial a number of length n. All jumps should be valid knight jumps.

As the answer may be very large, return the answer modulo 109 + 7.

*/

class Solution {
    
    int mod = 1000000007; // number to modulo
    int[][] map = new int[][]{{4, 6}, {8, 6}, {7, 9},
                              {4, 8}, {9, 3, 0}, {}, 
                              {1, 7, 0}, {2, 6}, {1, 3}, 
                              {4, 2}}; // numbers mapped at the indexes at which they are reachable from
    
    public int knightDialer(int n) {
        int[][] dp = new int[n + 1][10]; // dp array to store the value at each iteration
        for(int[] row : dp) Arrays.fill(row, -1); // fill with all -1 before using
        
        int distinct = 0; // count distinct nums
        for (int i = 0; i < 10; i++) // go through each number and count the number of moves that can be done n times
            distinct = (distinct + calculateMove(n - 1, i, dp)) % mod;           
        
        return distinct;
    }
    
    public int calculateMove(int n, int i, int[][] dp) {
        if(n == 0) return 1; // base case, 1 number can make 1
        if(dp[n][i] != -1) return dp[n][i]; // if we have already computed this problem, return that value, -1 signifies not updated yet
        
        int distinct = 0; // count distinct nums
        for(int num : map[i]) // go through all nums in the map that are reachable
            distinct = (distinct + calculateMove(n - 1, num, dp)) % mod; // count moves that can be done n times
        dp[n][i] = distinct; // update dp array
        return distinct; // return distinct nums
    }
}
