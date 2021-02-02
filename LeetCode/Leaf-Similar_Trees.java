/* Noah Park

Consider all the leaves of a binary tree, from left to right order, the values of those leaves form a leaf value sequence.



For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).

Two binary trees are considered leaf-similar if their leaf value sequence is the same.

Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.

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
    // Time: O(n) to search through the list and postorder algorithm
    // Space: O(n) for the lists and recursion stack
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new ArrayList<>(), l2 = new ArrayList<>(); // lists to compare the leaves
        
        postorder(root1, l1);
        postorder(root2, l2);
        
        // number of leaves in each tree doesn't match
        if (l1.size() != l2.size()) return false;
        
        // compare the two trees
        for (int i = 0; i < l1.size(); i++) 
            if (l1.get(i) != l2.get(i)) return false;
        
        return true;
    }
    
    // modified postorder algorithm to only add leaf nodes
    public void postorder(TreeNode root, List<Integer> l) {
        if (root != null) {
            postorder(root.left, l);
            postorder(root.right, l);
            if (root.left == null && root.right == null) l.add(root.val);
        }
    }
}
