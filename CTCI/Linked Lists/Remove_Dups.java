/*

Noah Park

Problem:

Write code to remove duplicates from an unsorted linked list. Furthermore, how would you solve this problem if a temporary buffer is not allowed.

*/

import java.util.LinkedList;

public class Remove_Dups {

    // Time: O(n)
    // Space: O(n) -> if every element in the linked list was duplicated (worst case)
    public static LinkedList<Integer> removeDups(LinkedList<Integer> l){
        // Build a new linked list that does not include duplicate nodes from the original list l
        LinkedList<Integer> newList = new LinkedList<>();
        for(Integer i : l){ // Iterate through the nodes in the list l
            if(!newList.contains(i)){ // If the new list does not contain our current node, add it to the linked list
                newList.add(i);
            }
        }
        return newList; // Return our newly constructed list without duplicate nodes
    }

    // This is solution for attempting to solve this problem WITHOUT a temporary buffer/new data structure.
    // Time: O(n^2)
    // Space: O(1)
    // Peculiarly, this method works correctly at removing duplicates, but did not remove the integer I was expecting
    // The remove method seems to remove the first instance of the duplicate rather than the expected second.
    public static LinkedList<Integer> removeDupsNoBuffer(LinkedList<Integer> l){
        // Use two "pointers" to iterate doubly through list l to check for duplicates
        for(int i = 0; i < l.size() - 1; i++){
            for(int j = i + 1; j < l.size(); j++){
                if(l.get(i) == l.get(j)){ // If we find a duplicate, we should remove it from the list l
                    l.remove(l.get(j));
                }
            }
        }
        return l; // Return the adjusted list l
    }

    public static void main(String[] args){
        LinkedList<Integer> l = new LinkedList<>();
        l.add(0, 0);
        l.add(1, 1);
        l.add(2, 2);
        l.add(3, 1);
        l.add(4, 3);
        l.add(5, 4);
        l.add(6, 3);
        System.out.println("Remove duplicates with a second Linked List:");
        for(Integer i : l){
            System.out.print(i + " -> "); // l: 0 -> 1 -> 2 -> 1 -> 3 -> 4 -> 3 -> null
        }
        System.out.println("null");
        LinkedList<Integer> newList = removeDups(l); // Remove duplicate nodes
        for(Integer i : newList){
            System.out.print(i + " -> "); // Should print 0 -> 1 -> 2 -> 3 -> 4 -> null
        }
        System.out.println("null");
        System.out.println();

        LinkedList<Integer> l2 = new LinkedList<>();
        l2.add(0, 0);
        l2.add(1, 1);
        l2.add(2, 2);
        l2.add(3, 1);
        l2.add(4, 3);
        l2.add(5, 4);
        l2.add(6, 3);
        System.out.println("Remove duplicates with no additional data structures:");
        for(Integer i : l2){
            System.out.print(i + " -> "); // l: 0 -> 1 -> 2 -> 1 -> 3 -> 4 -> 3 -> null
        }
        System.out.println("null");
        LinkedList<Integer> newList2 = removeDupsNoBuffer(l2); // Remove duplicate nodes
        for(Integer i : newList2){
            System.out.print(i + " -> "); // Should print 0 -> 2 -> 1 -> 4 -> 3 -> null
        }
        System.out.println("null");
    }
}
