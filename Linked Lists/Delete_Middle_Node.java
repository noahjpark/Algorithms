/*

Noah Park

Problem:

Implement an algorithm to delete a node in the middle (i.e., any
node but the first and last node, not necessarily the exact middle)
of a singly linked list, given only access to that node.

*/

public class Delete_Middle_Node {

    // Time: O(1)
    // Space: O(1)
    // We can be sneaky here; rather than actually removing the
    // node traditionally, we can essentially slide the data from
    // the next node down until we have moved all nodes data down
    // then we can remove the final node from the list easily by
    // pointing its trailer node to null rather than the final node
    public static void deleteMiddleNode(Node n){
        Node last = null; // This is to point the second to last node to null
        Node trailer = n; // Trailer node to move data down
        Node cur = n.getNextNode(); // Current node to take the data from the next node
        while(cur != null){ // Iterate through the linked list to shift all data down one
            trailer.setData(cur.getData()); // Shift the data down by one node
            last = trailer; // Move last up one node
            trailer = cur; // Move trailer up one node
            cur = cur.getNextNode(); // Move current up one node
        }
        last.setNextNode(null); // Drop the last node (trailer) off the list
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
        // Here list is: 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> null
        deleteMiddleNode(second); // Remove 2 from the linked list
        Node cur = head;
        while(cur != null){
            System.out.print(cur.getData() + " -> "); // Should print: 0 -> 1 -> 3 -> 4 -> 5 -> null
            cur = cur.getNextNode();
        }
        System.out.println("null");
        // Note we are reusing second, because the data was updated
        // and now second represents the node with data 3
        // Remember, we did not actually remove the center node we
        // simply changed the data
        deleteMiddleNode(second); // Remove 3 from the linked list
        cur = head;
        while(cur != null){
            System.out.print(cur.getData() + " -> "); // Should print: 0 -> 1 -> 4 -> 5 -> null
            cur = cur.getNextNode();
        }
        System.out.println("null");
    }
}
