/* Noah Park

You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

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
    public ListNode mergeKLists(ListNode[] lists) {
        // Error checking
        if(lists == null || lists.length == 0) return null;
        
        // Min Heap will store the next smallest node
        // O(lists.length) space
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(lists.length, (a, b) -> Integer.compare(a.val, b.val));
        
        // Initialize the heap with the head from each list in lists
        for(ListNode node : lists)
            if(node != null) minHeap.offer(node);
        
        // head will be our new list head and cur will be our traversal node
        ListNode head = null, cur = null;
        
        // Iterate until we have checked all nodes
        while(!minHeap.isEmpty()){
            // Remove the smallest node and offer its next pointer if its next is not null
            ListNode node = minHeap.poll();
            if(node.next != null) minHeap.offer(node.next);
            
            // If we haven't set our head yet, update head and cur to node and continue to the next iteration.
            // Else, update cur's next and move cur to its next.
            if(head == null) { head = cur = node; continue; }
            else cur.next = node;
            cur = cur.next;
        }
        
        return head;
    }
}
