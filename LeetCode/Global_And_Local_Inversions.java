/* Noah Park

We have some permutation A of [0, 1, ..., N - 1], where N is the length of A.

The number of (global) inversions is the number of i < j with 0 <= i < j < N and A[i] > A[j].

The number of local inversions is the number of i with 0 <= i < N and A[i] > A[i+1].

Return true if and only if the number of global inversions is equal to the number of local inversions.

*/

class Solution {
    
    // Intuition: Since all local inversions are global inversions, if there exists a global inversion that is not a local inversion, we return false.
    // Time: O(n)
    // Space: O(1)
    public boolean isIdealPermutation(int[] A) {
        int max = 0;
        
        for (int i = 0; i < A.length - 2; i++) {
            max = Math.max(A[i], max);
            if (max > A[i + 2]) return false;
        }
        
        return true;
        
    }
}
