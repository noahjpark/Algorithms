/* Noah Park

Apply Insertion Sort on a Linked List.

The O(n^3) solution in place moving nodes one by one fails the time constraints.

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
    public ListNode insertionSortList(ListNode head) {
        // dummy gives us a starting point for prev and next
        // prev and next are the two nodes in which cur will be inserted between
        ListNode dummy = new ListNode();
        ListNode cur = head, prev, next;
        
        // loop through the list
        while(cur != null){
            // always start prev and next at first/second spots in the list
            prev = dummy;
            next = dummy.next;
            
            // Move until we find the position in which cur will be put into
            while(next != null){
                if(cur.val < next.val) break;
                prev = next;
                next = next.next;
            }
            
            // Insert cur but store cur's next before inserting so we don't lose our place
            ListNode temp = cur.next;
            cur.next = next;
            prev.next = cur;
            cur = temp;
        }
        
        // dummy's next is the start of the list
        return dummy.next;
    }
}
