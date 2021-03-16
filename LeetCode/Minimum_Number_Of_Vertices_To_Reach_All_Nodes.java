/* Noah Park

Given a directed acyclic graph, with n vertices numbered from 0 to n-1, and an array edges where edges[i] = [fromi, toi] represents a directed edge from node fromi to node toi.

Find the smallest set of vertices from which all nodes in the graph are reachable. It's guaranteed that a unique solution exists.

Notice that you can return the vertices in any order.

*/

class Solution {
    // Time: O(n + e) where n is n and e is the number of edges in the graph
    // Space: O(n) for the inDegree array
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        int[] inDegree = new int[n];
        List<Integer> res = new ArrayList<>();
        
        for (List<Integer> edge : edges)
            inDegree[edge.get(1)]++;
        
        for (int i = 0; i < n; i++)
            if (inDegree[i] == 0) res.add(i);
        
        return res;
    }
    
    // Time: O(n + e) where n is n and e is the number of edges in the graph
    // Space: O(n) for the inDegree map
    public List<Integer> findSmallestSetOfVerticesSuboptimal(int n, List<List<Integer>> edges) {
        Map<Integer, Integer> inDegree = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        
        for (int i = 0; i < n; i++) 
            inDegree.put(i, 0);
        
        for (List<Integer> edge : edges) 
            inDegree.put(edge.get(1), edge.get(1) + 1);
        
        for (Integer key : inDegree.keySet()) 
            if (inDegree.get(key) == 0) res.add(key);
        
        return res;
    }
}
