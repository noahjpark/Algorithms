/* Noah Park

Given the head of a linked list and two integers m and n. Traverse the linked list and remove some nodes in the following way:

Start with the head as the current node.
Keep the first m nodes starting with the current node.
Remove the next n nodes
Keep repeating steps 2 and 3 until you reach the end of the list.
Return the head of the modified list after removing the mentioned nodes.

Follow up question: How can you solve this problem by modifying the list in-place?

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
class Solution {
    
    // Intuition: Simple deletion algorithm. Every loop keeps m nodes then removes the next n nodes.
    // Time: O(n) for the length of the list.
    // Space: O(1) constant.
    public ListNode deleteNodes(ListNode head, int m, int n) {
        ListNode cur = head;
        
        while (cur != null) {
            int i = 1, j = 0;
            
            while (cur != null && i++ < m) 
                cur = cur.next;
            
            if (cur == null) break;
            
            ListNode prev = cur;
            while (cur != null && j++ < n) 
                cur = cur.next;
            
            prev.next = cur == null ? null : cur.next;
            if (cur != null) cur = cur.next;
        }
        
        return head;
    }
}
