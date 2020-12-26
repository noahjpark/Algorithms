/* Noah Park

A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and going in the bottom-right direction until reaching the matrix's end. For example, the matrix diagonal starting from mat[2][0], where mat is a 6 x 3 matrix, includes cells mat[2][0], mat[3][1], and mat[4][2].

Given an m x n matrix mat of integers, sort each matrix diagonal in ascending order and return the resulting matrix.

*/

class Solution {
    public int[][] diagonalSort(int[][] mat) {
        if (mat == null || mat.length == 0) return mat; // edge case
        
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>(); // sort using a min heap
        int rows = mat.length, cols = mat[0].length;
        
        // iterate through the mat and put all diagonal elements into each min heap (one for each diagonal)
        for (int i = 0; i < rows; i++) { 
            for (int j = 0; j < cols; j++) {
                if (!map.containsKey(i - j)) map.put(i - j, new PriorityQueue<>((a, b) -> Integer.compare(a, b)));
                map.get(i - j).offer(mat[i][j]);
            }
        }
        
        // populate diagonals from top left to bottom right using the min heaps
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mat[i][j] = map.get(i - j).poll();
            }
        }
        
        return mat;
    }
}
