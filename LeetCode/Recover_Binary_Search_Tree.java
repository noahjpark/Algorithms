/* Noah Park

You are given the root of a binary search tree (BST), where exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.

Follow up: A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?

*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    
    // Intuition: Same as the iterative but a recursive inorder algorithm. Swapping portion is the same. Maintain a previous node to make comparisons with the current.
    // Time: O(n) to iterate over the tree.
    // Space: O(h) for the recursive stack.
    TreeNode t1 = null, t2 = null, prev = null;
    
    public void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            
            if (prev != null && root.val < prev.val) {
                t1 = root;
                if (t2 == null) t2 = prev;
                else return;
            }
            
            prev = root;
            
            inorder(root.right);
        }
    }
    
    // Intuition: Utilize a stack (ArrayDeque for runtime) to implement an iterative inorder algorithm. when we find the first out of place, we update the one pointer, then we find the second out of place and break as well as swap the two pointers.
    // Time: O(n) to iterate over all nodes
    // Space: O(h) to hold the height of the tree in the stack
    public void recoverTree(TreeNode root) {
        inorder(root);
        swap(t1, t2);
    }
    
    public void recoverTreeIterative(TreeNode root) {
        Deque<TreeNode> s = new ArrayDeque<>();
        TreeNode t1 = null, t2 = null, prev = null;
        while (!s.isEmpty() || root != null) {
            while (root != null) { s.add(root); root = root.left; } // add all nodes to the left
            
            root = s.removeLast(); // take the most recent left one added
            
            // root value is out of place
            if (prev != null && root.val < prev.val) {
                t1 = root;                 // t1 will be the last out of place
                if (t2 == null) t2 = prev; // t2 will be the first out of place
                else break;
            }
            
            prev = root;
            root = root.right;
        }
        
        swap(t1, t2);
    }
    
    // Intuition: Store the ordered (preorder) in an array of size 1000 (largest size of the tree). Then swap the largest and smallest out of place to fix the ordering.
    // Time: O(n) to populate the sorted list
    // Space: O(1) since we always have 1000 nodes and O(h) if we count recursive stack space height of the tree.
    TreeNode[] sorted = new TreeNode[1000];
    int i = 0;
    
    public void recoverTree2(TreeNode root) {
        inorder2(root);
        int t1 = -1, t2 = 0;

        for (int j = i - 1; j > 0; j--) if (sorted[j].val < sorted[j - 1].val) { t1 = j; break; } // find out of place from the end
        for (int j = 0; j < i - 1; j++) if (sorted[j].val > sorted[j + 1].val) { t2 = j; break; } // find out of place from the start
        
        // swap the two out of place
        swap(sorted[t1], sorted[t2]);
    }
    
    public void swap(TreeNode i, TreeNode j) {
        int temp = i.val;
        i.val = j.val;
        j.val = temp;
    }
    
    public void inorder2(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            sorted[i++] = root;
            inorder(root.right);
        }
    }
}
