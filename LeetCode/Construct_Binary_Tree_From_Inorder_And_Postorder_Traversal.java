/* Noah Park

Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.

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
    
    // Intuition: Postorder is left right root so since we build it backwards we go root right left. Our stopping point is where the indices in inorder are no longer valid. We can decrement a global index for our postorder algorithm since we just move to the left once each call. Once the right's have evaluated, we can do the lefts. A map is an optimization for time rather than constantly looping in the recursion to get our index in the inorder array.
    // Time: O(n) to create the tree.
    // Space: O(n) for the map or h for the depth of the tree with no map.
    int i;
    Map<Integer, Integer> map = new HashMap<>();
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        i = postorder.length - 1;
        for (int j = 0; j < inorder.length; j++) map.put(inorder[j], j);
        return build(inorder, postorder, 0, inorder.length - 1);
    }
    
    public TreeNode build(int[] inorder, int[] postorder, int istart, int iend) {
        if (istart > iend) return null;
        
        TreeNode cur = new TreeNode(postorder[i--]);
        int idx = map.get(cur.val);
        // for (idx = istart; idx <= iend; idx++)
        //     if (inorder[idx] == cur.val) break;
        
        cur.right = build(inorder, postorder, idx + 1, iend);
        cur.left = build(inorder, postorder, istart, idx - 1);
        
        return cur;
    }
}
