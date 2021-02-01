/* Noah Park

Given the root of a binary search tree, rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree, and every node has no left child and only one right child.

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
    
    public TreeNode increasingBST(TreeNode root) {
        return increasingBST(root, null);
    }
    
    // the prev node always gets passed down the right side and will get returned when reaching a leaf node
    public TreeNode increasingBST(TreeNode root, TreeNode prev) {
        if (root == null) return prev; 
        
        TreeNode res = increasingBST(root.left, root);
        root.left = null;
        root.right = increasingBST(root.right, prev);
        
        return res;
    }
    
    // Space and Time: O(n)
    public TreeNode increasingBSTWithList(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        inorder(root, res);
        
        // update pointers
        for (int i = 0; i < res.size() - 1; i++) {
            res.get(i).left = null;
            res.get(i).right = res.get(i + 1);
        }
        
        res.get(res.size() - 1).left = null;
        
        return res.get(0);
    }
    
    // populate list in order 
    public void inorder(TreeNode root, List<TreeNode> res) {
        if (root != null) {
            inorder(root.left, res);
            res.add(root);
            inorder(root.right, res);
        }
    }
}
