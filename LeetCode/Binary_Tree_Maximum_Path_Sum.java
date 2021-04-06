/* Noah Park

A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.

The path sum of a path is the sum of the node's values in the path.

Given the root of a binary tree, return the maximum path sum of any path.

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
    
    // Intuition: Simplify the other algorithm since we are already looking at the maximum path in each direction we can take advantage of the knowledge at our disposal. We look down the right and left paths and either take them or mark them as 0 so as not to take away from our current node's value. Then we update max with our path sum if larger. Finally, we return the larger of the two paths through the recursive call to a parent node since we can't take both the left and right at a lower point.
    // Time: O(n) to look through the nodes
    // Space: O(h) the depth of the tree
    int max = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        findPath(root);
        return max;
    }
    
    public int findPath(TreeNode root) {
        if (root == null) return 0;
        
        int left = Math.max(findPath(root.left), 0), right = Math.max(findPath(root.right), 0);
        max = Math.max(max, root.val + left + right); // We are looking at a current path of including the left and right subtrees
            
        return root.val + Math.max(left, right); // as a return value back to an upper path, we choose the larger of the left or right paths. We can only take both paths at a higher node.
    }
    
    
    // Intuition: For each node we look at in the tree, check the largest path on each side downwards.
    // Time: O(n*h) for each node we are looking through the depth of the tree
    // Space: O(h) for the depth of the tree
    public int maxPathSum2(TreeNode root) {
        helper(root);
        return max;
    }
    
    public void helper(TreeNode root) {
        if (root == null) return;
        
        int left = findPath(root.left, 0), right = findPath(root.right, 0);
        max = Math.max(max, Math.max(root.val, Math.max(root.val + left + right, Math.max(root.val + right, root.val + left))));
        
        helper(root.left);
        helper(root.right);
    }
    
    public int findPath(TreeNode root, int curSum) {
        if (root == null) return curSum;
        return Math.max(findPath(root.left, curSum + root.val), Math.max(curSum, findPath(root.right, curSum + root.val)));
    }
}
