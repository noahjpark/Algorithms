/* Noah Park

Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
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
    public TreeNode sortedListToBST(ListNode head) {
//         List<Integer> bst = new ArrayList<>();
//         while(head != null){
//             bst.add(head.val);
//             head = head.next;
//         }
        
//         return construct(bst, 0, bst.size() - 1);
        if(head == null) return null;
        return construct2(head, null);
    }
    
    // Supposedly O(n) and O(n) but actual runtime isn't as good as the other
//     public TreeNode construct(List<Integer> bst, int start, int end){
//         if(start > end) return null;
        
//         int mid = start + (end - start) / 2;
//         TreeNode node = new TreeNode(bst.get(mid));
//         node.left = construct(bst, start, mid - 1);
//         node.right = construct(bst, mid + 1, end);
        
//         return node;
//     }
    
    // O(n*log(n)) and O(1)
    public TreeNode construct2(ListNode start, ListNode end){
        // If nodes are equal return null, we already covered all nodes
        if(start == end) return null;
        ListNode fast = start, slow = start;
        
        // Get slow to the middle
        while(fast != end && fast.next != end){
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Make the new node and set its left and right using recursion
        TreeNode node = new TreeNode(slow.val);
        node.left = construct2(start, slow);
        node.right = construct2(slow.next, end);
        
        return node;
    }
}
