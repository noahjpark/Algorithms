/* Noah Park

Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}
 

Test case format:

For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.

An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.

*/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    
    // Intuition: DFS approach to copy all nodes. Maintain the association between the original and copy through a map. The set is to tell if we already visited a node and avoid infinite recursion. To ensure all associations between copies match the originals, the map allows me to pull copies I already created of nodes I visited as I recurse.
    // Time: O(V + E) for the dfs.
    // Space: O(V) for the map/set.
    Map<Node, Node> map = new HashMap<>();
    Set<Node> visited = new HashSet<>();
    
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        dfs(node);
        return map.get(node);
    }
    
    public void dfs(Node node) {
        if (visited.contains(node)) return;
        
        Node copy = map.containsKey(node) ? map.get(node) : new Node(node.val);
        if (!map.containsKey(node)) map.put(node, copy);
        visited.add(node);
        
        for (Node neighbor : node.neighbors) {
            Node n = map.containsKey(neighbor) ? map.get(neighbor) : new Node(neighbor.val);
            if (!map.containsKey(neighbor)) map.put(neighbor, n);
            
            copy.neighbors.add(n);
            dfs(neighbor);
        }
    }
    
}
