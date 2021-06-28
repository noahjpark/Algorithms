/* Noah Park

In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.

Two nodes of a binary tree are cousins if they have the same depth, but have different parents.

We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.

Return true if and only if the nodes corresponding to the values x and y are cousins.

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
    // Time: O(n)
    // Space: O(n)
    public boolean isCousins(TreeNode root, int x, int y) {
        TreeNode xp = null, yp = null; // parent nodes
        int xlevel = -1, ylevel = -1, level = 0; // level of each node and current level
        
        // queue for bfs
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        
        // level order traversal
        while (!q.isEmpty()) {
            if (xlevel != -1 && ylevel != -1) break; // break once the levels/parents have been set
            
            int size = q.size();
            
            // iterate over level
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                
                // add left node, update pointers if it is a target
                if (cur.left != null) {
                    q.offer(cur.left);
                    
                    if (cur.left.val == x) {
                        xlevel = level + 1;
                        xp = cur;
                    } else if (cur.left.val == y) {
                        ylevel = level + 1;
                        yp = cur;
                    }
                }
                
                // add right node, update pointers if it is a target
                if (cur.right != null) {
                    q.offer(cur.right);
                    
                    if (cur.right.val == x) {
                        xlevel = level + 1;
                        xp = cur;
                    } else if (cur.right.val == y) {
                        ylevel = level + 1;
                        yp = cur;
                    }
                }
            }
            
            level++; // update level
        }
        
        // by definition, cousins.
        return xp != yp && xlevel == ylevel;
    }
}
