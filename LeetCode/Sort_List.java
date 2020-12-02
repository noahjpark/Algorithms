/* Noah Park

Given the head of a linked list, return the list after sorting it in ascending order.

Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?

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
    public ListNode sortList(ListNode head) { // merge sort on linked list
        if (head == null || head.next == null) return head; // nothing to sort 
        
        ListNode cut = null, slow = head, fast = head; // get slow to the center of the list, cut will cut the list in half
        while (fast != null && fast.next != null) { cut = slow; slow = slow.next; fast = fast.next.next; } 
        
        cut.next = null; // cut the list
        
        ListNode t1 = sortList(head), t2 = sortList(slow); // break the list down recursively merge sort style
        
        return merge(t1, t2); // merge the broken pieces back together in sorted order
    }
    
    public ListNode merge(ListNode t1, ListNode t2) {
        ListNode dummy = new ListNode(0); // dummy is a placeholder for the front of the list
        ListNode cur = dummy;
        
        while (t1 != null && t2 != null) { // iterate until one of the lists has no more elements
            // set cur's next to the smaller element, then move the pointers forward accordingly
            if (t1.val <= t2.val) { cur.next = t1; t1 = t1.next; } 
            else { cur.next = t2; t2 = t2.next; }
            cur = cur.next;
        }
        
        // clean up the remaining lists
        while (t1 != null) { cur.next = t1; t1 = t1.next; cur = cur.next; }
        while (t2 != null) { cur.next = t2; t2 = t2.next; cur = cur.next; }
        
        return dummy.next; // the front is dummy's next as we have a headed list
    }
    
    public ListNode sortBruteForce(ListNode head) {
        List<Integer> temp = new ArrayList<>(); // store node values to sort
        ListNode cur = head;
        while (cur != null) { // put all node values in temp
            temp.add(cur.val);
            cur = cur.next;
        }
        
        Collections.sort(temp); // sort using quicksort (n*log(n))
        
        cur = head; // put values back into linked list
        int i = 0;
        while (cur != null) {
            cur.val = temp.get(i++);
            cur = cur.next;
        }
        
        return head;
    }
}
