/* Noah Park

An n x n grid is composed of 1 x 1 squares where each 1 x 1 square consists of a '/', '\', or blank space ' '. These characters divide the square into contiguous regions.

Given the grid grid represented as a string array, return the number of regions.

Note that backslash characters are escaped, so a '\' is represented as '\\'.

*/

class Solution {
    
    // Intuition: Union find by splitting each square into four parts. This is a bit like the number of islands ii problem.
    // Time: O(n*n*a(n)) to iterate over the grid and the a(n) for the uf algorithm.
    // Space: O(n*n) for the union find ds.
    int[] parents;
    int[] rank;
    
    public int regionsBySlashes(String[] grid) {
        int n = grid.length, res = 0;
        
        parents = new int[4*n*n];
        rank = new int[4*n*n];
        
        for (int i = 0; i < 4*n*n; i++) {
            parents[i] = i;
            rank[i] = 1;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cur = 4 * (i*n + j);
                char c = grid[i].charAt(j);
                
                if (c != '/') {
                    union(cur, cur  + 3);
                    union(cur + 1, cur + 2);
                } 
                
                if (c != '\\') {
                    union(cur, cur + 1);
                    union(cur + 2, cur + 3);
                }
            
                if (i + 1 < n) union(cur + 2, cur + 4*n);
                if (i - 1 >= 0) union(cur, cur - 4*n + 2);
                if (j + 1 < n) union(cur + 3, (cur + 4) + 1);
                if (j - 1 >= 0) union(cur + 1, (cur - 4) + 3);
            }
        }
        
        for (int i = 0; i < 4*n*n; i++) 
            if (find(i) == i) res++;
               
        return res;
    }
    
    public void union(int a, int b) {
        int pa = find(a), pb = find(b);
        
        if (pa != pb) {
            if (rank[pa] > rank[pb]) {
                parents[pb] = pa;
                rank[pa] += rank[pb];
            } else {
                parents[pa] = pb;
                rank[pb] += rank[pa];
            }
        }
    }
    
    public int find(int a) {
        if (a != parents[a]) parents[a] = find(parents[a]);
        return parents[a];
    }
    
}
