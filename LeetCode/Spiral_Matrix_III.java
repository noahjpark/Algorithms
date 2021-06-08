/* Noah Park

On a 2 dimensional grid with rows rows and cols columns, we start at (rStart, cStart) facing east.

Here, the north-west corner of the grid is at the first row and column, and the south-east corner of the grid is at the last row and column.

Now, we walk in a clockwise spiral shape to visit every position in this grid. 

Whenever we would move outside the boundary of the grid, we continue our walk outside the grid (but may return to the grid boundary later.) 

Eventually, we reach all rows * cols spaces of the grid.

Return a list of coordinates representing the positions of the grid in the order they were visited.

*/

class Solution {
    
    // Intuition: four pointers to maintain each next square. Then simulate just like the original spiral matrix problem.
    // Time: O(max(r, c) ^ 2) to iterate over the larger dimension squared since we are doing a spiral square.
    // Space: O(r*c) for the return matrix.
    int rows, cols;
    
    public int[][] spiralMatrixIII(int totrows, int totcols, int rStart, int cStart) {
        rows = totrows;
        cols = totcols;
        int size = rows * cols, rs = rStart - 1, re = rStart + 1, cs = cStart - 1, ce = cStart + 1, i = 1;
        int[][] res = new int[size][2];
        res[0] = new int[]{ rStart, cStart };
        
        while (i < size) {
            // right column
            for (int j = rs + 1; j <= re; j++)
                if (in(j, ce)) res[i++] = new int[]{ j, ce };
            
            // bottom row
            for (int j = ce - 1; j >= cs; j--) 
                if (in(re, j)) res[i++] = new int[]{ re, j };
            
            // left column
            for (int j = re - 1; j >= rs; j--)
                if (in(j, cs)) res[i++] = new int[]{ j, cs };
            
            // top row
            for (int j = cs + 1; j <= ce; j++)
                if (in(rs, j)) res[i++] = new int[]{ rs, j };
            
            rs--; cs--;
            re++; ce++;
        }
        
        return res;
    }
    
    public boolean in(int r, int c) {
        return r >= 0 && c >= 0 && r < rows && c < cols;
    }
}
