/* Noah Park

Given a non-negative integer represented as a linked list of digits, plus one to the integer.

The digits are stored such that the most significant digit is at the head of the list.

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
    
    // Intuition: 2 pass solution -> find the rightmost non nine value. If there isn't one, we add a new node to the front with a value of zero and set that to the right most non nine value. Then increment the rightmost non nine value node and update all nodes following it to zero since they must have a value of 9.
    // Space: O(1) constant.
    public ListNode plusOne(ListNode head) {
        ListNode rightmost = null;
        for (ListNode cur = head; cur != null; cur = cur.next) 
            if (cur.val != 9) rightmost = cur;
        
        if (rightmost == null) { head = new ListNode(0, head); rightmost = head; }
        
        rightmost.val++;
        for (rightmost = rightmost.next; rightmost != null; rightmost = rightmost.next) 
            rightmost.val = 0;
        
        return head;
    }
    
    // Intuition: 3 pass solution -> reverse, add normally, reverse back.
    // Time: O(n) 3 passes.
    // Space: O(1) constant.
    public ListNode plusOne2(ListNode head) {
        ListNode end = reverse(head), cur = end, prev = cur;
        
        boolean add = true;
        for (; cur != null; prev = cur, cur = cur.next) {
            if (cur.val < 9) {
                cur.val++;
                add = false;
                break;
            }
            
            cur.val = 0;
        }
        
        if (add) prev.next = new ListNode(1);
        
        return reverse(end);
    }
    
    public void print(ListNode n) {
        for (ListNode cur = n; cur != null; cur = cur.next)
            System.out.print(cur.val + "->");
    }
    
    public ListNode reverse(ListNode n) {
        ListNode prev = null, cur = n, next = null;
        for (; cur != null; prev = cur, cur = next) {
            next = cur.next;
            cur.next = prev;
        }
        return prev;
    }
}
