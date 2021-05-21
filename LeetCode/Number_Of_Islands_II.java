/* Noah Park

You are given an empty 2D binary grid grid of size m x n. The grid represents a map where 0's represent water and 1's represent land. Initially, all the cells of grid are water cells (i.e., all the cells are 0's).

We may perform an add land operation which turns the water at position into a land. You are given an array positions where positions[i] = [ri, ci] is the position (ri, ci) at which we should operate the ith operation.

Return an array of integers answer where answer[i] is the number of islands after turning the cell (ri, ci) into a land.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

*/

class Solution {
    
    // Intuition: Another Union find problem. bfs/dfs takes far too long to apply to this sort of problem as the solution space changes many times. Instead, we maintain updates with the union find disjoint set data structure. As we make updates, we union adjacent islands together. Tricky parts: We need to account to make sure we don't change the count at points where there is already a 1. We know there is a 1 if the parent of that index is not -1 - meaning we have updated it before. In that case, we add the current count without changing anything and continue looping. Getting the index means reducing the 2D array to a 1D array. There are at most m*n indices so we always multiply the row index by n instead of m. 
    // Time: O(m*n + p) iterating over m*n to initialize the union find, p to check all positions.
    // Space: O(m*n) to maintain the union find data structure.
    int cnt = 0;
    int[] parent, rank;
    
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        
        parent = new int[m*n];
        rank = new int[m*n];
        
        for (int i = 0; i < m*n; i++) {
            parent[i] = -1;
            rank[i] = 0;
        }
        
        for (int[] position : positions) {
            int r = position[0], c = position[1], i = r*n+c;
            
            if (parent[i] == -1) {
                cnt++;
                parent[i] = i;

                if (r - 1 >= 0 && parent[(r-1)*n+c] > -1) union(i, (r-1)*n+c);
                if (c - 1 >= 0 && parent[r*n+c-1] > -1) union(i, r*n+c-1);
                if (r + 1 < m && parent[(r+1)*n+c] > -1) union(i, (r+1)*n+c);
                if (c + 1 < n && parent[r*n+c+1] > -1) union(i, r*n+c+1);
            }
            
            res.add(cnt);
        }
        
        return res;
    }
    
    public void union(int a, int b) {
        int pa = find(a), pb = find(b);
        
        if (pa != pb) {
            if (rank[pa] > rank[pb]) parent[pb] = pa;
            else if (rank[pa] < rank[pb]) parent[pa] = pb;
            else {
                parent[pb] = pa;
                rank[pa]++;
            }
            cnt--;
        }
    }
    
    public int find(int i) {
        if (i != parent[i]) parent[i] = find(parent[i]);
        return parent[i];
    }
    
}
