/*

Noah Park

Problem:

You have two numbers represented by a linked list, where each node contains a single digit. The digits are stored in reverse order, such that the 1's digit is the head of
the list. Write a function that adds the two numbers and returns the sum as a linked list. Write a second function for where the digits are stored in forward order.

*/

public class Sum_Lists {

    // Time: O(n)
    // Space: O(n)
    public static Node sumListsReverse(Node n1, Node n2){
        int mult = 1;   // Multiplication factor to multiply each individual data by a multiple of 10
        int num1 = 0;   // Integer value of the list n1 added together
        int num2 = 0;   // Integer value of the list n2 added together
        Node cur = n1;  // Pointer initialized to add the data in n1 together
        while(cur != null){                 // Iterate through n1
            num1 += (cur.getData() * mult); // Since we are in reverse order, we add data*mult and increment mult by the next multiple of 10
            mult *= 10;
            cur = cur.getNextNode();
        }
        mult = 1;   // Reset mult to go through n2
        cur = n2;   // Set pointer to n2
        while(cur != null){
            num2 += (cur.getData() * mult); // Since we are in reverse order, we add data*mult and increment mult by the next multiple of 10
            mult *= 10;
            cur = cur.getNextNode();
        }
        int sum = num1 + num2;  // Sum up the two numbers
        Node newHead = new Node(sum % 10);  // Use modular arithmetic by 10 to obtain each integer as a singular value and set this as our head node to begin with
        sum -= sum % 10;    // Subtract the modulus to get a number divisible by ten
        sum /= 10;          // Divide by ten to move to the next number
        Node newCur = newHead;  // Use a pointer to set the rest of the list
        while(sum > 0){         // While we still have numbers to insert into the list we will continue adding nodes to the list
            Node next = new Node(sum % 10);     // Use the modular arithmetic, then set the next node using the pointer and move the pointer down the line
            newCur.setNextNode(next);              // Finally, decrement sum using the tactic above
            newCur = newCur.getNextNode();
            sum -= sum % 10;
            sum /= 10;
        }
        return newHead; // Return the head of the newly created list
    }

    // Time: O(n)
    // Space: O(n)
    public static Node sumListForward(Node n1, Node n2){
        int length = 0; // To help us figure out what multiple of 10 to start our mult variable at
        Node cur = n1;
        while(cur != null){ // Count the number of numbers in the list
            length++;
            cur = cur.getNextNode();
        }
        int mult = 1;   // Multiplication factor to multiple each individual data by a multiple of 10
        for(int i = 0; i < length - 1; i++){
            mult *= 10; // This will start our multiplication at the correct multiple of 10
        }
        int div = mult; // To be used later for modular arithmetic
        int num1 = 0;   // Integer value of the list n1 added together
        int num2 = 0;   // Integer value of the list n2 added together
        cur = n1;  // Pointer initialized to add the data in n1 together
        while(cur != null){                 // Iterate through n1
            num1 += (cur.getData() * mult); // Since we are in reverse order, we add data*mult and divide mult by the next multiple of 10
            mult /= 10;
            cur = cur.getNextNode();
        }
        mult = div; // Reset mult to go through n2
        cur = n2;   // Set pointer to n2
        while(cur != null){
            num2 += (cur.getData() * mult); // Since we are in reverse order, we add data*mult and divide mult by the next multiple of 10
            mult /= 10;
            cur = cur.getNextNode();
        }
        int sum = num1 + num2;  // Sum up the two numbers
        int next = sum % div;   // Remainding numbers
        sum -= next;            // make sum divible by div from earlier
        Node newHead = new Node(sum / div);  // Use modular arithmetic by 10 to obtain each integer as a singular value and set this as our head node to begin with
        sum = next;             // Sum becomes remainding numbers
        div /= 10;              // Divide by ten to move to the next number
        Node newCur = newHead;  // Use a pointer to set the rest of the list
        while(sum > 0){         // While we still have numbers to insert into the list we will continue adding nodes to the list
            next = sum % div;       // Remainding numbers
            sum -= next;            // sum becomes remainding numbers
            Node nextNode = new Node(sum / div);     // Use the modular arithmetic, then set the next node using the pointer and move the pointer down the line
            newCur.setNextNode(nextNode);
            newCur = newCur.getNextNode();
            sum = next;             // Sum becomes remainding numbers
            div /= 10;              // Divide by ten to move to the next number
        }
        return newHead; // Return the head of the newly created list
    }

    public static void main(String[] args){
        // Reverse order number 1
        Node head1 = new Node(7);
        Node first1 = new Node(1);
        head1.setNextNode(first1);
        Node second1 = new Node(6);
        first1.setNextNode(second1);

        // Reverse order number 2
        Node head2 = new Node(5);
        Node first2 = new Node(9);
        head2.setNextNode(first2);
        Node second2 = new Node(2);
        first2.setNextNode(second2);

        Node cur = sumListsReverse(head1, head2);
        while(cur != null){
            System.out.print(cur.getData() + " -> "); // Expecting: 2 -> 1 -> 9 -> null which is equal to 912
            cur = cur.getNextNode();
        }
        System.out.println("null");

        // Forward order number 1
        Node head3 = new Node(6);
        Node first3 = new Node(1);
        head3.setNextNode(first3);
        Node second3 = new Node(7);
        first3.setNextNode(second3);

        // Forward order number 2
        Node head4 = new Node(2);
        Node first4 = new Node(9);
        head4.setNextNode(first4);
        Node second4 = new Node(5);
        first4.setNextNode(second4);

        cur = sumListForward(head3, head4);
        while(cur != null){
            System.out.print(cur.getData() + " -> "); // Expecting 9 -> 1 -> 2 -> null which is equal to 912
            cur = cur.getNextNode();
        }
        System.out.println("null");
    }
}
