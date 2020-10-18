// Noah Park
// Problem: Given a square array of integers A, we want the minimum sum of a falling path through A. A falling path starts at any element in the first row, and chooses one 
// element from each row.  The next row's choice must be in a column that is different from the previous row's column by at most one.
// Dynamic Programming Solution

class Solution {
    public int minFallingPathSum(int[][] A) {
        int rows = A.length;
        for(int i = rows - 2; i >= 0; i--){
            for(int j = 0; j < rows; j++){
                // Find the best of the available neighbors from the next row within the bounds
                int sum = A[i + 1][j];
                if(j > 0) sum = Math.min(sum, A[i + 1][j - 1]);
                if(j < rows - 1) sum = Math.min(sum, A[i + 1][j + 1]);
                
                // Store the updated minimum value in the index in A
                A[i][j] += sum;
            }
        }
        
        // Find the minimum from the top row and return it
        int total = Integer.MAX_VALUE;
        for(int i : A[0])
            total = Math.min(total, i);
        return total;
    }
}
