/* Noah Park

Given an array A of distinct integers sorted in ascending order, return the smallest index i that satisfies A[i] == i.  Return -1 if no such i exists.

*/

class Solution {
    // Time: O(log n)
    // Space: O(1)
    public int fixedPoint(int[] A) {
        int res = -1, left = 0, right = A.length - 1;
        
        // binary search to find 
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (A[mid] >= mid) {
                if (A[mid] == mid) res = mid;
                right = mid - 1;
            } else left = mid + 1;
        }
        
        return res;
    }
    
    // Time: O(n)
    // Space: O(1)
    public int fixedPointLinear(int[] A) {
        for (int i = 0; i < A.length; i++) // find the index where A[i] == i
            if (A[i] == i) return i;
        
        // no index exists, return -1
        return -1;
    }
}
