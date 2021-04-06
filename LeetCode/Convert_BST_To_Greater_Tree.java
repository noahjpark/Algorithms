/* Noah Park

Given the root of a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

As a reminder, a binary search tree is a tree that satisfies these constraints:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Note: This question is the same as 1038: https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/

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
    
    // Intuition: Utilize a modified inorder algorithm (backwards). Solve for the right first (sum is initially 0 and accumulated once we start going to the left). Since we return the sum when root is null, we can return the left value at the end of the function. This is utilized as the sum of all of the nodes in front of the current one once returned. 
    // Time: O(n) to update all nodes
    // Space: O(h) height of the tree for the modified inorder algorithm.
    // Note: This solution is far better than the one below it as it minimizes time and space in all cases.
    public TreeNode convertBST(TreeNode root) {
        inorder(root, 0);
        //pi(root);
        return root;
    }
    
    public int inorder(TreeNode root, int sum) {
        if (root == null) return sum;
        
        root.val += inorder(root.right, sum);
        int left = inorder(root.left, root.val);
        
        return left;
    }
    
    public void pi(TreeNode root) {
        if (root != null) {
            pi(root.left);
            System.out.print(root.val + " ");
            pi(root.right);
        }
    }
    
    // Intuition: Store all sums in a list. Prefix from the end then update the nodes in the tree.
    // Time: O(n) for inorder and sum passes
    // Space: O(n) for the sums
    List<Integer> sums = new ArrayList<>();
    int idx = 0;
    
    public TreeNode convertBST2(TreeNode root) {
        inorder(root);
        
        for (int i = sums.size() - 2; i >= 0; i--) 
            sums.set(i, sums.get(i + 1) + sums.get(i));
        
        inorderSum(root);
        return root;
    }
    
    public void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            sums.add(root.val);
            inorder(root.right);
        }
    }
    
    public void inorderSum(TreeNode root) {
        if (root != null) {
            inorderSum(root.left);
            root.val = sums.get(idx++);
            inorderSum(root.right);
        }
    }
}
