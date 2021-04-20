/* Noah Park

Given an integer array nums, return the largest perimeter of a triangle with a non-zero area, formed from three of these lengths. If it is impossible to form any triangle of a non-zero area, return 0.

*/

class Solution {
    
    // Intuition: Sort A so we can go from the end and try to find 3 values that form a valid triangle. A triangle can't be formed if the smallest two values are less than or equal to the largest.
    // Time: O(n log n) for sorting
    // Space: O(1) since we have ints
    public int largestPerimeter(int[] A) {
        Arrays.sort(A);
        
        for (int i = A.length - 1; i > 1; i--)
            if (A[i] < (A[i - 1] + A[i - 2])) return A[i] + A[i - 1] + A[i - 2];
        
        return 0;
    }
}
