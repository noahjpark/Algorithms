// Noah Park
/*

Problem: Write a MinMaxStack class for a Min Max Stack. The class should support:

    - Pushing and popping values on and off the stack
    - Peeking at the value at the top of the stack
    - Getting both the minimum and the maximum values in the stack at any given point in time

All class methods, when considered independently, should run in constant time and with constant space.

*/

import java.util.ArrayList;

public class Min_Max_Stack_Construction {
    // Feel free to add new properties and methods to the class.
    static class MinMaxStack {

        private ArrayList<Integer> stack;
        private ArrayList<int[]> vals;

        public MinMaxStack(){
            stack = new ArrayList<>();
            vals = new ArrayList<>();
        }

        public int peek() {
            if(stack.isEmpty()){
                return -1;
            }
            return stack.get(stack.size() - 1);
        }

        public int pop() {
            if(stack.isEmpty()){
                return -1;
            }
            int i = stack.size() - 1;
            vals.remove(i);
            return stack.remove(i);
        }

        public void push(Integer number) {
            if(stack.isEmpty()){
                stack.add(number);
                vals.add(new int[] {number, number});
            }
            else{
                stack.add(number);
                int min = this.getMin();
                int max = this.getMax();
                vals.add(new int[2]);
                vals.get(vals.size() - 1)[0] = number < min ? number : min;
                vals.get(vals.size() - 1)[1] = number > max ? number : max;
            }
        }

        public int getMin() {
            if(vals.isEmpty()){
                return -1;
            }
            return vals.get(vals.size() - 1)[0];
        }

        public int getMax() {
            if(vals.isEmpty()){
                return -1;
            }
            return vals.get(vals.size() - 1)[1];
        }
    }
}
