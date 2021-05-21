/* Noah Park

Given a node in a binary search tree, return the in-order successor of that node in the BST. If that node has no in-order successor, return null.

The successor of a node is the node with the smallest key greater than node.val.

You will have direct access to the node but not to the root of the tree. Each node will have a reference to its parent node. Below is the definition for Node:

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
    
    // Intuition: Consider what the three cases of the next in the sequence would be. First would be if it is in a subtree of the current node. In this case, we would find the smallest in its right subtree by starting at its right child then moving leftwards as deep as possible. The next case would be if we were finishing a left subtree and simply moved to the parent (i.e. the parent's value is larger than the current's). Otherwise the last and trickier case would be finding the next higher subtree as the next in the sequence. This would involve moving up through the parents until we do find a parent that is larger and returning that value.
    // Time: O(log n) at worst we would go from the bottom to the top.
    // Space: O(1) constant.
    public Node inorderSuccessor(Node node) {
        if (node.right != null) {
            Node cur = node.right;
            while (cur.left != null) cur = cur.left;
            return cur;
        }
        if (node.parent != null) {
            Node cur = node.parent;
            while (cur.parent != null && cur.val < node.val) cur = cur.parent;
            return cur.val > node.val ? cur : null;
        }
        
        return null;
    }
    
    // Intuition: More straightforward and easy step by step approach where you find the root, then do a full inorder traversal to find the next in the sequence.
    // Time: O(n + logn) for finding the root then traversing the tree.
    // Space: O(h) height of the tree for the recursion.
    Node successor = null;
    boolean found = false;
    
    public Node inorderSuccessor2(Node node) {
        Node root = node;
        while (root.parent != null) root = root.parent;
        inorder(root, node);
        return successor;
    }
    
    public void inorder(Node root, Node node) {
        if (root == null) return;
        
        inorder(root.left, node);
        if (found) { successor = root; found = false; }
        if (root == node) found = true;
        inorder(root.right, node);
    }
}
