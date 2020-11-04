/* Noah Park

Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. 
Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.

Notice that you should not modify the linked list.

Commented out portion was first solution, much more inefficient.

*/

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) break;
        }
        
        if(fast == null || fast.next == null) return null; // no cycle
        
//         int count = 1;
//         slow = slow.next;
//         fast = fast.next.next;
//         while(slow != fast){
//             slow = slow.next;
//             fast = fast.next.next;
//             count++;
//         }
        
//         ListNode cur = head;
//         while(cur != null){
//             if(isInCycle(cur, slow, count)) break;
//             cur = cur.next;
//         }
        
//         return cur;
        slow = head;
        while(slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
    
    // public boolean isInCycle(ListNode cur, ListNode slow, int count){
    //     for(int i = 0; i < count; i++){
    //         if(cur == slow) return true;
    //         slow = slow.next;
    //     }
    //     return false;
    // }
}
