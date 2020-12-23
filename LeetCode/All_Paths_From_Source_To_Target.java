/* Noah Park

Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1, and return them in any order.

The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).

*/

class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        if (graph == null || graph.length == 0) return new ArrayList<>(); // edge cases
        
        List<List<Integer>> paths = new ArrayList<>(); // resulting paths
        
        findPath(graph, paths, new ArrayList<>(), 0); // compute paths
        
        return paths; // return the paths
    }
    
    public void findPath(int[][] graph, List<List<Integer>> paths, List<Integer> current, int cur) {
        if (cur == graph.length - 1) { // we have gotten a path to the n - 1 node
            current.add(cur); // add the final node
            paths.add(new ArrayList<>(current)); // make sure we only add a shallow copy so we don't modify it later
            return; // stop this path
        }
        for (int i = 0; i < graph[cur].length; i++) { // iterate over all possible paths
            current.add(cur); // add the current choice
            findPath(graph, paths, new ArrayList<>(current), graph[cur][i]); // follow that path greedily
            current.remove(current.size() - 1); // backtrack and try a new choice
        }
    }
}
