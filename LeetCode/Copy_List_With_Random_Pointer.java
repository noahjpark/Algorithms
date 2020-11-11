/* Noah Park

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

The Linked List is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) where random pointer points to, or null if it does not point to any node.

*/

/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        // Insert the copies into the current list
        Node cur = head, next = null;
        while(cur != null){
            next = cur.next;
            Node copy = new Node(cur.val);
            cur.next = copy;
            copy.next = next;
            cur = next;
        }
        
        // Insert the random pointers into the copied nodes from the reference ones
        cur = head;
        while(cur != null){
            if(cur.random != null) cur.next.random = cur.random.next; // point to another copy. Without the .next in the last part, we would point to the reference node
            cur = cur.next.next;
        }
        
        cur = head;
        Node dummy = new Node(0); // dummy node for our new list
        Node copy, trail = dummy;
        
        // Extract the copied nodes into their own list while preserving and resolving them from the original list
        while(cur != null){
            next = cur.next.next; // next reference node
            
            // Get the copy and insert it into the copied list
            copy = cur.next;
            trail.next = copy;
            trail = copy;
            
            cur.next = next; // Remove the copied node from the original list
            cur = next;
        }
        
        return dummy.next;
    }
}
