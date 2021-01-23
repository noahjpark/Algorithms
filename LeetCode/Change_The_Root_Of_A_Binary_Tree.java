/* Noah Park

Given the root of a binary tree and a leaf node, reroot the tree so that the leaf is the new root.

You can reroot the tree with the following steps for each node cur on the path starting from the leaf up to the root​​​ excluding the root:

If cur has a left child, then that child becomes cur's right child.
cur's original parent becomes cur's left child. Note that in this process the original parent's pointer to cur becomes null, making it have at most one child.
Return the new root of the rerooted tree.

Note: Ensure that your solution sets the Node.parent pointers correctly after rerooting or you will receive "Wrong Answer".

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
    // Time: O(n) 
    // Space: O(n)
    public Node flipBinaryTree(Node root, Node leaf) {
        if (root == null) return null; // edge cases
        
        Queue<Node> q = new LinkedList<>(); // use a queue for level order traversal backwards
        q.offer(leaf);
        Map<Node, Node> parents = new HashMap<>(); // mapping a node to its parent
        
        // traverse the tree
        while (!q.isEmpty()) {
            Node cur = q.poll(), l = null, r = cur.right; 
            
            // edge case when we are done updating the tree, we just need to manipulate the root depending on which side we came from and break from the loop
            if (cur == root) {
                if (parents.containsValue(cur.left)) {
                    cur.parent = cur.left;
                    cur.left = null;
                } else {
                    cur.parent = cur.right;
                    cur.right = null;
                }
                break;
            }
            
            // if the left is not null and not already a parent, update the right pointer
            // if the parent is not null and not already a parent, update the left pointer
            if (cur.left != null && !parents.containsValue(cur.left)) r = cur.left;
            if (cur.parent != null && !parents.containsValue(cur.parent)) l = cur.parent;
            
            // update node's pointers
            cur.left = l;
            cur.right = r;
            if (parents.containsKey(cur)) cur.parent = parents.get(cur);
            
            // update parent list
            parents.put(cur.left, cur);
            parents.put(cur.right, cur);
            
            if (cur.left != null) q.offer(cur.left);
            if (cur.right != null) q.offer(cur.right);
        }
        
        leaf.parent = null; // ensure the new root has no parent
        
        return leaf;
    }
    
    
    // Debug printing statement to print information about the tree in level traversal order
    public void print(Node leaf) {
        Queue<Node> q = new LinkedList<>();
        q.offer(leaf);
        int level = 1;
        
        while (!q.isEmpty()) {           
            Node cur = q.poll();
            
            Integer l = cur.left != null ? cur.left.val : null;
            Integer r = cur.right != null ? cur.right.val : null;
            Integer p = cur.parent != null ? cur.parent.val : null;
            System.out.println(cur.val + " " + l + " " + r +  " " + p);
            
            if (cur.left != null) q.offer(cur.left);
            if (cur.right != null) q.offer(cur.right);
        }
    }
}
