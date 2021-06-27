/* Noah Park

There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
Return the minimum number of candies you need to have to distribute the candies to the children.

*/

class Solution {
    
    // Intuition: Maintain the best going from left to right and right to left considering the appropriate neighbor. We want the maximum value required at each point to include in our result.
    // Time: O(n) to iterate over ratings (3 pass).
    // Space: O(n) for the temp arrays.
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        
        int n = ratings.length, res = 0, min = 1;
        int[] lr = new int[n], rl = new int[n];
        Arrays.fill(lr, 1); Arrays.fill(rl, 1);
        
        for (int i = 1; i < n; i++)
            if (ratings[i] > ratings[i - 1]) lr[i] = lr[i - 1] + 1;
        
        for (int i = n - 2; i >= 0; i--)
            if (ratings[i] > ratings[i + 1]) rl[i] = rl[i + 1] + 1;
        
        for (int i = 0; i < n; i++)
            res += Math.max(lr[i], rl[i]);
        
        return res;
    }
        
}

/*

[1, 2, 2] -> [1, 2, 2] -> [1, 2, 2]

[4, 3, 2, 4] -> [1, 0, -1, 0]

*/
