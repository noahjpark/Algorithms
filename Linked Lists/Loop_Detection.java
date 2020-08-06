// Noah Park
/*

Problem: Given a circular linked list, implement an algorithm that
returns the node at the beginning of the loop.

*/

import java.util.HashMap;

public class Loop_Detection {

    // Returns the starting node of the loop, null elsewise
    public static Node<Character> findLoop(Node<Character> node){
        // nodes will hold all the nodes in the list
        // If we find a repeat node, we have found our circle
        // loop will represent this repeat node
        HashMap<Character, Node<Character>> nodes = new HashMap<>();
        Node<Character> loop = null;

        // Iterate through the list
        Node<Character> head = node;
        while(head != null){
            if(nodes.containsValue(head)){
                loop = head;
                break;
            }
            nodes.put(head.getData(), head);
            head = head.getNext();
        }

        // If head reaches null, there wasn't a loop and all
        // entries in the hashmap are unique.
        // Simply return loop as it will have been updated in
        // the loop. Otherwise it will still be its initial value
        // of null.
        return loop;
    }

    public static void main(String[] args){
        // A -> B -> C -> D -> E -> C -> .....
        // Loop start is C
        Node<Character> n1 = new Node<>('A');
        Node<Character> n2 = new Node<>('B');
        Node<Character> n3 = new Node<>('C');
        Node<Character> n4 = new Node<>('D');
        Node<Character> n5 = new Node<>('E');

        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);
        n4.setNext(n5);
        n5.setNext(n3);

        Node<Character> loopStart = findLoop(n1);

        System.out.println(loopStart.getData());

    }

}
