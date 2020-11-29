// Noah Park
/*

Problem: Write a program to sort a stack such that the smallest items
are on the top. You can use an additional temporary stack, but you may
not copy the elements into any other data structure (such as an array).
The stack supports the following operations: push, pop, peek, and isEmpty.

*/

public class Sort_Stack<T extends Comparable<T>> {

    // Sorts the stack so that the smallest elements are on the top
    // of the stack
    public Stack<T> sort(Stack<T> s){
        // If s is empty, simply return it
        if(s.isEmpty()){
            return s;
        }

        // Use a second stack for sorting
        // s2 will maintain a sorted list (descending) from top to bottom so that when
        // transferring back to s will be sorted in ascending order
        Stack<T> s2 = new Stack<>(s.getSize());

        // Loop while s still has elements to sort into s2
        while(!s.isEmpty()){
            // pop the top off s to insert into the correct position in s2
            T top = s.pop();
            int count = 0; // Count the number of elements we put back into s that we need to move back to s2
            // Loop while s2 is not empty and its top element is larger than s's old top
            // This will get us to the correct position in the stack to maintain sorted order
            while(!s2.isEmpty() && s2.peek().compareTo(top) > 0){
                // Move elements from s2 back to s and increment count
                s.push(s2.pop());
                count++;
            }
            // Insert the old top of s into the correct position in s2
            s2.push(top);
            // Put back the elements moved from s2 so that it will still contain them in a sorted order
            for(int i = 0; i < count; i++){
                s2.push(s.pop());
            }
        }
        // Now just transfer everything from s2 back to s which will now have been sorted
        while(!s2.isEmpty()){
            s.push(s2.pop());
        }
        return s;
    }

    // Testing for the sort stack program
    public static void main(String[] args){
        Sort_Stack<Integer> stack = new Sort_Stack<>();
        Stack<Integer> s = new Stack<>();
        s.push(0);
        s.push(8);
        s.push(1);
        s.push(7);
        s.push(3);
        s.push(2);
        s.push(4);
        s.push(6);
        s.push(9);
        s.push(5);
        System.out.println(s);
        Stack<Integer> sorted = stack.sort(s);
        System.out.println(sorted);
    }

}
