/* Noah Park

Given a binary tree where node values are digits from 1 to 9. A path in the binary tree is said to be pseudo-palindromic if at least one permutation of the node values in the path is a palindrome.

Return the number of pseudo-palindromic paths going from the root node to leaf nodes.

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
    
    int[] counts = new int[10];
    int res = 0;
    
    // Intuition: Maintain an array of the occurrences of each digit in the path. In this case, there are only 9 digits so we end up with constant time/space overhead additionally. Simple backtracking.
    // Time: O(n) to visit the tree.
    // Space:O(h) height of the tree for recursion stack.
    public int pseudoPalindromicPaths(TreeNode root) {
        dfs(root);
        return res;
    }
    
    public void dfs(TreeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            counts[root.val]++;
            int odd = 0;
            
            for (int i = 1; i < 10; i++)
                if (counts[i] % 2 == 1) odd++;
            
            if (odd < 2) res++;
            counts[root.val]--;
        }
        
        counts[root.val]++;
        
        dfs(root.left);
        dfs(root.right);
        
        counts[root.val]--;
    }
    
}
