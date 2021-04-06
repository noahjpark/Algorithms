/* Noah Park

Given a binary tree

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
    
    // Intuition: Based on the idea of a headed list. Utilize a single dummy node to chain together each level. After the root chain together a full level while moving the pointer through the level as well. After move the pointer to the next level start which will be the first node after the dummy and break the link between that node and the dummy.
    // Time: O(n) to create the pointers
    // Space: O(1) constant
    public Node connect(Node root) {
        Node temp = new Node(-1), cur = root;
        while (cur != null) {
            Node ch = temp;
            while (cur != null) {
                if (cur.left != null) { ch.next = cur.left; ch = ch.next; }
                if (cur.right != null) { ch.next = cur.right; ch = ch.next; }
                cur = cur.next;
            }
            cur = temp.next;
            temp.next = null;
        }
        return root;
    }
    
    // Intuition: Recursively visit each node and utilize iteration to set each pointer.
    // Time: O(n^2) each recursive call visits an entire level
    // Space: O(1) not counting recursive stack space
    public void helper(Node root) {
        if (root == null || (root.left == null && root.right == null)) return;
        
        Node cur = root;
        while (cur != null) {
            while (cur != null && cur.left == null && cur.right == null) cur = cur.next; // move cur to the next set of nodes to update.

            if (cur == null) break;

            if (cur.left != null) {
                if (cur.right != null) cur.left.next = cur.right;
                else {
                    Node next = cur.next;

                    while (next != null && next.left == null && next.right == null) next = next.next; // find the next pointer to point cur's left to.

                    if (next != null) cur.left.next = next.left != null ? next.left : next.right;
                }
            }

            if (cur.right != null) {
                Node next = cur.next;

                while (next != null && next.left == null && next.right == null) next = next.next; // find the next pointer to point cur's left to.

                if (next != null) cur.right.next = next.left != null ? next.left : next.right;
            }

            cur = cur.next;
        }
        
        helper(root.left);
        helper(root.right);
    }
    
    public Node connect3(Node root) {
        helper(root);
        return root;
    }
    
    // Intuition: Same as the first type of this problem. Use a bfs with queue to linearly update the next pointers.
    // Time: O(n) to update all node pointers.
    // Space: O(n) to store the bottom level of the queue.
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
