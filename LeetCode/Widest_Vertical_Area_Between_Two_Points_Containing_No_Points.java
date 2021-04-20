/* Noah Park

Given n points on a 2D plane where points[i] = [xi, yi], Return the widest vertical area between two points such that no points are inside the area.

A vertical area is an area of fixed-width extending infinitely along the y-axis (i.e., infinite height). The widest vertical area is the one with the maximum width.

Note that points on the edge of a vertical area are not considered included in the area.

*/

class Solution {
    
    // Intuition: Simply sort based on x values and compare each x value to another to find the maximum width between two points.
    // Time: O(nlogn) for sorting
    // Space: O(1)
    public int maxWidthOfVerticalArea2(int[][] points) {
        Arrays.sort(points, (a, b) -> (a[0] - b[0]));
        int width = 0;
        
        for (int i = 0; i < points.length - 1; i++)
            width = Math.max(width, points[i + 1][0] - points[i][0]);
        
        return width;
    }
}
