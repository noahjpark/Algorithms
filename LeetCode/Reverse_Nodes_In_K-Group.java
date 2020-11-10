/* Noah Park

Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.

Follow up:

Could you solve the problem in O(1) extra memory space?
You may not alter the values in the list's nodes, only nodes itself may be changed.

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
    public ListNode reverseKGroup(ListNode head, int k) {
        // Error checking
        if(k <= 1 || head == null) return head;
        
        // cur will always be the front of the k sublist and prev will be the previous node right behind it
        ListNode cur = head, prev = null;
        
        // Loop until we reach the end of the list denoted by a break statement
        while(true){
            // lastBefore is the lastNode before reversing the list (The front of the previous sublist), lastReversed is the last node after reversing the sub-list
            ListNode lastBefore = prev, lastReversed = cur;
            
            int i = 0; // Iterate using cur and going while i < k
            for(; i < k && cur != null; i++)
                cur = cur.next;
            
            if(cur == null && i < k) break; // If cur == null and i < k then we had too small a sublist remaining, if cur is null and i == k, we need to reverse the remaining sublist
            
            // temp ensures that prev is not passed in as null
            ListNode temp = prev == null ? head : prev;
            prev = reverse(temp, cur);
            
            // If lastBefore is null, we are at the first k sublist, else we need to point it to the next k sublist.
            if(lastBefore != null) lastBefore.next = prev;
            else head = prev;
            
            lastReversed.next = cur; // Point the end of the reversed sublist to cur which is the start of the next sublist
            prev = lastReversed;     // Move prev to be one behind cur
        }
        
        return head;
    }
    
    public ListNode reverse(ListNode node, ListNode end){
        ListNode prev = null, cur = node, next = null;
        while(cur != end){
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
