// Noah Park
/*

Problem: Write a function that takes in an n x m two-dimensional array (that can be square-shaped
when n == m) and returns a one-dimensional array of all the array's elements in spiral order.

Spiral order starts at the top left corner of the two-dimensional array, goes to the right, and
proceeds in a spiral pattern all the way until every element has been visited.

*/

import java.util.ArrayList;
import java.util.List;

public class Spiral_Traverse {
    // Time: O(n) | Space: O(n)
    public static List<Integer> spiralTraverse(int[][] array) {
        // Declare list to hold
        List<Integer> ans = new ArrayList<Integer>();

        // Overall width and height of the array
        int width = array[0].length;
        int height = array.length;

        // Keep track of the starts and ends of our current column/row
        int wstart = 0;
        int hstart = 0;
        int wend = width; // The wend and hend represent the ending column and row values + 1
        int hend = height;

        // Break once the start of the rows or columns reaches the end of the rows or columns
        while(wstart < wend && hstart < hend){

            // Populate the top row of our current sub-matrix
            // Start from the the start of our current column, wstart, and go to the end of
            // our current column, wend.
            for(int j = wstart; j < wend; j++){
                ans.add(array[hstart][j]); // hstart represents our top of our current sub-matrix
            }

            // Populate the right column of our current sub-matrix
            // We populated the last first value of the column in the previous for loop, and we will
            // populate the last value in the next for loop. We need to populate all values in between.
            // To bound this, add one to the start of our beginning row and subtract one from our ending column.
            for(int j = hstart + 1; j < hend - 1; j++){
                ans.add(array[j][wend - 1]); // Now the row is changing, j, and wend - 1 represents the ending column of our sub-matrix
            }

            // Populate the bottom row of our current sub-matrix
            // Start from the ending value of our current column, and iterate through the start of our current
            // column.
            for(int j = wend - 1; j >= wstart; j--){
                if(hstart == hend - 1){ // If the starting row and ending row are at the same value, we have a singular row we added earlier. Avoid adding this again by breaking from the loop.
                    break;
                }
                ans.add(array[hend - 1][j]); // Follow the pattern from above and populate using the persisting ending row, and the changing column, j.
            }

            // Populate the left column of our current sub-matrix excluding the bottom and top values, since we already added them in a previous loop.
            for(int j = hend - 2; j > hstart; j--){ // Start at the first inner value and iterate until we reach the starting value without adding the starting value
                if(wstart == wend - 1){ // If the starting column and ending column are at the same value, we have a singular column we added earlier. Avoid adding this again by breaking from the loop.
                    break;
                }
                ans.add(array[j][wstart]); // Populate using the array arithmetic just like above
            }

            // Move our start and ending pointers inwards by one
            wstart++;
            hstart++;
            wend--;
            hend--;
        }

        // Return our ans list
        return ans;
    }

    // Recursive solution done in the same time and space
    // I'm not going to bother commenting anything more, as they are the same implementation in two different styles
    public static List<Integer> spiralTraverseRec(int[][] array) {
        List<Integer> ans = new ArrayList<>();
        return spiralRec(ans, array, 0, 0, array[0].length, array.length);
    }

    public static List<Integer> spiralRec(List<Integer> ans, int[][] array, int wstart, int hstart, int wend, int hend){
        if(wstart >= wend || hstart >= hend){
            return ans;
        }

        // Populate the top row of our current sub-matrix
        // Start from the the start of our current column, wstart, and go to the end of
        // our current column, wend.
        for(int j = wstart; j < wend; j++){
            ans.add(array[hstart][j]); // hstart represents our top of our current sub-matrix
        }

        // Populate the right column of our current sub-matrix
        // We populated the last first value of the column in the previous for loop, and we will
        // populate the last value in the next for loop. We need to populate all values in between.
        // To bound this, add one to the start of our beginning row and subtract one from our ending column.
        for(int j = hstart + 1; j < hend - 1; j++){
            ans.add(array[j][wend - 1]); // Now the row is changing, j, and wend - 1 represents the ending column of our sub-matrix
        }

        // Populate the bottom row of our current sub-matrix
        // Start from the ending value of our current column, and iterate through the start of our current
        // column.
        for(int j = wend - 1; j >= wstart; j--){
            if(hstart == hend - 1){ // If the starting row and ending row are at the same value, we have a singular row we added earlier. Avoid adding this again by breaking from the loop.
                break;
            }
            ans.add(array[hend - 1][j]); // Follow the pattern from above and populate using the persisting ending row, and the changing column, j.
        }

        // Populate the left column of our current sub-matrix excluding the bottom and top values, since we already added them in a previous loop.
        for(int j = hend - 2; j > hstart; j--){ // Start at the first inner value and iterate until we reach the starting value without adding the starting value
            if(wstart == wend - 1){ // If the starting column and ending column are at the same value, we have a singular column we added earlier. Avoid adding this again by breaking from the loop.
                break;
            }
            ans.add(array[j][wstart]); // Populate using the array arithmetic just like above
        }

        return spiralRec(ans, array, wstart + 1, hstart + 1, wend - 1, hend - 1);
    }
}
