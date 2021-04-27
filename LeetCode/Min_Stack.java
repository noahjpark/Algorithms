/* Noah Park

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.

*/

class MinStack {
    
    // Intuition: Maintain the normal stack then an ordered (minimum at the top) stack of the minimum elements. We don't care about larger elements in between. If we popped a min element, then next min would be the element smaller than the larger ones. We would need to pop off the larger elements in between to reach the next min element. 
    Stack<Integer> stack; // stores typical elements
    Stack<Integer> min;   // stores min elements
    
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        min = new Stack<>();
    }
    
    public void push(int x) {
        stack.push(x); // always push regular elements
        if (min.isEmpty() || x <= min.peek()) min.push(x); // store min for our first element and all smaller elements than the previous after
    }
    
    public void pop() {
        if (stack.pop().equals(min.peek())) min.pop(); // if we are popping the min, remove from the min stack
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
