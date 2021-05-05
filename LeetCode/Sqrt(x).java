/* Noah Park

Given a non-negative integer x, compute and return the square root of x.

Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result is returned.

*/

class Solution {
    
    // Intuition: Modified binary search to consider each square at the midpoint. The only edge case that becomes a problem is integer overflow.
    // Time: O(log n) since we are splitting our search space in half at each point.
    // Space: O(1) constant.
    int limit = (int) Math.sqrt(Integer.MAX_VALUE);
    
    public int mySqrt(int x) {
        int left = 0, right = x;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid > limit) { right = mid - 1; continue; }
            
            if (mid*mid == x) return mid;
            else if (mid*mid < x) left = mid + 1;
            else right = mid - 1;
        }
        
        return left*left > x ? left - 1 : left;
    }
}
