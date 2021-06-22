/* Noah Park

Given the edges of a directed graph where edges[i] = [ai, bi] indicates there is an edge between nodes ai and bi, and two nodes source and destination of this graph, determine whether or not all paths starting from source eventually, end at destination, that is:

At least one path exists from the source node to the destination node
If a path exists from the source node to a node with no outgoing edges, then that node is equal to destination.
The number of possible paths from source to destination is a finite number.
Return true if and only if all roads from source lead to destination.

*/

class Solution {
    
    // Intuition: Build the graph then do a dfs over it. Before doing a dfs, check to ensure the destination is a stopping point. In the dfs function, we count how many outgoing paths there are. If there is at least 1, we are not at a stopping point and utilize the return values from each path to ensure they are all true (&=). Otherwise, we have no outgoing paths and want to ensure our current node is the destination one. We attempt to check all paths through a dfs with backtracking. If there are any self-cycles anywhere, we return false.
    // Time: O(V + E) to iterate over all paths.
    // Space: O(V + E) for the graph.
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        List<Integer>[] map = new ArrayList[n];
        boolean[] visited = new boolean[n];
        
        for (int[] edge : edges) {
            if (map[edge[0]] == null) map[edge[0]] = new ArrayList<>();
            map[edge[0]].add(edge[1]);
        }
        
        if (map[destination] != null) return false; // ensure destination is an endpoint to avoid cycling around
        
        return dfs(source, destination, map, visited);
    }
    
    public boolean dfs(int cur, int dest, List<Integer>[] map, boolean[] visited) {
        int count = 0;
        boolean res = true;
        List<Integer> children = map[cur];
        
        if (children != null) {
            for (int child : children) {
                if (child == cur) return false; // self-cycle always should return false

                // dfs with backtracking
                if (!visited[child]) {
                    visited[child] = true;
                    res &= dfs(child, dest, map, visited);
                    count++;
                    visited[child] = false;
                }
            }
        }
        
        return count > 0 ? res : cur == dest; // if we have at least one outgoing path, get the result otherwise the only stopping point should be destination (i.e. cur == dest)
    }
}
