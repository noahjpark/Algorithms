/* Noah Park

Given a linked list, rotate the list to the right by k places, where k is non-negative.

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
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || k == 0) return head;
        
        int size = 1;
        ListNode cur = head;
        while(cur.next != null){
            cur = cur.next;
            size++;
        }
        
        cur.next = head; // create a cycle
        int rotations = size - k % size;
        
        for(int i = 0; i < rotations; i++){
            cur = cur.next;
        }
        
        head = cur.next;
        cur.next = null;
        
        return head;
    }
}
