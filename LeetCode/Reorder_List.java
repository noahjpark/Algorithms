/* Noah Park

You are given the head of a singly linked-list. The list can be represented as:

L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be on the following form:

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes. Only nodes themselves may be changed.

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
    
    // Intuition: First try was to map indices to nodes for easy access from the back. This required some extra space but maintained linear complexity. The other approach was to reverse the halfway point and merge the lists one by one.
    // Time: O(n) linear through the list.
    // Space: O(1) for the merging/ O(n) if using the map.
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        
        ListNode slow = head, fast = head, prev = head;
        while (fast.next != null && fast.next.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        if (fast.next != null) { prev = slow; slow = slow.next; }
        prev.next = null; // break link to middle
     
        ListNode cur = head, mid = reverse(slow);
        
        while (cur != null) {
            ListNode next = cur.next, temp = mid;
            mid = mid.next;
            
            if (next != null) temp.next = next;
            cur.next = temp;
            
            cur = next;
        }
    }
    
    public void print(ListNode head) {
        for (ListNode cur = head; cur != null; cur = cur.next)
            System.out.print(cur.val + " ");
    }
    
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
    
    Map<Integer, ListNode> map = new HashMap<>();
    
    public void reorderList2(ListNode head) {
        if (head == null) return;
        
        int n = mapAndCount(head), end = n - 1;
        ListNode cur = head, prev = cur;
        for (int i = 0; i < n; i++) {
            if (i >= end) { prev = cur; break; }
            
            ListNode insert = map.get(end--), next = cur.next;
            insert.next = next;
            cur.next = insert;
            
            prev = cur;
            cur = next;
        }
        
        prev.next = null;
    }
    
    public int mapAndCount(ListNode head) {
        int c = 0;
        
        for (ListNode cur = head; cur != null; cur = cur.next) {
            map.put(c, cur);
            c++;
        }
        
        return c;
    }
}
