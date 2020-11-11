// Noah Park
/*

Problem: Write a function that takes in the head of a Singly Linked List and an integer k and
removes the kth node from the end of the list.

The removal should be done in place, meaning that the original data structure should be mutated
(no new structure should be created).

Furthermore, the input head of the linked list should remain the head of the linked list after the
removal is done, even if the head is the node that's supposed to be removed. In other words, if the head
is the node that's supposed to be removed, your function should simply mutate its value and next pointer.

Note that your function doesn't need to return anything.

You can assume that the input Linked List will always have at least two nodes and, more specifically, at
least k nodes.

Each LinkedList node has an integer value as well as a next node pointing to the next node in the list or
to None / null if it's the tail of the list.

*/

public class Remove_Kth_Node_From_End {
    // O(n) time | O(1) space
    public static void removeKthNodeFromEnd(LinkedList head, int k) {
        int target = size(head) - k; // Get the target index we want to remove
        // Remove the head by shifting over values then cutting off the last element
        if(target == 0){
//            LinkedList cur = head;
//            LinkedList prev = cur;
//            while(cur.next != null){
//                cur.value = cur.next.value;
//                prev = cur;
//                cur = cur.next;
//            }
//            prev.next = null;
            // A better way to write the commented piece
            // Steal the value of the node in front of it (mimic it)
            // Then point to its pointer
            // Inadvertently, this is like cutting off the front of the list
            head.value = head.next.value;
            head.next = head.next.next;
        }
        // Remove any other node by pointing over it
        else{
            LinkedList cur = head;
            LinkedList prev = cur;
            while(target > 0){
                prev = cur;
                cur = cur.next;
                target--;
            }
            prev.next = cur.next;
            cur = null;
        }
    }

    // Clement's solution with a faster and slow pointer
    // O(n) time | O(1) space
    public static void removeKthNodeFromEnd2(LinkedList head, int k){
        int counter = 0;
        LinkedList p1 = head;
        LinkedList p2 = head;
        while(counter < k){
            p2 = p2.next;
            counter++;
        }

        // If we are removing the first value, means that k is equal to our size
        // steal the value in front of it and point to its next value
        if(p2 == null){
            head.value = head.next.value;
            head.next = head.next.next;
            return;
        }
        // Otherwise we adjust our first pointer the rest of the way
        // Essentially, this solution takes out the middle man of having to compute the size of the list
        // By stopping when p2 hits the end of the list (does not fall off), p1 will be one node before the one
        // we want to remove. Thus, we can simply point over it.
        while(p2.next != null){
            p2 = p2.next;
            p1 = p1.next;
        }
        p1.next = p1.next.next;
    }

    // Returns the number of nodes in the given linked list
    public static int size(LinkedList head){
        int i = 0;
        LinkedList cur = head;
        while(cur != null){
            i++;
            cur = cur.next;
        }
        return i;
    }

    // Linked List class for testing
    static class LinkedList {
        int value;
        LinkedList next = null;

        public LinkedList(int value) {
            this.value = value;
        }
    }
}
