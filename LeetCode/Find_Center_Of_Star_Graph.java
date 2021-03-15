/* Noah Park

There is an undirected star graph consisting of n nodes labeled from 1 to n. A star graph is a graph where there is one center node and exactly n - 1 edges that connect the center node with every other node.

You are given a 2D integer array edges where each edges[i] = [ui, vi] indicates that there is an edge between the nodes ui and vi. Return the center of the given star graph.

*/

class Solution {
    public int findCenter(int[][] edges) {
        HashMap<Integer, Integer> edgeMap = new HashMap<>(); // map the edges to one another
        
        // iterate over edges
        for (int[] edge : edges) {
            // update edges
            edgeMap.put(edge[0], edgeMap.getOrDefault(edge[0], 0) + 1);
            edgeMap.put(edge[1], edgeMap.getOrDefault(edge[1], 0) + 1);
            
            // if either edge is larger than 1, it is the center
            if (edgeMap.get(edge[0]) > 1) return edge[0];
            if (edgeMap.get(edge[1]) > 1) return edge[1];
        }
        
        return -1; // center doesn't exist.
    }
}
