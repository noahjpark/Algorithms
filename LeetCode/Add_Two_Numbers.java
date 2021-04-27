/* Noah Park

You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

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
    
    // Intuition: Adds two numbers to a completely new summed lists.
    // Time: O(n) where n is the length of the longer list.
    // Space: O(n) where n is the length of the longer list.
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode t1 = l1, t2 = l2, dummy = new ListNode(0), cur = dummy;
        int rem = 0;
        
        while (t1 != null || t2 != null) {
            int c1 = t1 == null ? 0 : t1.val, c2 = t2 == null ? 0 : t2.val, newVal = c1 + c2 + rem;
            rem = 0;
            
            if (newVal >= 10) { newVal -= 10; rem = 1; }
            
            cur.next = new ListNode(newVal);
            
            cur = cur.next;
            if (t1 != null) t1 = t1.next;
            if (t2 != null) t2 = t2.next;
        }
        
        if (rem != 0) cur.next = new ListNode(1);
        
        return dummy.next;
    }
    
    // Intuition: Adds two numbers in place to the larger of the two linked lists.
    // Time: O(n) where n is the length of the longer list.
    // Space: O(1) constant.
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode t1 = l1, t2 = l2, cleanup = l1;
        int rem = 0;
        
        while (t1 != null || t2 != null) {
            int c1 = t1 == null ? 0 : t1.val, c2 = t2 == null ? 0 : t2.val, newVal = c1 + c2 + rem;
            rem = 0;
            
            if (newVal >= 10) { newVal -= 10; rem = 1; }
            
            if (t1 != null) {
                t1.val = newVal;
                if (t1.next == null && t2 != null) { t1.next = t2.next; t2.next = null; }
            }
            
            cleanup = t1;
            if (t1 != null) t1 = t1.next;
            if (t2 != null) t2 = t2.next;
        }
        
        if (rem != 0) cleanup.next = new ListNode(1);
        
        return l1;
    }
}
