/* Noah Park

You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // reverse the two lists to use them to add the numbers with ease
        l1 = reverse(l1);
        l2 = reverse(l2);
        
        ListNode cur1 = l1, cur2 = l2, head = new ListNode(0), cur = head;
        int rem = 0;
        
        // add each point together while keeping track of the remainder
        while (cur1 != null && cur2 != null) {
            int sum = cur1.val + cur2.val + rem;
            ListNode node = new ListNode(sum % 10);
            cur.next = node;
            cur = cur.next;
            cur1 = cur1.next;
            cur2 = cur2.next;
            rem = sum / 10;
        }
        
        // clean up if cur1 has more numbers
        while (cur1 != null) {
            int sum = cur1.val + rem;
            ListNode node = new ListNode(sum % 10);
            cur.next = node;
            cur = cur.next;
            cur1 = cur1.next;
            rem = sum / 10;
        }
        
        // clean up if cur2 has more numbers
        while (cur2 != null) {
            int sum = cur2.val + rem;
            ListNode node = new ListNode(sum % 10);
            cur.next = node;
            cur = cur.next;
            cur2 = cur2.next;
            rem = sum / 10;
        }
        
        // if there is enough to make a new node, make the final node with the remainder
        if (rem != 0) {
            ListNode node = new ListNode(rem);
            cur.next = node;
        }
        
        // get rid of the dummy first node
        head = head.next;
        return reverse(head); // reverse the number so it is not pointing backwards
    }
    
    // print debug helper method
    public void print(ListNode head) {
        ListNode cur = head;
        
        while (cur != null) {
            System.out.print(cur.val + " -> ");
            cur = cur.next;
        }
        System.out.println("END");
    }
    
    // reverses a linked list in O(n) linear time
    public ListNode reverse(ListNode head) {
        ListNode cur = head, prev = null, next = null;
        
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        
        return prev;
    }
}
