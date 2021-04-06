/* Noah Park

Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

Return the smallest level x such that the sum of all the values of nodes at level x is maximal.

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
    
    // Intuition: BFS traversal using a queue.
    // Time: O(n) to look over the sums of all nodes in the tree.
    // Space: O(n) to store the nodes from the tree in the queue.
    public int maxLevelSumIterative(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int maxSum = root.val, maxLevel = 1, level = 1;
        
        while (!q.isEmpty()) {
            int size = q.size(), sum = 0;
            
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                sum += cur.val;
                
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            
            if (sum > maxSum) { maxSum = sum; maxLevel = level; } // larger level sum
            level++;
        }
        
        return maxLevel;
    }
    
    // Intuition: We konw there are at most 10^4 levels in the tree. We can use counting sort to store this information to improve the space complexity. This will likely increase runtime as well not having to use the linked list queue implementation.
    // Time: O(n) to add all sums of all nodes in the tree
    // Space: O(1) since we know the size of our sum array
    int largestLevel = 0;
    int[] levelSum = new int[10000];

    public void inorder(TreeNode node, int level) {
        if (node != null) {
            largestLevel = Math.max(largestLevel, level);
            inorder(node.left, level + 1);
            levelSum[level] += node.val;
            inorder(node.right, level + 1);
        }
    }
  
    public int maxLevelSum(TreeNode root) {
        inorder(root, 1);
        int lev = 1;
        for (int i = 1; i <= largestLevel; i++) if (levelSum[i] > levelSum[lev]) lev = i;
        return lev;
    }
}  
