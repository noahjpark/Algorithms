/* Noah Park

Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).

Each node will have a reference to its parent node. The definition for Node is below:

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}
According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)."

*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/

class Solution {
    
    // Intuition: Tortoise and hare algorithm (Circular track). Eventually, the pointers will meet at the intersection of the paths which will be the LCA.
    // Time: O(h) for two passes to reach the intersection.
    // Space: O(1) constant
    public Node lowestCommonAncestor(Node p, Node q) {
        Node i = p, j = q;
        while (i != j) {
            i = i == null ? q : i.parent;
            j = j == null ? p : j.parent;
        }
        return i;
    }

    // Intuition: Get the depth of p and q from the root node. Then adjust until p and q are on the same level. Finally, move them up together until they reach the same node.
    // Time: O(h) three depth traversals (one for p, one for q, one to bring them to their LCA).
    // Space: O(h) height of the tree for the height algorithm and bringing them to the LCA
    public Node lowestCommonAncestor2(Node p, Node q) {
        int ph = findHeight(p, 0), qh = findHeight(q, 0);
        
        while (ph < qh) { q = q.parent; qh--; }
        while (qh < ph) { p = p.parent; ph--; }
        while (p != q) { p = p.parent; q = q.parent; }
        
        return p;
    }
    
    public int findHeight(Node n, int depth) {
        if (n.parent == null) return depth;
        return findHeight(n.parent, depth + 1);
    }
}
