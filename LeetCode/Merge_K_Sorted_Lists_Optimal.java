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
        if (lists.length == 0) return null; // edge case
        
        int interval = 1, n = lists.length; // interval represents interval size of merging lists
        
        while (interval < n) { // iterate until all lists are merged
            for (int i = 0; i + interval < n; i = i + interval * 2) lists[i] = merge(lists[i], lists[i + interval]); // merge into lists[i]
            interval *= 2; // update interval
        }
        
        return lists[0];
    }
    
    public ListNode merge(ListNode l1, ListNode l2) { // merge two lists together
        ListNode head = new ListNode(0), cur = head; // dummy head
        
        // take the smaller of the two and append to the list
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) { cur.next = l1; l1 = l1.next; cur = cur.next; }
            else { cur.next = l2; l2 = l2.next; cur = cur.next; }
        }
        
        // clean up 
        while (l1 != null) { cur.next = l1; l1 = l1.next; cur = cur.next; }
        while (l2 != null) { cur.next = l2; l2 = l2.next; cur = cur.next; }
        
        return head.next; // dummy head next is the start of the merged list
    }
    
    public ListNode mergeKListsPQ(ListNode[] lists) {
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
