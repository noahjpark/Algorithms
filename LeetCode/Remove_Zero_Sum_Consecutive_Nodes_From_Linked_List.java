/* Noah Park

Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until there are no such sequences.

After doing so, return the head of the final linked list.  You may return any such answer.

(Note that in the examples below, all sequences are serializations of ListNode objects.)

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
    public ListNode removeZeroSumSublists(ListNode head) {
        Map<Integer, ListNode> map = new HashMap<>(); // map stores sum with farthest node where the sum is, this tells us where consecutive nodes sum to 0
        ListNode cur = head; // list traversal node
        int sum = 0; // prefix sum
        while (cur != null) { // loop through list
            sum += cur.val; // keep track of sum
            if(sum == 0) { head = cur.next; } // edge case, if sum is 0, we need to remove all nodes in front of this sum
            map.put(sum, cur); // update the map
            cur = cur.next;
        }
        
        cur = head; // second pass to update nodes
        sum = 0;
        while (cur != null) { 
            sum += cur.val;
            if (map.containsKey(sum)) cur.next = map.get(sum).next; // if we contain the sum, remove all nodes in front of cur including the node that has sum mapped
            cur = cur.next;
        }
        
        return head;
    }
}
