/* Noah Park

Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.

You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.

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
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        // base cases
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        
        // merge trees recursively into t1
        merge(t1, t2);
        
        // reutrn the merged trees
        return t1;
    }
    
    public void merge(TreeNode t1, TreeNode t2) {
        t1.val += t2.val; // add values
        
        // compute the left subtree
        if (t1.left != null && t2.left != null) merge(t1.left, t2.left);
        else if (t1.left == null && t2.left != null) t1.left = t2.left;
        
        // compute the right subtree
        if (t1.right != null && t2.right != null) merge(t1.right, t2.right);
        else if (t1.right == null && t2.right != null) t1.right = t2.right;
    }
    
    public TreeNode mergeTreesIt(TreeNode t1, TreeNode t2) {
        // base cases
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        
        // two queues for each tree
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();
        q1.offer(t1);
        q2.offer(t2);
        
        while (!q1.isEmpty() && !q2.isEmpty()) {
            TreeNode c1 = q1.poll(), c2 = q2.poll();
            
            c1.val += c2.val; // add values
            
            // update children iteratively
            if (c1.left != null || c2.left != null) {
                if (c1.left != null) q1.offer(c1.left);
                else {
                    TreeNode temp = new TreeNode(0);
                    c1.left = temp;
                    q1.offer(temp);
                }
            }
            if (c1.right != null || c2.right != null) {
                if (c1.right != null) q1.offer(c1.right);
                else {
                    TreeNode temp = new TreeNode(0);
                    c1.right = temp;
                    q1.offer(temp);
                }
            }
            if (c2.left != null || c1.left != null) {
                if (c2.left != null) q2.offer(c2.left);
                else {
                    TreeNode temp = new TreeNode(0);
                    c2.left = temp;
                    q2.offer(temp);
                }
            }
            if (c2.right != null || c1.right != null) {
                if (c2.right != null) q2.offer(c2.right);
                else {
                    TreeNode temp = new TreeNode(0);
                    c2.right = temp;
                    q2.offer(temp);
                }
            }
        }
        
        return t1;
    }
}
