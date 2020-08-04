// Noah Park
/*

Problem: Given two singly linked lists, determine if the two lists
intersect. Return the intersecting node. Note that the intersection
is defined based on reference, not value. That is, if the kth node
of the first linked list is the exact same node (by reference) as
the jth node of the second linked list, then they are intersecting.

*/

public class Intersection {

    // Finds the last node given the head node of a linked list
    public static Node<Integer> findLast(Node<Integer> node){
        Node<Integer> cur = node;
        while(cur.getNext() != null){
            cur = cur.getNext();
        }
        return cur;
    }

    // Finds the node in the linked list headed by "node" that
    // precedes "target"
    // This method is used to move "backwards" in the lists
    public static Node<Integer> findPrev(Node<Integer> node, Node<Integer> target){
        Node<Integer> cur = node;
        while(cur.getNext() != target){
            cur = cur.getNext();
        }
        return cur;
    }

    // Returns the first intersecting node given two linked lists
    // If no intersection exists, returns null
    public static Node<Integer> findIntersection(Node<Integer> n1, Node<Integer> n2){
        // An intersecting list will have the same last node
        // Start by checking both last nodes and return null
        // if they are not the same
        Node<Integer> last1 = findLast(n1);
        Node<Integer> last2 = findLast(n2);
        if(last1 != last2){
            return null;
        }

        // Last nodes match and there is an intersection
        // Track the most recent intersection using 'intersect'
        Node<Integer> intersect = last1;

        // Loops while the pointer references are the same
        while(last1 == last2){
            intersect = last1; // Update the intersection pointer since last1 and last2 have the same reference
            // Moves the pointers using the "backwards" helper method
            last1 = findPrev(n1, last1);
            last2 = findPrev(n2, last2);
        }

        return intersect;
    }

    public static void main(String[] args){
        Node<Integer> n1 = new Node<>(1);
        Node<Integer> n2 = new Node<>(2);
        Node<Integer> n3 = new Node<>(3);
        Node<Integer> n4 = new Node<>(4);
        Node<Integer> n5 = new Node<>(5);

        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);
        n5.setNext(n4);

        Node<Integer> intersect = findIntersection(n1, n5);

        if(intersect != null) {
            System.out.println("Intersecting Node's Data: " + intersect.getData());
        }
    }

}
