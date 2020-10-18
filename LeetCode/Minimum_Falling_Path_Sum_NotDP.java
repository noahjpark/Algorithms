// Noah Park
// Problem: Given a square array of integers A, we want the minimum sum of a falling path through A. A falling path starts at any element in the first row, and chooses one 
// element from each row.  The next row's choice must be in a column that is different from the previous row's column by at most one.
// Non Dynamic Programming Solution

class Solution {
    public int minFallingPathSum(int[][] A) {
        if(A.length == 0) return 0;
        int c = A[0].length;
        int total = Integer.MAX_VALUE;
        
        for(int i = 0; i < c; i++){
            total = Math.min(total, helper(0, i, A));
        }
        
        return total;
    }
    
    public int helper(int r, int c, int[][] A){
        if(r == A.length) return 0;
        if(c < 0 || c == A[0].length) return 100000000;
        
        int total = Integer.MAX_VALUE;
        
        // Update the total's with the next best values.
        total = Math.min(total, A[r][c] + helper(r + 1, c, A));
        total = Math.min(total, A[r][c] + helper(r + 1, c + 1, A));
        total = Math.min(total, A[r][c] + helper(r + 1, c - 1, A));
        
        return total;
    }
}
