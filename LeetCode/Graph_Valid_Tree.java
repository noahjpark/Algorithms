/* Noah Park

You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.

Return true if the edges of the given graph make up a valid tree, and false otherwise.

*/

class Solution {
    
    // Intuition: DFS on the graph to find if each node is visited exactly once from a source node.
    // Time: O(n) to traverse the graph.
    // Space: O(n) for the recursion stack.
    List<Integer>[] map;
    boolean[] visited;
    int c = 0;
    
    public boolean validTree(int n, int[][] edges) {
        if (edges == null || (edges.length == 0 && n == 1)) return true;
        if (edges.length != n - 1) return false;
        
        map = new ArrayList[n];
        visited = new boolean[n];
        
        for (int[] edge : edges) {
            if (map[edge[0]] == null) map[edge[0]] = new ArrayList<>();
            if (map[edge[1]] == null) map[edge[1]] = new ArrayList<>();
            
            map[edge[0]].add(edge[1]);
            map[edge[1]].add(edge[0]);
        }
        
        return dfs(0, -1) && c == n;
    }
    
    public boolean dfs(int i, int p) {
        if (visited[i] || map[i] == null) return false;
        visited[i] = true;
        c++;
        
        for (int neighbor : map[i]) 
            if (p != neighbor && !dfs(neighbor, i)) return false;
        
        return true;
    }
}
