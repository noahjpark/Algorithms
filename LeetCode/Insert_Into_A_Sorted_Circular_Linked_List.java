/* Noah Park

Given a node from a Circular Linked List which is sorted in ascending order, write a function to insert a value insertVal into the list such that it remains a sorted circular list. The given node can be a reference to any single node in the list, and may not be necessarily the smallest value in the circular list.

If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the circular list should remain sorted.

If the list is empty (i.e., given node is null), you should create a new single circular list and return the reference to that single node. Otherwise, you should return the original given node.

*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

class Solution {
    
    // Intuition: Utilize the tortiose and hare algorithm to find the size of the list cycle as well as find the minimum valued node and maximum valued node. If they are the same, we can insert anywhere. If our insert value is smaller than or equal to the min or larger than or equal to the max, we insert between the max and min. Otherwise, we go from the min until we find the appropriate place to insert our new node.
    // Time: O(n) two passes (one for the t&h algorithm and one if we need to find the place to insert our insertValue).
    // Space: O(1) constant.
    Node min = null, max = null;
    
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node res = new Node(insertVal);
            res.next = res;
            return res;
        }
        
        int size = getSize(head);
        if (min.val == max.val) head.next = new Node(insertVal, head.next);
        else if (insertVal <= min.val || insertVal >= max.val) max.next = new Node(insertVal, min);
        else {
            Node prev = min, cur = min;
            while (cur.val <= insertVal) {
                prev = cur;
                cur = cur.next;
            }

            prev.next = new Node(insertVal, cur);
        }
        return head;
    }
    
    public int getSize(Node head) {
        int size = 0;
        
        Node slow = head, fast = head;
        
        while (fast != slow || size == 0) {
            if (min == null || slow.val < min.val) min = slow;
            if (max == null || slow.val >= max.val) max = slow;
            
            slow = slow.next;
            fast = fast.next.next;
            size++;
        }
        
        if (min == null) min = max = slow;
        
        return size;
    }
}
