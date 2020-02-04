/*

Noah Park

Problem:

Write code to partition a linked list around a value x, such that
all nodes less than x come before all nodes greater than or equal
to x. If x is contained within the list, the values of x only need
to be after the elements less than x. The partition element x can
appear anywhere in the "right partition"; it does not need to appear
between the left and right partitions.

*/

public class Partition {

    // Time: O(n)
    // Space: O(1)
    // Essentially, I go through the original linked list and check each node's data against the passed in partition. Then I create a left and right partition for data that
    // is less than and greater than or equal to respectively. Finally, I stitch these two new linked lists together and return the head of the start of this combined list.
    public static Node partitionList(Node head, int p){
        Node secondHead = null; // Second partition list (>=)
        Node secondCur = null;  // Pointer through second partition
        Node firstHead = null;  // First partition list (<)
        Node firstCur = null;   // Pointer for first partition
        Node cur = head;        // Our pointer node to go through head (passed in list)
        while(cur != null){     // Iterate through the given list
            if(cur.getData() < p){        // If current data is less than partition
                if(firstHead == null){    // Initialize first partition
                    firstHead = cur;
                    firstCur = firstHead; // Initialize first partition pointer
                } else{
                    firstCur.setNextNode(cur);         // If first partition is already initialized, use pointer to set next nodes and move it along its own list
                    firstCur = firstCur.getNextNode();
                }
            } else{                             // If current data is less than partition
                if(secondHead == null){         // Initialize second partition
                    secondHead = cur;
                    secondCur = secondHead;     // Initialize second partition pointer
                } else{
                    secondCur.setNextNode(cur); // If second partition is already initialized, use pointer to set next nodes and move it along its own list
                    secondCur = secondCur.getNextNode();
                }
            }
            cur = cur.getNextNode(); // Move cur through the original list
        }
        // Ensure that we do not get a null pointer exception
        if(firstCur != null && secondCur != null) {
            firstCur.setNextNode(null); // Set both pointers next to null so the list does not continue (issue with how pointers work in java)
            secondCur.setNextNode(null);
            firstCur.setNextNode(secondHead); // Connect the two lists using the end of the first partition and the head of the second partition
        }

        return firstHead; // Return pointer to head of the newly combined left and right partition
    }

    public static void main(String[] args){
        Node head = new Node(3);
        Node first = new Node(5);
        head.setNextNode(first);
        Node second = new Node(8);
        first.setNextNode(second);
        Node third = new Node(5);
        second.setNextNode(third);
        Node fourth = new Node(10);
        third.setNextNode(fourth);
        Node fifth = new Node(2);
        fourth.setNextNode(fifth);
        Node sixth = new Node(1);
        fifth.setNextNode(sixth);
        // List: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 -> null
        Node cur = partitionList(head, 5);
        while(cur != null){
            System.out.print(cur.getData() + " -> "); // Output: 3 -> 2 -> 1 -> 5 -> 8 -> 5 -> 10 -> null
            cur = cur.getNextNode();
        }
        System.out.println("null");
    }
}
