/* Noah Park

Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.

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
    
    // Intuition: Maintain outer pointers to the reversed portion. Reverse the inner portion and update the pointers. Only edge case is if the left includes the head since it must be updated. Otherwise, right including n is included in the reversal.
    // Time: O(n) to iterate over the list.
    // Space: O(1) constant.
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) return head;
        
        ListNode start = head, startRev = head, endRev = head, after = head;
        int i = 1;
        
        for (; i + 1 < left; i++) start = start.next;
        startRev = start.next;
        endRev = start;
        for(; i < right; i++) endRev = endRev.next;
        after = endRev.next;
        
        if (left == 1) {
            head = reverse(start, endRev);
            start.next = after;
        } else {
            start.next = reverse(startRev, endRev);
            startRev.next = after;
        }
            
        return head;
    }
    
    public ListNode reverse(ListNode start, ListNode end) {
        ListNode prev = null, cur = start, next = null;
        while (prev != end) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
