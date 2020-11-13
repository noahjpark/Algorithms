/* Noah Park

Given a m * n matrix of distinct numbers, return all lucky numbers in the matrix in any order.

A lucky number is an element of the matrix such that it is the minimum element in its row and maximum in its column.

*/

class Solution {
    public List<Integer> luckyNumbers (int[][] matrix) {
        List<Integer> lucky = new ArrayList<>();
        
        for(int i = 0; i < matrix.length; i++){
            // Find the minimum index of our current row
            int minidx = 0;
            for(int j = 1; j < matrix[0].length; j++){
                if(matrix[i][minidx] > matrix[i][j]) minidx = j;
            }
            
            // Use the minidx as the column index to check the column
            // Compare to the current minval using i and minidx and find out if it is greater than all values in that column
            int minval = matrix[i][minidx], count = 0;
            for(int row = 0; row < matrix.length; row++){
                if(row != i && matrix[row][minidx] > minval){
                    count++;
                    break;
                }
            }
            
            if(count == 0) lucky.add(minval); // If count is 0, we couldn't find any larger values
        }
        
        return lucky;
    }
}
