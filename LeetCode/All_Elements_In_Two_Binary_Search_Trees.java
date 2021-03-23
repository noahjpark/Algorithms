/* Noah Park

Given two binary search trees root1 and root2.

Return a list containing all the integers from both trees sorted in ascending order.

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
    // Intuition, populate two sorted lists using inorder algorithm then use merge sort application to form a sorted singular list.
    // Time: O(n + m) where n is the size of the first tree and m is the size of the second tree
    // Space: O(h1 + h2) where h1 is the depth of the first tree and h2 is the depth of the second tree
    // Two pass algorithm, one for populating the lists, then one for merging
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {        
        List<Integer> treeOne = new ArrayList<>(), treeTwo = new ArrayList<>(), res = new ArrayList<>();
        
        inorder(root1, treeOne);
        inorder(root2, treeTwo);
        
        int p1 = 0, p2 = 0, s1 = treeOne.size(), s2 = treeTwo.size();
        
        while (p1 < s1 && p2 < s2) {
            if (treeOne.get(p1) < treeTwo.get(p2)) res.add(treeOne.get(p1++));
            else res.add(treeTwo.get(p2++));
        }
        
        while (p1 < s1) res.add(treeOne.get(p1++));
        while (p2 < s2) res.add(treeTwo.get(p2++));
        
        return res;
    }
    
    public void inorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            inorder(root.left, list);
            list.add(root.val);
            inorder(root.right, list);
        }
    }
}
