/* Noah Park

Given the root of a binary tree, flatten the tree into a "linked list":

The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.

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
    
    // Iterative solution to the problem
    // Time: O(n) 2 pass solution at worst
    // Space: O(1)
    public void flatten(TreeNode root) {
        if (root != null) {
            for (TreeNode cur = root; cur != null; cur = cur.right) {
                if (cur.left != null) {
                    TreeNode r = cur.left;
                    while (r.right != null) r = r.right;

                    r.right = cur.right;
                    cur.right = cur.left;
                    cur.left = null;
                }
            }
        }
    }
    
    // Intuition: Use a post order traversal to do everything backwards.
    // Time: O(n)
    // Space: O(h) for the height of the tree
    TreeNode previous = null;
    
    public void flattenRecursive(TreeNode root) {
        if (root != null) {
            flatten(root.right);
            flatten(root.left);
            root.right = previous;
            root.left = null;
            previous = root;
        }
    }
    
    // Intuition: map nodes to their indices in order then adjust pointers after.
    // Time: O(n) 2 pass solution
    // Space: O(n) storing all the nodes in the map
    Map<Integer, TreeNode> map = new TreeMap<>();
    int i = 0;
    
    public void flattenFirst(TreeNode root) {
        if (root == null) return;
        
        mapTree(root);
        
        for (int j = 0; j < map.size() - 1; j++) {
            TreeNode cur = map.get(j);
            cur.left = null;
            cur.right = map.get(j + 1);
        }
        
        TreeNode last = map.get(map.size() - 1);
        last.left = last.right = null;
    }
    
    public void mapTree(TreeNode root) {
        if (root != null) {
            map.put(i++, root);
            mapTree(root.left);
            mapTree(root.right);
        }
    }
}
