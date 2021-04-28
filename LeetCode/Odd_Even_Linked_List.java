/* Noah Park

Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with even indices, and return the reordered list.

The first node is considered odd, and the second node is even, and so on.

Note that the relative order inside both the even and odd groups should remain as it was in the input.

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
    
    // Intuition: Utilize two adjacent pointers to point to all even indices/all odd indices. Maintain a trailer to the odd so it ends up at the end and a start to the even so they can be chained together at the end. If first ends early because the number of nodes is odd, trailer is obsolete.
    // Time: O(n) to iterate over the list.
    // Space: O(1) constant.
    public ListNode oddEvenList(ListNode head) {
        if(head == null) return head;
        
        ListNode first = head, second = head.next, evenStart = second, trail = first;
        
        while (first != null && second != null) {
            //System.out.println(first.val + " " + second.val);
            first.next = second.next;
            second.next = first.next == null ? null : first.next.next;
            
            trail = first;
            first = first.next;
            second = second.next;
        }
        
        if (first == null) trail.next = evenStart;
        else first.next = evenStart;
        
        return head;
    }
}
