/* Noah Park

You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

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
    
    // Intuition: Maintain a global index for movement through the string. Convert each number to its numerical value without extra space. If we reach an open parenthesis, set the left child. If after coming back from the recursion, we find another open space, set the right child.
    // Time: O(n) to iterate over the string.
    // Space: O(h) height of the tree.
    int i = 0, n;
    
    public TreeNode str2tree(String s) {
        n = s.length();
        return helper(s);
    }
    
    public TreeNode helper(String s) {
        if (i >= n) return null;
        if (s.charAt(i) == ')') { i++; return null; }
        
        int val = 0, sign = 1;
        if (s.charAt(i) == '-') { sign = -1; i++; }
        val = s.charAt(i++) - '0';
        while (i < n && s.charAt(i) != '(' && s.charAt(i) != ')') 
            val = (val * 10) + (s.charAt(i++) - '0');
        TreeNode root = new TreeNode(val * sign);
        
        if (i < n && s.charAt(i) == '(') { i++; root.left = helper(s); }
        if (i < n && s.charAt(i) == '(') { i++; root.right = helper(s); }
        
        i++;
            
        return root;
    }
}
