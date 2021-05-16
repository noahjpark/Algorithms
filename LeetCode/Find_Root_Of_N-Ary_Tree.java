/* Noah Park

You are given all the nodes of an N-ary tree as an array of Node objects, where each node has a unique value.

Return the root of the N-ary tree.

Custom testing:

An N-ary tree can be serialized as represented in its level order traversal where each group of children is separated by the null value (see examples).

*/

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    
    public Node() {
        children = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        children = new ArrayList<Node>();
    }
    
    public Node(int _val,ArrayList<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    
    // Intuition: We know that the root is the only node in the tree that doesn't have a parent. Each time we find a node with a parent, we mark it. Originally, I used a hash set but improved the complexity by utilizing a bit map with the size of the number of nodes in the tree (since all node values are unique).
    // Time: O(n) to iterate over the tree twice (one to populate the bit map, one to find the node that isn't in the bit map). Note that if we find a node that's already been visited, we don't revisit.
    // Space: O(n) for the set, O(1) for the bit map.
    Set<Integer> set = new HashSet<>();
    boolean[] map = new boolean[50000];
    
    public Node findRoot(List<Node> tree) {
        if (tree == null || tree.size() == 0) return null;
        
        for (Node n : tree) 
            visit(n);
        
        for (Node root : tree)
            if (!map[root.val]) return root;
        
        return null;
    }
    
    public void visit(Node n) {
        if (map[n.val]) return;
        
        for (Node child : n.children) {
            visit(child);
            map[child.val] = true;
        }
    }
}
