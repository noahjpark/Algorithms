/* Noah Park

Given a binary tree with the following rules:

root.val == 0
If treeNode.val == x and treeNode.left != null, then treeNode.left.val == 2 * x + 1
If treeNode.val == x and treeNode.right != null, then treeNode.right.val == 2 * x + 2
Now the binary tree is contaminated, which means all treeNode.val have been changed to -1.

You need to first recover the binary tree and then implement the FindElements class:

FindElements(TreeNode* root) Initializes the object with a contamined binary tree, you need to recover it first.
bool find(int target) Return if the target value exists in the recovered binary tree.

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
class FindElements {
    
    // Intuition: Recovering is straightforward - used a preorder algorithm to populate according to the rules. Initially had find as searching through the entire tree structure. Improved on the solution by storing the integers in a set to immediately grab them for find.
    // Time: O(n) to recover the tree.
    // Space: O(n) for the set of all integers in the tree.
    TreeNode root = null;
    Set<Integer> set = new HashSet<>();

    public FindElements(TreeNode r) {
        recover(r, 0);
        root = r;
    }
    
    public void recover(TreeNode r, int val) {
        if (r != null) {
            r.val = val;
            set.add(r.val);
            recover(r.left, r.val*2 + 1);
            recover(r.right, r.val*2 + 2);
        }
    }
    
    public boolean find(int target) {
        return set.contains(target);
    }
}

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */
