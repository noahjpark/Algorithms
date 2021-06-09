/* Noah Park

There is only one character 'A' on the screen of a notepad. You can perform two operations on this notepad for each step:

Copy All: You can copy all the characters present on the screen (a partial copy is not allowed).
Paste: You can paste the characters which are copied last time.
Given an integer n, return the minimum number of operations to get the character 'A' exactly n times on the screen.

*/

class Solution {
    
    // Intuition: prime factorization to create each value within n, we can find the smallest number that divides n, we need to copy n divided by that number that number times. We add that number to the result and divide n to continue
    // Time: O(sqrt(n)) or O(n) ?? For some situations O(sqrt(n)) makes sense but for a prime example, we would iterate n times...
    // Space: O(1) constant.
    public int minSteps(int n) {
        int res = 0;
        
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                res += i;
                n /= i;
            }
        }
        
        return res;
    }
    
    // Intuition: Top down dp to calculate all possibilities. Take a knapsack approach to attempt to either paste what we currently have copied if anything, or copy paste our values for weights of 1 and 2 respectively. The edge case of adding too many characters (> n) is covered by returning a bogus value to indicate we have found an invalid path.
    // Time: O(n^2) to calculate all possibilities.
    // Space: O(n) if we continuously paste a single character.
    public int minSteps2(int n) {
        return dp(n, 1, 0);
    }
    
    public int dp(int n, int cur, int copied) {
        if (cur == n) return 0;
        if (cur > n) return Integer.MAX_VALUE;
        
        int paste = Integer.MAX_VALUE, cpyPaste = dp(n, cur*2, cur);
        if (copied > 0) paste = dp(n, cur + copied, copied);
        
        if (paste != Integer.MAX_VALUE) paste++;
        if (cpyPaste != Integer.MAX_VALUE) cpyPaste += 2;
        
        return Math.min(paste, cpyPaste);
    }
}
