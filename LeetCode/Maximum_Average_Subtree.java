/* Noah Park

Given the root of a binary tree, find the maximum average value of any subtree of that tree.

(A subtree of a tree is any node of that tree plus all its descendants. The average value of a tree is the sum of its values, divided by the number of nodes.)

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
    
    // Intuition: Essentially we need to maintain the total nodes from each subtree as well as the current total. Note that the average of averages is NOT the average. I started by mapping each node to its total sum including the value itself and its subtrees, and the function returned the number of nodes in each subtree as well as itself. This was semi-slow so I figured we could optimize and return a tuple of both the total nodes in a given subtree as well as the total sum of that subtree. This way we can easily calculate the average at any given node.
    // Time: O(n) to look at the tree.
    // Space: O(n) for the map, optimized down to just the recursion O(h) for the height of the tree.
    //Map<TreeNode, Double> map = new HashMap<>();
    double res = 0;
    
    public double maximumAverageSubtree(TreeNode root) {
        max(root);
        return res;
    }
    
    public double[] max(TreeNode root) {
        if (root == null) return new double[]{ 0, 0 };
        
        double[] left = max(root.left), right = max(root.right);
        double totalnodes = left[0] + right[0] + 1;
        double curtotal = left[1] + right[1] + root.val, avg = curtotal / totalnodes;
        //double curtotal = map.getOrDefault(root.left, 0.0) + map.getOrDefault(root.right, 0.0) + root.val, avg = curtotal / totalnodes;
    
        //map.put(root, curtotal);
        res = Math.max(res, avg);
        
        return new double[]{ totalnodes, curtotal };
    }
}
