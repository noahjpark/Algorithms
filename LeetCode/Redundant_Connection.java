/* Noah Park

In this problem, a tree is an undirected graph that is connected and has no cycles.

You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.

Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers, return the answer that occurs last in the input.

*/

class Solution {
    
    // Intuition: Utilize a dfs on each edge starting from the end to find the redundant connection. In the dfs, we try to visit all nodes, if we have a way to revisit a node not going back from where we came from, we have a redundant connection. viscount keeps track of the total nodes we visit such that we don't accidentally remove a necessary connection.
    // Time: O(n^2) to iterate over the graph for each edge.
    // Space: O(n) to maintain the graph.
    int viscount = 0;
    
    public int[] findRedundantConnection(int[][] edges) {
        if (edges == null || edges.length == 0) return new int[]{};
        
        int n = edges.length;
        List<Integer>[] map = new ArrayList[n + 1];
        for (int[] edge : edges) {
            if (map[edge[0]] == null) map[edge[0]] = new ArrayList<>();
            if (map[edge[1]] == null) map[edge[1]] = new ArrayList<>();
            
            map[edge[0]].add(edge[1]);
            map[edge[1]].add(edge[0]);
        }
        
        for (int i = n - 1; i >= 0; i--) {
            if (dfs(-1, 1, new boolean[n + 1], map, edges[i]) && viscount == n) return edges[i];
            viscount = 0;
        }
        
        return new int[]{};
    }
    
    public boolean dfs(int from, int cur, boolean[] visited, List<Integer>[] map, int[] exclude) {
        visited[cur] = true;
        List<Integer> children = map[cur];
        viscount++;
        if (children.size() == 1 && children.get(0) == from) return true;
        
        for (int child : children) {
            if (child == from || (cur == exclude[0] && child == exclude[1]) || (cur == exclude[1] && child == exclude[0])) continue;
            
            if (visited[child]) return false;
            else {
                visited[child] = true;
                dfs(cur, child, visited, map, exclude);
            }
        }
    
        return true;
    }
}
