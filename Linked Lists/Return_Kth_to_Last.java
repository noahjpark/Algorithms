/*

Noah Park

Problem:

Implement an algorithm to find the kth to last element of a singly linked list.

*/

public class Return_Kth_to_Last {

    // Time: O(n)
    // Space: O(1)
    // Note: if this was a doubly linked list, we could just go to the end and work backwards
    // until we find our target node.
    public static Node kth(Node head, int k){
        int count = 0; // count to keep track of the number of nodes in the linked list
        Node cur = head; // Set our jumping pointer to head
        while(cur != null){ // Iterate through the linked list
            cur = cur.getNextNode();
            count++; // Count the nodes
        }
        count -= k; // Subtract for the kth node
        cur = head; // Go back through starting from the head
        while(count > 0){ // Decrement count until we get to our target node
            cur = cur.getNextNode();
            count--;
        }
        return cur; // Return our target node
    }

    public static void main(String[] args){
        Node head = new Node(0);
        Node first = new Node(1);
        head.setNextNode(first);
        Node second = new Node(2);
        first.setNextNode(second);
        Node third = new Node(3);
        second.setNextNode(third);
        Node fourth = new Node(4);
        third.setNextNode(fourth);
        Node fifth = new Node(5);
        fourth.setNextNode(fifth);
        int k = 2;
        System.out.println(kth(head, k).getData()); // Should print out 4
    }
}
