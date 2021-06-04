/* Noah Park

Imagine you have a special keyboard with the following keys:

A: Print one 'A' on the screen.
Ctrl-A: Select the whole screen.
Ctrl-C: Copy selection to buffer.
Ctrl-V: Print buffer on screen appending it after what has already been printed.
Given an integer n, return the maximum number of 'A' you can print on the screen with at most n presses on the keys.

*/

class Solution {
    
    // Intuition: For each value that is calculable, we try to break down the problem into only putting As or copy pasting numerous As in.
    // Time: O(n^2) to make the comparisons.
    // Space: O(n) for the recursion tree.
    public int maxA2(int n) {
        int res = n;
        for (int i = 1; i < n - 2; i++)
            res = Math.max(res, maxA(i) * (n - i - 1));
        return res;
    }
    
    // Intuition: Tabulated version of the recursive solution above.
    // Time: O(n^2) to make the comparisons.
    // Space: O(n) for the memoized array.
    public int maxA(int n) {
        if (n < 5) return n;
        
        int[] mem = new int[n + 1];
        
        for (int i = 1; i < n + 1; i++) {
            mem[i] = i;
            for (int j = 1; j < i - 2; j++)
                mem[i] = Math.max(mem[i], mem[i - j]*(j - 1));
        }
        
        return mem[n];
    }
    
//     public int dp(int i, int total, boolean select, boolean copy) {
//         if (i == n) return total;
        
//         int res = dp(i + 1, total + 1, select, copy);
        
//         if (!select) res = Math.max(res, dp(i + 1, total, true, copy));
//         else if (!copy) {
//             copyLen = total;
//             res = Math.max(res, dp(i + 1, total, true, true));
//         } else res = Math.max(res, dp(i + 1, total + copyLen, true, true));
        
//         return res;
//     }
}

/*
a a a a a CA CC CV CV CV CV
a a a a CA CC CV CV CV CV CV
a a a CA CC CV CV CA CC CV CV
a a a a CA CC CV CA CC CV CV
*/
