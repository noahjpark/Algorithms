/* Noah Park

An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).

Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.

To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), and so on. Replace the color of all of the aforementioned pixels with the newColor.

At the end, return the modified image.

*/

class Solution {
    // 2d matrix dfs implementation
    public void dfs(int[][] image, int i, int j, int color, int cur) {
        if (i < 0 || j < 0 || i >= image.length || j >= image[0].length || image[i][j] != cur) return; // out of bounds or color doesn't match
        
        image[i][j] = color; // update color
        
        // call on all neighbors
        dfs(image, i + 1, j, color, cur); dfs(image, i - 1, j, color, cur);
        dfs(image, i, j + 1, color, cur); dfs(image, i, j - 1, color, cur);
    }
    
    // 2d matrix bfs implementation
    public void bfs(int[][] image, int i, int j, int color, int cur) {
        Queue<int[]> q = new LinkedList<>(); // queue for the bfs
        q.offer(new int[] { i, j }); // push initial coordinates
        
        while (!q.isEmpty()) { // iterate until all reachable neighbors have been visited
            int[] coor = q.poll(); // get the current indices
            int r = coor[0], c = coor[1];
            
            image[r][c] = color; // update the color
            
            // add valid neighbors (index in bounds and same original color)
            if (r - 1 >= 0 && image[r - 1][c] == cur) q.offer(new int[] { r - 1, c });
            if (c - 1 >= 0 && image[r][c - 1] == cur) q.offer(new int[] { r, c - 1 });
            if (r + 1 < image.length && image[r + 1][c] == cur) q.offer(new int[] { r + 1, c });
            if (c + 1 < image[0].length && image[r][c + 1] == cur) q.offer(new int[] { r, c + 1 });
        }
    }
    
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image[sr][sc] != newColor) dfs(image, sr, sc, newColor, image[sr][sc]); // only call the update if the newColor is actually a newColor to avoid infinite looping
        return image;
    }
}
