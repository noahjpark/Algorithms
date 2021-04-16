/* Noah Park

In a country popular for train travel, you have planned some train travelling one year in advance.  The days of the year that you will travel is given as an array days.  Each day is an integer from 1 to 365.

Train tickets are sold in 3 different ways:

a 1-day pass is sold for costs[0] dollars;
a 7-day pass is sold for costs[1] dollars;
a 30-day pass is sold for costs[2] dollars.
The passes allow that many days of consecutive travel.  For example, if we get a 7-day pass on day 2, then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.

Return the minimum number of dollars you need to travel every day in the given list of days.

*/

class Solution {
    
    // Intuition: Top down dynamic programming with memoization. We target the next pass day that we must buy and always take the smallest values.
    // Time: O(n) to go over each day.
    // Space: O(n) for the memoized array and stack space.
    Integer[] mem;
    int[] pass = new int[]{ 1, 7, 30 };
    
    public int mincostTickets(int[] days, int[] costs) {
        mem = new Integer[days.length];
        dp(days, costs, 0);
        return mem[0];
    }
    
    public int dp(int[] days, int[] costs, int idx) {
        if (idx >= days.length) return 0;
        if (mem[idx] != null) return mem[idx];
        
        int totalCost = Integer.MAX_VALUE, nextPass = idx;
        
        for (int i = 0; i < 3; i++) {
            while (nextPass < days.length && days[nextPass] < days[idx] + pass[i]) // starting from the smallest pass, we calculate the next index in days that we will have to "update"
                nextPass++;
            totalCost = Math.min(totalCost, dp(days, costs, nextPass) + costs[i]);
        }
        
        mem[idx] = totalCost;
        return mem[idx];
    }
}
