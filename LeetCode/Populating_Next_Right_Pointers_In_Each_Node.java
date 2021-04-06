/* Noah Park

You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

 

Follow up:

You may only use constant extra space.
Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.

*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    
    // Intuition: Use an iterative bfs traversal as we establish new next pointers.
    // Time: O(n) to update all pointers in the tree.
    // Space: O(1) constant
    public Node connect(Node root) {
        Node cur = root;
        while (cur != null && cur.left != null) { // cur will always be the leftmost node at each level
            Node node = cur; // bfs travel to the right
            while (node != null) {
                node.left.next = node.right; // update the left child
                
                if (node.next != null) node.right.next = node.next.left; // update the right child if necessary
                
                node = node.next; // move linearly through the tree
            }
            
            cur = cur.left;
        }
        
        return root;
    }
    
    // Intuition: Use a bfs traversal to simply put the current node's next to the next in the sequence.
    // Time: O(n) to look through the tree and update the pointers.
    // Space: O(n) to store the bottom row of the tree.
    public Node connect2(Node root) {
        if (root == null) return root;
        
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        
        while (!q.isEmpty()) {
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                Node cur = q.poll();
                
                if (i < size - 1) cur.next = q.peek();
                
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
        }
        
        return root;
    }
}
