/* Noah Park

You are given an m x n integer matrix grid​​​, where m and n are both even integers, and an integer k.

The matrix is composed of several layers, which is shown in the below image, where each color is its own layer:



A cyclic rotation of the matrix is done by cyclically rotating each layer in the matrix. To cyclically rotate a layer once, each element in the layer will take the place of the adjacent element in the counter-clockwise direction. An example rotation is shown below:


Return the matrix after applying k cyclic rotations to it.

*/

class Solution {
    
    // Intuition: Iterate layer by layer over the grid. At each layer, modularize k around the maximum rotations necessary (layer length) then store temporary lists of where the start of the rotation is and the wrap around. Then, simply iterate over the lists and populate the temp list.
    // Time: O(n*m) to iterate over the grid. technically two pass.
    // Space: O(n*m) but could modify grid for constant space.
    public int[][] rotateGrid(int[][] grid, int kk) {
        int rows = grid.length, cols = grid[0].length, outerLayer = 2*rows + 2*cols - 4;
        int[][] res = new int[rows][cols];
        
        int rs = 0, re = rows - 1, cs = 0, ce = cols - 1, elements = 0;
        
        while (re > rs && ce > cs) {
            int outer = 2*(re - rs + 1) + 2*(ce - cs + 1) - 4, c = 0;
            int k = kk % outer;
            List<Integer> temp = new ArrayList<>(), end = new ArrayList<>();
            
            for (int i = cs; i <= ce; i++) {
                if (c++ < k) end.add(grid[rs][i]);
                else temp.add(grid[rs][i]);
            }
            
            for (int i = rs + 1; i <= re; i++) {
                if (c++ < k) end.add(grid[i][ce]);
                else temp.add(grid[i][ce]);
            }
            
            for (int i = ce - 1; i >= cs; i--) {
                if (c++ < k) end.add(grid[re][i]);
                else temp.add(grid[re][i]);
            }
            
            for (int i = re - 1; i > rs; i--) {
                if (c++ < k) end.add(grid[i][cs]);
                else temp.add(grid[i][cs]);
            }
            
            int idx = 0, j = 0, n = temp.size();
            
            for (int i = cs; i <= ce; i++) 
                res[rs][i] = (idx < n) ? temp.get(idx++) : end.get(j++);
            
            for (int i = rs + 1; i <= re; i++) 
                res[i][ce] = (idx < n) ? temp.get(idx++) : end.get(j++);
                
            for (int i = ce - 1; i >= cs; i--) 
                res[re][i] = (idx < n) ? temp.get(idx++) : end.get(j++);
            
            for (int i = re - 1; i > rs; i--)
                res[i][cs] = (idx < n) ? temp.get(idx++) : end.get(j++);
            
            elements += outer; rs++; cs++; re--; ce--;
        }
        
        return res;
    }
}
