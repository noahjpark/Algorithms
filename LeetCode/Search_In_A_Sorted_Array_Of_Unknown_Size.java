/* Noah Park

Given an integer array sorted in ascending order, write a function to search target in nums.  If target exists, then return its index, otherwise return -1. However, the array size is unknown to you. You may only access the array using an ArrayReader interface, where ArrayReader.get(k) returns the element of the array at index k (0-indexed).

You may assume all integers in the array are less than 10000, and if you access the array out of bounds, ArrayReader.get will return 2147483647.

*/

/**
 * // This is ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface ArrayReader {
 *     public int get(int index) {}
 * }
 */

class Solution {
    public int search(ArrayReader reader, int target) {
        int left = 0, right = 10000; // we know the maximum size that the array could be so we use that as the upper bound of the left pointer
        
        // apply modified binary search on the array
        while (left <= right) {
            int mid = left + (right - left) / 2, val = reader.get(mid); // get the middle value
            
            // the only difference to a typical binary search is that we add the clause that if we are out of bounds and receive a 
            // maximum integer value, we need to move the right bounds in, as the array is much smaller than the current right side value
            if (val == Integer.MAX_VALUE || val > target) right = mid - 1;
            else if (val == target) return mid;
            else if (val < target) left = mid + 1;
        }
        
        return -1; // target not in the array
    }
}
