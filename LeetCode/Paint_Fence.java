/* Noah Park

There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.

*/

class Solution {
    
    // bottom up dynamic programming with memoization
    public int numWays(int n, int k) {
        int[] mem = new int[n + 1]; // memoized table
        
        // base cases
        mem[0] = 0; // no paint
        if (n + 1 > 1) mem[1] = k; // each color can be painted once
        if (n + 1 > 2) mem[2] = k*k; // each k can be matched with k other colors because we can repeat due to length 2
        
        for (int i = 3; i < n + 1; i++) { // calculate the rest through iteration
            mem[i] = (mem[i - 1] + mem[i - 2]) * (k - 1); // take the previous two (fibonacci style) and multiply by k - 1 new colors we can use
        }
        
        return mem[n]; // mem[n] will be the n'th fence ways we can paint
    }
    
    // top down dynamic programming with memoization
    public int numWaysTopDown(int n, int k) {
        Integer[] mem = new Integer[n + 1]; // memoized table
        return paint(n, k, mem); // call recursive helper
    }
    
    public int paint(int n, int k, Integer[] mem) {
        if (mem[n] != null) return mem[n]; // if we already have the value memoized, do not recompute and simply return our value
        else if (n == 0) return 0; // base case no paint
        else if (n == 1 || n == 2) return (int) Math.pow(k, n); // k can be matched either once or with k other colors
        
        // store the value in the memoized table to avoid recomputation
        mem[n] = paint(n - 1, k, mem) * (k - 1) + paint(n - 2, k, mem) * (k - 1);
    
        return mem[n]; // return the value
    }
}
