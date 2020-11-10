/* Noah Park

Given n and m which are the dimensions of a matrix initialized by zeros and given an array indices where indices[i] = [ri, ci]. For each pair of [ri, ci] you have to increment all cells in row ri and column ci by 1.

Return the number of cells with odd values in the matrix after applying the increment to all indices.

*/

class Solution {
    public int oddCells(int n, int m, int[][] indices) {
        int odd = 0;
        int[] rows = new int[n];
        int[] cols = new int[m];
        for(int[] i : indices){
            rows[i[0]]++;
            cols[i[1]]++;
        }
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++)
                if((rows[i] + cols[j]) % 2 == 1) odd++;
        }
        
        return odd;
    }
}
