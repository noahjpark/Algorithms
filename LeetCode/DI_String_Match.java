/* Noah Park

A permutation perm of n + 1 integers of all the integers in the range [0, n] can be represented as a string s of length n where:

s[i] == 'I' if perm[i] < perm[i + 1], and
s[i] == 'D' if perm[i] > perm[i + 1].
Given a string s, reconstruct the permutation perm and return it. If there are multiple valid permutations perm, return any of them.

*/

class Solution {
    
    // Intuition: Whenever we have an increase we can greedily take the smallest number, largest for a decrease.
    // Time: O(n) to iterate over s.
    // Space: O(n) for the result / O(1) otherwise.
    public int[] diStringMatch(String s) {
        int n = s.length(), l = 0, r = n;
        int[] res = new int[n + 1];
        
        for (int i = 0; i < n; i++) 
            res[i] = (s.charAt(i)) == 'I' ? l++ : r--;
        res[n] = l;
        
        return res;
    }
}
