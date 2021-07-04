/* Noah Park

Given an integer n, your task is to count how many strings of length n can be formed under the following rules:

Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
Each vowel 'a' may only be followed by an 'e'.
Each vowel 'e' may only be followed by an 'a' or an 'i'.
Each vowel 'i' may not be followed by another 'i'.
Each vowel 'o' may only be followed by an 'i' or a 'u'.
Each vowel 'u' may only be followed by an 'a'.
Since the answer may be too large, return it modulo 10^9 + 7.

*/

class Solution {

    
    // Intuition: Optimize the space solution to only use the previous values.
    // Time: O(n) to iterate over n.
    // Space: O(1) constant.
    public int countVowelPermutation(int n) {
        long pa = 1, pe = 1, pi = 1, po = 1, pu = 1;
        String vowels = "aeiou";
        int mod = 1000000007;
        
        for (int j = 2; j <= n; j++) {
            long a = pe % mod, e = (pa + pi) % mod, i = (pa + pe + po + pu) % mod, o = (pi + pu) % mod, u = pa % mod;
            pa = a; pe = e; pi = i; po = o; pu = u;
        }
        
        return (int) ((pa + pe + pi + po + pu) % mod);
    }
    
    // Intuition: Bottom up dynamic programming to calculate from the base case to the top.
    // Time: O(n) to iterate over n.
    // Space: O(26*n) to maintain the mem array.
    public int countVowelPermutation2(int n) {
        long[][] mem = new long[n + 1][26];
        String vowels = "aeiou";
        int mod = 1000000007;
        
        for (char c : vowels.toCharArray())
            mem[1][c - 'a'] = 1;
        
        for (int i = 2; i <= n; i++) {
            mem[i]['a' - 'a'] = (mem[i - 1]['e' - 'a']) % mod;
            mem[i]['e' - 'a'] = (mem[i - 1]['a' - 'a'] + mem[i - 1]['i' - 'a']) % mod;
            mem[i]['i' - 'a'] = (mem[i - 1]['a' - 'a'] + mem[i - 1]['e' - 'a'] + mem[i - 1]['o' - 'a'] + mem[i - 1]['u' - 'a']) % mod;
            mem[i]['o' - 'a'] = (mem[i - 1]['i' - 'a'] + mem[i - 1]['u' - 'a']) % mod;
            mem[i]['u' - 'a'] = (mem[i - 1]['a' - 'a']) % mod;
        }
        
        long res = 0;
        for (char c : vowels.toCharArray()) 
            res = (res + mem[n][c - 'a']) % mod;
        
        return (int) (res % mod);
    }
}
