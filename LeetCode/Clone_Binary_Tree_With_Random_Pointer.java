/* Noah Park

A binary tree is given such that each node contains an additional random pointer which could point to any node in the tree or null.

Return a deep copy of the tree.

The tree is represented in the same input/output way as normal binary trees where each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (in the input) where the random pointer points to, or null if it does not point to any node.
You will be given the tree in class Node and you should return the cloned tree in class NodeCopy. NodeCopy class is just a clone of Node class with the same attributes and constructors.

*/

/**
 * Definition for Node.
 * public class Node {
 *     int val;
 *     Node left;
 *     Node right;
 *     Node random;
 *     Node() {}
 *     Node(int val) { this.val = val; }
 *     Node(int val, Node left, Node right, Node random) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *         this.random = random;
 *     }
 * }
 */

class Solution {
    
    // Intuition: Map all nodes to their copies to easily modify the pointers.
    // Time: O(n) one pass for setting up the copies, another to update the random pointers.
    // Space: O(n) for the mappings.
    Map<Node, NodeCopy> map = new HashMap<>();
    
    public NodeCopy copyRandomBinaryTree(Node root) {
        NodeCopy copyRoot = preorder(root);
        
        for (Node node : map.keySet())
            if (node.random != null) map.get(node).random = map.get(node.random);
        
        return map.get(root);
    }
    
    public NodeCopy preorder(Node root) {
        if (root == null) return null;

        NodeCopy copy = new NodeCopy(root.val);
        map.put(root, copy);

        copy.left = preorder(root.left);
        copy.right = preorder(root.right);
        
        return copy;
    }
}
