/* Noah Park

Given an array of unique integers preorder, return true if it is the correct preorder traversal sequence of a binary search tree.

*/

class Solution {
    
    int i = 0;
    
    // Intuition: Maintain the minimum and maximum allowed values as we "traverse" the preorder. When we reach points that the current value is invalid, we stop traversing that branch and come back to go to try it at other branches. We know we have a valid preorder sequence if the index is at the end of the preorder meaning we have used each preorder value in a valid position.
    public boolean verifyPreorder(int[] preorder) {
        preorder(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return i == preorder.length;
    }
    
    public void preorder(int[] pre, int min, int max) {
        if (i >= pre.length || pre[i] < min || pre[i] > max) return;
        
        int val = pre[i++];
        preorder(pre, min, val);
        preorder(pre, val, max);
    }
    
}
