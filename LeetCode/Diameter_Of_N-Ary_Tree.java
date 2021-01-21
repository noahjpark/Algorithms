/* Noah Park

Given a root of an N-ary tree, you need to compute the length of the diameter of the tree.

The diameter of an N-ary tree is the length of the longest path between any two nodes in the tree. This path may or may not pass through the root.

(Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value.)

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
    int max = 0; // max diameter
    
    // driver method
    public int diameter(Node root) {
        helper(root);
        return max;
    }
    
    public int helper(Node root) {
        if (root == null || (root.children == null)) return 0; // edge cases (null node or leaf node)
        
        int left = 0, right = 0;
        
        // iterate through children and update the two longest chains if they exist
        for (Node child : root.children) {
            if (left == 0) left = 1 + helper(child);
            else if (right == 0) right = 1 + helper(child);
            else if (left < right) left = Math.max(left, 1 + helper(child));
            else right = Math.max(right, 1 + helper(child));
        }
        
        // the maximum diameter is the larger of the current max and the left + right chains
        max = Math.max(max, left + right);
        
        // return the longer chain up the recursive line
        return Math.max(left, right);
    }
}
