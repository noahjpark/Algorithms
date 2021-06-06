/* Noah Park

You are given two linked lists: list1 and list2 of sizes n and m respectively.

Remove list1's nodes from the ath node to the bth node, and put list2 in their place.

The blue edges and nodes in the following figure incidate the result:

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
    
    // Intuition: Find the node before the first node to remove in list1 and the node after the last node to be removed in list1. We also need the start/last node from list2. We are given the start so we just need to traverse to the end to find the end.
    // Time: O(n + m) to iterate over list1 and list2.
    // Space: O(1) in place.
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode start = list1;
        
        for (int i = 0; i < a - 1; i++) 
            start = start.next;
        
        ListNode end = start.next;
        for (int i = 0; i < b - a + 1; i++) 
            end = end.next;
        
        ListNode otherEnd = list2;
        while (otherEnd.next != null) 
            otherEnd = otherEnd.next;
        
        start.next = list2;
        otherEnd.next = end;
        
        return list1;
    }
}
