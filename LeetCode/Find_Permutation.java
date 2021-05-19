/* Noah Park

A permutation perm of n integers of all the integers in the range [1, n] can be represented as a string s of length n - 1 where:

s[i] == 'I' if perm[i] < perm[i + 1], and
s[i] == 'D' if perm[i] > perm[i + 1].
Given a string s, reconstruct the lexicographically smallest permutation perm and return it.

*/

class Solution {
    
    // Intuition: Initialize the resultant array in increasing order. This way, we just count consecutive d's and reverse d + 1 values in the subarray from the starting point where the D's began.
    // Time: O(n) to iterate over the string and reverse parts of the resulting array. We only iterate over all elements to reverse them once instead of many each time.
    // Space: O(1) constant.
    public int[] findPermutation(String s) {
        if (s == null || s.length() == 0) return new int[]{};
        
        int n = s.length(), prev = 0, d = 0;
        int[] res = new int[n + 1];
        
        for (int i = 0; i < n + 1; i++)
            res[i] = i + 1;
        
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            
            if (c == 'I') {
                if (d > 0) reverse(res, prev, d);
                prev = i + 1;
                d = 0;
            } else d++;
        }
        
        if (d > 0) reverse(res, prev, d);
        
        return res;
    }
    
    public void reverse(int[] arr, int i, int d) {
        int upper = i + d;
        
        for (int k = i; k <= i + d / 2; k++) {
            int temp = arr[k];
            arr[k] = arr[upper - (k - i)];
            arr[upper - (k - i)] = temp;
        }
    }
}
