/* Noah Park

The Tribonacci sequence Tn is defined as follows: 

T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.

Given n, return the value of Tn.

*/

class Solution {
    
    // Intuition: Optimize the bottom up dynamic programming approach to not utilize an array since we only need the most recent three values.
    // Time: O(n) to iterate over n.
    // Space: O(1) constant.
    public int tribonacci(int n) {
        if (n == 0 || n == 1) return n;
        if (n == 2) return 1;
        
        int third = 0, second = 1, first = 1;
        
        for (int i = 3; i <= n; i++) {
            int cur = third + second + first;
            third = second;
            second = first;
            first = cur;
        }
        
        return first;
    }
    
    // Intuition: Utilize bottom up dynamic programming with memoization to optimize the recursive stack space from the top down method.
    // Time: O(n) to iterate over n.
    // Space: O(n) for the memoized array.
    public int tribonacciBottomUp(int n) {
        if (n == 0 || n == 1) return n;
        if (n == 2) return 1;
        
        int[] mem = new int[n + 1];
        mem[0] = 0; mem[1] = 1; mem[2] = 1;
        
        for (int i = 3; i <= n; i++)
            mem[i] = mem[i - 1] + mem[i - 2] + mem[i - 3];
        
        return mem[n];
    }
    
    // Intuition: We must utilize top down dynamic programming with memoization to optimze the brute force recursion and avoid recomputation.
    // Time: O(n) to compute the recursive value of each number once
    // Space: O(n) for the memoized array and stack space.
    Integer[] mem;
    
    public int tribonacciTopDown(int n) {
        mem = new Integer[n + 1];
        return trib(n);
    }
    
    public int trib(int n) {
        if (n == 0 || n == 1) return n;
        if (n == 2) return 1;
        if (mem[n] != null) return mem[n]; 
        
        mem[n] = trib(n - 1) + trib(n - 2) + trib(n - 3);
        return mem[n];
    }
    
    public int tribonacciBruteForce(int n) {
        if (n == 0 || n == 1) return n;
        if (n == 2) return 1;
        
        return tribonacciBruteForce(n - 1) + tribonacciBruteForce(n - 2) + tribonacciBruteForce(n - 3);
    }
}
