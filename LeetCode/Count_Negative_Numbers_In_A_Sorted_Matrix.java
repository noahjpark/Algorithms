/* Noah Park

Given a m * n matrix grid which is sorted in non-increasing order both row-wise and column-wise. 

Return the number of negative numbers in grid.

*/

class Solution {
    public int countNegatives(int[][] grid) {
        int count = 0;
        
        for(int[] row : grid){
            
            int left = 0, right = row.length - 1;
            
            // After this loop, left will be on the first negative number
            while(left <= right){
                int mid = left + (right - left) / 2;
                if(row[mid] < 0) right = mid - 1;
                else left = mid + 1;
            }
            
            count += (row.length - left);
        }
        
        return count;
    }
}
