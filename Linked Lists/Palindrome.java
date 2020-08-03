// Noah Park
/*

Problem: Implement a function to check if a linked list is a
palindrome.

*/

import java.util.Stack;

public class Palindrome {

    // Checks if the given linked list (node) is a palindrome or not
    public static boolean isPalindrome(Node<Character> n){
        // We will compare n to the reverse of n
        Node<Character> reversed = reverse(n);
        Node<Character> normal = n;

        // They should be the same length
        // If they don't have the same value at the current index, return false
        while(normal != null && reversed != null){
            if(normal.getData().compareTo(reversed.getData()) != 0){
                return false;
            }
            normal = normal.getNext();
            reversed = reversed.getNext();
        }

        // Returns true if we made it through the loop. All values matched.
        return normal == null && reversed == null;
    }

    // Reverses the linked list in a single pass O(n)
    // Returns the head as a node
    public static Node<Character> reverse(Node<Character> n){
        Node<Character> cur = n;
        Node<Character> prev = null;
        Node<Character> next;
        while(cur != null){
            next = cur.getNext();
            cur.setNext(prev);
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public static boolean isPalindrome2(Node<Character> n){
        // Use the fast slow pointer technique
        // When the fast reaches the end, slow will be in the middle
        Stack<Character> s = new Stack<>();
        int index = 0;
        Node<Character> fast = n;
        Node<Character> slow = n;
        while(fast.getNext() != null){
            // Add for every 2 fast goes through
            if(index % 2 == 0){
                s.push(slow.getData());
                slow = slow.getNext();
            }
            index++;
            fast = fast.getNext();
        }

        // Now go from slow to the end
        while(slow != null){
            // If index is even, then there were an odd number of elements
            // Skip the middle element since it will count towards the palindrome in this case
            if(index % 2 == 0){
                slow = slow.getNext();
                index = 1;
            }
            // Compare the stack's value to the pointer's data and return false if they
            // don't match
            Character c = s.pop();
            if(slow.getData().compareTo(c) != 0){
                return false;
            }
            slow = slow.getNext();
        }

        // Everything has been compared and was ok. Return true.
        return true;
    }

    public static void main(String[] args){
        Node <Character> n = new Node<>('r', new Node<>('a', new Node<>(
                'c', new Node<>('e', new Node<>('c', new Node<>(
                        'a', new Node<>('r', null)))))));

        Node<Character> n2 = new Node<>('r', new Node<>('a', null));

        System.out.println(isPalindrome2(n));
        System.out.println(isPalindrome2(n2));
    }

}
