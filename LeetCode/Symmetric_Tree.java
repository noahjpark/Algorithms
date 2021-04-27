/* Noah Park

Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

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
    
    // Intuition: Utilize recursion to follow the steps in an identical fashion to the iteration.
    // Time: O(n) to visit all tree nodes.
    // Space: O(h) where h is the height of the tree.
    public boolean isSymmetric(TreeNode root) {
        return helper(root.left, root.right);
    }
    
    public boolean helper(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        return left.val == right.val && helper(left.left, right.right) && helper(left.right, right.left);
    }
    
    // Intuition: Utilize a queue to store adjacent nodes in the symmetric tree next to one another. If the children placement isn't mirrored, return false. Otherwise, travers in the mirrored order.
    // Time: O(n) to visit all tree nodes.
    // Space: O(n) to store in the queue at the bottom level.
    public boolean isSymmetricIterative(TreeNode root) {
        if (root == null) return true;
        if (root.left == null || root.right == null) return root.left == null && root.right == null;
        
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root.left); q.offer(root.right);
        
        while (!q.isEmpty()) {
            TreeNode n1 = q.poll(), n2 = q.poll();
            
            if (n1.val != n2.val || !identicalChildren(n1, n2)) return false;
            
            if (n1.left != null) q.offer(n1.left);
            if (n2.right != null) q.offer(n2.right);
            if (n1.right != null) q.offer(n1.right);
            if (n2.left != null) q.offer(n2.left);
        }
        
        return true;
    }
    
    // Checks the mirrored children of n1 and n2.
    public boolean identicalChildren(TreeNode n1, TreeNode n2) {
        boolean l1 = n1.left == null ? false : true, r1 = n1.right == null ? false : true;
        boolean l2 = n2.left == null ? false : true, r2 = n2.right == null ? false : true;
        return l1 == r2 && r1 == l2;
    }
}
