// Noah Park
/*

Problem: Write a DoublyLinkedList class that has a head and a tail, both of which point to
either a linked list Node or None / null. The class should support:

    - Setting the head and tail of the linked list.
    - Inserting nodes before and after other nodes as well as at given positions (the position of
    the head node is 1).
    - Removing given nodes and removing nodes with given values.
    - Searching for nodes with given values

Note that the setHead, setTail, insertBefore, insertAfter, insertAtPosition, and remove methods all
take in actual Nodes as input parameters--not integers (except for insertAtPosition, which also takes
in an integer representing the position); this means that you don't need to create any new Nodes in these
methods. The input nodes can be either stand-alone nodes or nodes that are already in the linked list. If
they're nodes that are already in the linked list, the methods will effectively be moving the nodes within
the linked list. You won't be told if the input nodes are already in the linked list so your code will have to
defensively handle this scenario.

Each Node has an integer value as well as a prev node and a next node, both of which can point to either another
node or None / null.

*/

public class Linked_List_Construction {
    static class DoublyLinkedList {
        public Node head;
        public Node tail;

        // O(1) time and space
        public void setHead(Node node) {
            // If the node is already the tail, we will move it to the front
            // Update the tail to be the new tail which is the last value excluding the node
            if(tail == node) tail = node.prev;

            unlink(node); // unlink the node from the list if it is in it

            // If the head is null, update the pointers
            if(head != null){
                head.prev = node;
                node.next = head;
            }

            // Set the head
            head = node;

            // If the tail is null, update the tail to match the head
            if(tail == null){
                tail = head;
            }
        }

        // O(1) time and space
        public void setTail(Node node) {
            // If the node is already the head, we will move it to the end
            // Update the head to be the new head which is the first value excluding the node
            if(head == node) head = node.next;


            unlink(node); // unlink the node from the list if it is present

            // If the tail is null, update the pointers
            if(tail != null){
                tail.next = node;
                node.prev = tail;
            }

            // Set the tail
            tail = node;

            // If the head is null, update the head to match the tail
            if(head == null){
                head = tail;
            }
        }

        // O(1) time and space
        // Passes node reference
        public void insertBefore(Node node, Node nodeToInsert) {
            // If the node to be inserted is currently the tail, update the tail to be the previous node of the one we are moving
            if(nodeToInsert == tail) tail = nodeToInsert.prev;
            remove(nodeToInsert); // Remove the moving node from the list if it is present

            // Update the 4 pointers
            nodeToInsert.prev = node.prev;
            if(node.prev != null){
                node.prev.next = nodeToInsert;
            }
            node.prev = nodeToInsert;
            nodeToInsert.next = node;

            // If the node we inserted before was the head, update the head to be the newly inserted node
            if(node == head){
                head = nodeToInsert;
            }
        }

        // O(1) time and space
        // Passes node reference
        public void insertAfter(Node node, Node nodeToInsert) {
            // If the node to be inserted is currently the head, update the head to be the next node after the one we are moving
            if(nodeToInsert == head) head = nodeToInsert.next;
            remove(nodeToInsert); // Remove the moving node from the list if it is present

            // Update the 4 pointers
            nodeToInsert.next = node.next;
            if(nodeToInsert.next != null){
                nodeToInsert.next.prev = nodeToInsert;
            }
            node.next = nodeToInsert;
            nodeToInsert.prev = node;

            // If the node we inserted after was the tail, update the tail to be the newly inserted node
            if(node == tail){
                tail = nodeToInsert;
            }
        }

        // O(n) time | O(1) space
        // Passes node reference
        // node should be inserted at the given position (1 is the head)
        // All nodes in that position and further on should be shifted down to accommodate
        public void insertAtPosition(int position, Node nodeToInsert) {
            // If the position is 1 (head) we want to just call set head with the inserting node
            if(position == 1){
                setHead(nodeToInsert);
            }
            // Otherwise, we will search for our position
            else{
                int i = 1;
                Node cur = head;
                // Get to our position or fall off the end of the list
                while(i < position && cur != null){
                    cur = cur.next;
                    i++;
                }
                // If we fall off the end of the list, insert the node at the tail
                // Otherwise, insert before the 'cur' pointer
                if(cur == null) setTail(nodeToInsert);
                else insertBefore(cur, nodeToInsert);
            }
        }

        // O(n) time | O(1) space
        public void removeNodesWithValue(int value) {
            // Simply iterate until we find each node with a particular value 'value'
            // Call remove on each of these nodes
            // I'm using a temp pointer to avoid losing cur before moving to the next node
            Node cur = head;
            while(cur != null){
                Node toRemove = cur;
                cur = cur.next;
                if(toRemove.value == value){
                    remove(toRemove);
                }
            }
        }

        // O(1) space and time
        // Passes node reference
        public void remove(Node node) {
            // Update head and tail pointers if the node is either of them
            if(node == head) head = head.next;
            if(node == tail) tail = tail.prev;
            unlink(node); // Helper function
        }

        // O(n) time | O(1) space
        public boolean containsNodeWithValue(int value) {
            // Returns true if we find a node with the value 'value' and false otherwise
            Node cur = head;
            while(cur != null){
                if(cur.value == value){
                    return true;
                }
                cur = cur.next;
            }
            return false;
        }

        // Removes traces of the node from a list
        public void unlink(Node node){
            // Update traces of the node if it is in the list
            // Update its neighbors pointers to point around it (at each other)
            if(node.prev != null) node.prev.next = node.next;
            if(node.next != null) node.next.prev = node.prev;
            // Update its pointers
            node.prev = null;
            node.next = null;
        }

    }

    // Do not edit the class below.
    static class Node {
        public int value;
        public Node prev;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }
}
