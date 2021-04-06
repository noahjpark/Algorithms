/* Noah Park

You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.

The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.

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
    
    // Intuition: Use a preorder traversal to build the parenthesis (including empty ones). At the end of each left and right subtree traversal, remove from the end moving backwards any unnecessary parenthesis pairs. Note that we keep the left empty ones if the right is not.
    // Time: O(n) for the preorder traversal
    // Space: O(h) where h is the height of the tree (not including the output string)
    StringBuilder res = new StringBuilder();
    
    public String tree2str(TreeNode t) {
        helper(t);
        return res.toString();
    }
    
    public void helper(TreeNode t) {
        if (t != null) {
            res.append(t.val);
            res.append("(");
            helper(t.left);
            res.append(")");
            
            res.append("(");
            helper(t.right);
            res.append(")");
            
            for (int i = 0; i < 2; i++) {
                if (res.charAt(res.length() - 1) == ')' && res.charAt(res.length() - 2) == '(')
                    for (int j = 0; j < 2; j++) res.deleteCharAt(res.length() - 1);
            }
        }
    }
}
