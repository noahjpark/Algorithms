// Noah Park
/*

Problem: Write a function that takes in an n x m two-dimensional array (that can be square-shaped
when n == m) and returns a one-dimensional array of all the array's elements in zigzag order.

Zigzag order starts at the top left corner of the two-dimensional array, goes down by one element,
and proceeds in a zigzag pattern all the way to the bottom right corner

*/

import java.util.ArrayList;
import java.util.List;

public class Zigzag_Traverse {
    // Optimal Solution
    // Time: O(n*m) | Space: O(n*m)
    // n*m are the total number of elements in the array
    public static List<Integer> zigzagTraverse(List<List<Integer>> array) {
        if(array.size() == 0) return new ArrayList<>(); // If the array is empty return an empty array list

        // Initialize the ans array list
        // left and right tell us the direction we're going in
        // Since we will go right after putting the first value, it is safe to assume we are going left to begin with
        // row and col represent our indices
        List<Integer> ans = new ArrayList<>();
        boolean left = true;
        boolean right = false;
        int row = 0;
        int col = 0;

        // Total number of rows and columns and number of elements
        int numrows = array.size();
        int numcols = array.get(0).size();
        int size = numrows * numcols;

        // Iterate through all elements
        for(int i = 0; i < size; i++){
            System.out.println(row + " " + col);
            ans.add(array.get(row).get(col)); // Add the current index to the return array

            // If we are moving left and hit the left wall but not the bottom (corner)
            // Update left and right to go right and move down a row to do the next zig zag
            if(left && col == 0 && row < numrows - 1){
                left = false;
                right = true;
                row++;
            }
            // Otherwise, if we are moving left and have reached the bottom row, we cannot go any deeper,
            // and must instead move down the columns
            // Also update left and right to go right
            else if(left && row == numrows - 1){
                left = false;
                right = true;
                col++;
            }
            // If we are moving right and hit the top wall but not the right wall (corner)
            // Update left and right to go left and move up a column to do the next zig zag
            else if(right && row == 0 && col < numcols - 1){
                left = true;
                right = false;
                col++;
            }
            // Otherwise, if we are moving right and have reached the right wall, we cannot go any deeper
            // Instead, we must move down the rows.
            // Also update left and right to go left
            else if(right && col == numcols - 1){
                left = true;
                right = false;
                row++;
            }
            // Normal cases, If we are going left, increment row and decrement column. Do the opposite if we are going right.
            else{
                if(left){
                    row++;
                    col--;
                }
                else{
                    row--;
                    col++;
                }
            }
        }

        // Return our fully built ans array
        return ans;
    }
}
