/* Noah Park

Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.

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
    
    // Intuition: Basic removal of a linked list.
    // Time: O(n) to iterate over the list.
    // Space: O(1) constant.
    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) head = head.next;
        
        ListNode cur = head, prev = head;
        
        while (cur != null) {
            prev = cur;
            cur = cur.next;
            while (cur != null && cur.val == val) cur = cur.next;
            
            prev.next = cur;
        }
        
        return head;
    }
}
