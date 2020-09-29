// Noah Park
/*

Problem: You're given a two-dimensional array (a matrix) of potentially unequal height and width
containing only 0s and 1s. Each 0 represents land, and each 1 represents part of a river. A river
consists of any number of 1s that are either horizontally or vertically adjacent (but not diagonally)
adjacent). The number of adjacent 1s forming a river determines its size.

Note that a river can twist. In other words, it doesn't have to be a straight vertical line or a straight
horizontal line; it can be L-shaped, for example.

Write a function that returns an array of the sizes of all rivers represented in the input matrix. The sizes
don't need to be in any particular order.

*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class River_Sizes {
    // Breadth-first Search on a matrix
    public static void bfs(int[][] matrix, int i, int j, int length, ArrayList<Integer> ans){
        // Use a queue to implement bfs
        // Start by putting the current (i,j) index into it
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});

        // Iterate until it is empty (visited all positions)
        while(!q.isEmpty()){
            // Get our current row and column indexes
            int[] indices = q.remove();
            int row = indices[0];
            int col = indices[1];

            // If we happened to update/visit that position, we don't want to double count
            if(matrix[row][col] != 1){
                continue;
            }

            // Mark that position as "visited" using -1
            // Note: if we didn't want to destroy this matrix, we could use a different
            // matrix to mark the corresponding positions at the cost of more space.
            // Also increment our current river length
            matrix[row][col] = -1;
            length++;

            // Check all neighbors:
            // Note: We are only adding to the queue if the neighbor is also a valid river
            // as well.
            if(row > 0){
                if(matrix[row - 1][col] == 1){
                    q.add(new int[]{row - 1, col});
                }
            }
            if(col > 0){
                if(matrix[row][col - 1] == 1){
                    q.add(new int[]{row, col - 1});
                }
            }
            if(row < matrix.length - 1){
                if(matrix[row + 1][col] == 1){
                    q.add(new int[]{row + 1, col});
                }
            }
            if(col < matrix[0].length - 1){
                if(matrix[row][col + 1] == 1){
                    q.add(new int[]{row, col + 1});
                }
            }
        }

        // Once the entire river has been counted, update the ans array list
        ans.add(length);
    }

    // Time: O(wh) | Space: O(1)
    // w is the width of the matrix and h is the height of the matrix
    public static List<Integer> riverSizes(int[][] matrix) {
        ArrayList<Integer> ans = new ArrayList<>(); // Store river sizes in an array list

        // Iterate through all elements in the matrix
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == 1){ // Only call bfs on river values
                    bfs(matrix, i, j, 0, ans); // Initialize length to 0 on the call
                }
            }
        }

        // Return build ans array list
        return ans;
    }
}
