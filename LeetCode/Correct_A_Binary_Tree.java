/* Noah Park

You have a binary tree with a small defect. There is exactly one invalid node where its right child incorrectly points to another node at the same depth but to the invalid node's right.

Given the root of the binary tree with this defect, root, return the root of the binary tree after removing this invalid node and every node underneath it (minus the node it incorrectly points to).

Custom testing:

The test input is read as 3 lines:

TreeNode root
int fromNode (not available to correctBinaryTree)
int toNode (not available to correctBinaryTree)
After the binary tree rooted at root is parsed, the TreeNode with value of fromNode will have its right child pointer pointing to the TreeNode with a value of toNode. Then, root is passed to correctBinaryTree.

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
    
    // Intuition: dfs approach to the problem. Maintain a left and right child hashmap that maps the corresponding child to its parent. Dfs in preorder but go right then left since we want to find an occurrence of a node a second time. This means our current node would be the bad node. Once we reach this point, we are able to figure out which side our current node is to its parent (left/right) and update its corresponding pointer to null. Optimized to stop doing any searching by the boolean done.
    // Time: O(n) to search through the tree.
    // Space: O(n) to maintain mappings of all nodes to their parents.
    Map<TreeNode, TreeNode> right = new HashMap<>(), left = new HashMap<>();
    boolean done = false;
    
    public TreeNode correctBinaryTree(TreeNode root) {
        dfs(root);
        return root;
    }
    
    public void print(Map<TreeNode, TreeNode> map) {
        for (TreeNode n : map.keySet()) 
            System.out.println(n.val + " -> " + map.get(n).val);
    }
    
    public void dfs(TreeNode root) {
        if (root == null || done) return;
        
        if (root.right != null) {
            if (right.containsKey(root.right) || left.containsKey(root.right)) {
                if (left.containsKey(root)) left.get(root).left = null;
                else right.get(root).right = null;
                done = true;
                return;
            }
            right.put(root.right, root);
        }
        if (root.left != null) left.put(root.left, root);
        
        dfs(root.right);
        dfs(root.left);
    }
    
    // Intuition: Same as above but bfs order.
    // Time: O(n) to search through the tree.
    // Space: O(n) to maintain mappings of all nodes to their parents.
    public TreeNode correctBinaryTree2(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        
        while (!q.isEmpty()) {
            int size = q.size();
            boolean found = false;
            
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                
                if (cur.left != null) {
                    q.offer(cur.left);
                    left.put(cur.left, cur);
                }
                if (cur.right != null) {
                    if (right.containsKey(cur.right) || left.containsKey(cur.right)) {
                        found = true;
                        if (left.containsKey(cur)) left.get(cur).left = null;
                        if (right.containsKey(cur)) right.get(cur).right = null;
                        break;
                    }
                    q.offer(cur.right);
                    right.put(cur.right, cur);
                }
            }
            
            if (found) break;
        }
        
        return root;
    }
    
}
