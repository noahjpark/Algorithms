/* Noah Park

Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.

For example, the following two linked lists begin to intersect at node c1:


It is guaranteed that there are no cycles anywhere in the entire linked structure.

Note that the linked lists must retain their original structure after the function returns.

*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    
    // Intuition: Utilize a set to store all nodes. When we reach the node intersection contained by the set, we return it. Otherwise null.
    // Time: O(n) two pass solution to reach intersection.
    // Space: O(n) to maintain the length of the longer list.
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        Set<ListNode> set = new HashSet<>();
        
        while (a != null) {
            set.add(a);
            a = a.next;
        }
        
        while (b != null) {
            if (set.contains(b)) return b;
            b = b.next;
        }
        
        return null;
    }
    
    // Intuition: Utilize a two pointer solution where the two pointers will meet at the intersection. The two pointers will swap starting points when falling off the end of the array.
    // Time: O(n) two passes to reach the intersection.
    // Space: O(1) constant.
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        
        while (a != b) {
            a = a != null ? a.next : headB;
            b = b != null ? b.next : headA;
        }
        
        return a;
    }
}
