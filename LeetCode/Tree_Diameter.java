/* Noah Park

Given an undirected tree, return its diameter: the number of edges in a longest path in that tree.

The tree is given as an array of edges where edges[i] = [u, v] is a bidirectional edge between nodes u and v.  Each node has labels in the set {0, 1, ..., edges.length}.

*/

class Solution {
    
    // Intuition: Initally went with a brute force nested dfs where I visit the entire graph treating each node in the graph as a starting point. This timed out of course, however I realized that we only had to consider 0 as the starting node. I first went the wrong direction trying to maximize two paths at each point, however this is incorrect since that would be creating multipaths rather than a single one. Instead, we always only consider to return the longest individual path at any given node. However, if we encounter multiple paths, we see if taking two of them (longest two) results in the longest path in the graph. This way we are maximizing both nodes being on the individual path or being at a pivot point where they are in the middle of the path.
    // Time: O(n) to visit the nodes in the graph once.
    // Space: O(h) height of the tree from the starting point.
    int res = 0;
    
    public int treeDiameter(int[][] edges) {
        int e = edges.length + 1;
        List<Integer>[] map = new ArrayList[e];
        
        for (int[] edge : edges) {
            if (map[edge[0]] == null) map[edge[0]] = new ArrayList<>();
            if (map[edge[1]] == null) map[edge[1]] = new ArrayList<>();
            
            map[edge[0]].add(edge[1]);
            map[edge[1]].add(edge[0]);
        }
        
        boolean[] visited = new boolean[e];
        visited[0] = true;
        dfs(map, visited, 0);
        
        return res;
    }
    
    public int dfs(List<Integer>[] map, boolean[] visited, int cur) {
        int max1 = 0, max2 = 0;
        
        for (Integer num : map[cur]) {
            if (!visited[num]) {
                visited[num] = true;
                int d = dfs(map, visited, num) + 1;
                
                if (d > max2) max2 = d;
                if (max2 > max1) {
                    int temp = max2;
                    max2 = max1;
                    max1 = temp;
                }
                visited[num] = false;
            }
        }
        
        res = Math.max(res, max1 + max2);
        
        return max1;
    }
}
