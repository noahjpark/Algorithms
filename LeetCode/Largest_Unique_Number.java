/* Noah Park

Given an array of integers A, return the largest integer that only occurs once.

If no integer occurs once, return -1.

*/

class Solution {
    // Time: O(n)
    // Space: O(n)
    public int largestUniqueNumber(int[] A) {
        // edge cases
        if (A == null || A.length == 0) return -1;
        else if (A.length == 1) return A[0];
        
        // frequency map
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int num : A) map.put(num, map.getOrDefault(num, 0) + 1);
        
        int res = -1;
        
        // only update res based on non duplicates
        for (Integer key : map.keySet()) 
            if (map.get(key) == 1 && key > res) res = key;
    
        return res;    
    }
    
    // Time: O(n log n)
    // Space: O(1)
    public int largestUniqueNumberSort(int[] A) {
        // edge cases
        if (A == null || A.length == 0) return -1;
        else if (A.length == 1) return A[0];
        
        // sort all elements
        Arrays.sort(A);
        
        int idx = A.length - 1;
        
        // iterate from the end checking for the largest non-duplicate element
        while (idx > 0) {
            if (A[idx] != A[idx - 1]) return A[idx];
            
            int newidx = idx;
            
            // move backwards to the next new number
            while (newidx > -1 && A[newidx] == A[idx]) newidx--;
            
            idx = newidx;
        }
        
        // if the idx ends up at -1, all elements have duplicates, else idx will be the rightmost non duplicate idx in A
        return idx == -1 ? idx : A[idx];
    }
}
