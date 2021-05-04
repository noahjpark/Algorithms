/* Noah Park

You are given an array rectangles where rectangles[i] = [li, wi] represents the ith rectangle of length li and width wi.

You can cut the ith rectangle to form a square with a side length of k if both k <= li and k <= wi. For example, if you have a rectangle [4,6], you can cut it to get a square with a side length of at most 4.

Let maxLen be the side length of the largest square you can obtain from any of the given rectangles.

Return the number of rectangles that can make a square with a side length of maxLen.

*/

class Solution {
    
    // Intuition: Maintain the maximum square so far and the count of this max. Each time we update max, we reset count. We increment count if the current square matches max.
    // Time: O(n) to iterate over the rectangles.
    // Space: O(1) constant.
    public int countGoodRectangles(int[][] rectangles) {
        int max = 0, count = 0;
        
        for (int[] rectangle : rectangles) {
            int square = Math.min(rectangle[0], rectangle[1]);
            
            if (square > max) { max = square; count = 0; }
            if (square == max) count++;
        }
        
        return count;
    }
}
