/* Noah Park

Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

*/

class Solution {
    public int countComponents(int n, int[][] edges) {
        // ids will be the "union find" data structure
        int[] ids = new int[n];
        
        // initialize all nodes to point to themselves
        for (int i = 0; i < n; i++) ids[i] = i;
        
        // union the nodes with the given edges
        for (int[] edge : edges) union(edge[0], edge[1], ids);
        
        // store all parent/source nodes in a set (no duplicates) and return its size
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < ids.length; i++) set.add(find(ids[i], ids));
        return set.size();
    }
    
    public void union(int edge1, int edge2, int[] ids) {
        // find the overall parents of each edge, update one nodes parent to the other connecting the two
        int p1 = find(ids[edge1], ids), p2 = find(ids[edge2], ids);
        ids[p1] = p2;
    }
    
    public int find(int edge, int[] ids) {
        // path compression
        // continue updating ids[edge] until we reach the parent node which will be when the id[edge] is equal to the edge
        if(ids[edge] != edge) ids[edge] = find(ids[edge], ids);
        return ids[edge];
    }
}
