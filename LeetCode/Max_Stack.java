/* Noah Park

Design a max stack data structure that supports the stack operations and supports finding the stack's maximum element.

Implement the MaxStack class:

MaxStack() Initializes the stack object.
void push(int x) Pushes element x onto the stack.
int pop() Removes the element on top of the stack and returns it.
int top() Gets the element on the top of the stack without removing it.
int peekMax() Retrieves the maximum element in the stack without removing it.
int popMax() Retrieves the maximum element in the stack and removes it. If there is more than one maximum element, only remove the top-most one.

*/

class MaxStack {
    
    // Intuition: Maintain two stacks, one that holds the normal values and the other with the increasing maximums. 
    // Time and Space: O(n) linear.
    Stack<Integer> s, max;

    /** initialize your data structure here. */
    public MaxStack() {
        s = new Stack<>();
        max = new Stack<>();
    }
    
    public void push(int x) {
        s.push(x);
        if (max.isEmpty() || max.peek() <= x) max.push(x);
    }
    
    public int pop() {
        if (top() == peekMax()) { s.pop(); return max.pop(); }
        return s.pop();
    }
    
    public int top() {
        return s.peek();
    }
    
    public int peekMax() {
        return max.peek();
    }
    
    public int popMax() {
        Stack<Integer> temp = new Stack<>();
        while (top() != peekMax()) temp.push(pop());
        int res = pop();
        while (!temp.isEmpty()) push(temp.pop());
        return res;
    }

}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */
