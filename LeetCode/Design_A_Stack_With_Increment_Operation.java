/* Noah Park

Design a stack which supports the following operations.

Implement the CustomStack class:

CustomStack(int maxSize) Initializes the object with maxSize which is the maximum number of elements in the stack or do nothing if the stack reached the maxSize.
void push(int x) Adds x to the top of the stack if the stack hasn't reached the maxSize.
int pop() Pops and returns the top of stack or -1 if the stack is empty.
void inc(int k, int val) Increments the bottom k elements of the stack by val. If there are less than k elements in the stack, just increment all the elements in the stack.

*/

class CustomStack {
    
    int maxSize; // maxSize of the stack
    Stack<Integer> stack; // stack structure
    int[] increment; // stores the increment values for O(1)

    // Default constructor for CustomStack
    public CustomStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new Stack<>();
        increment = new int[maxSize];
    }
    
    // pushes x onto the stack if the stack is not at capacity
    public void push(int x) {
        if(stack.size() < maxSize) stack.push(x);
    }
    
    // pops from the stack
    public int pop() {
        int n = stack.size() - 1; // n is used for the increment portion
        if(stack.size() == 0) return -1; // if the stack is empty do not pop
        if(n > 0) increment[n - 1] += increment[n]; // if n > 0, we can move the increment value down in the increment array (not replace we use += to maintain)
        int res = stack.pop() + increment[n]; // return the value in the stack plus the incremented value
        increment[n] = 0; // reset the incremented value for future use
        return res; // return the result
    }
    
    // use the increment array to store the val at the smaller of the size of the stack - 1 or k - 1
    public void increment(int k, int val) {
        int idx = Math.min(k, stack.size()) - 1;
        if(idx > -1) increment[idx] += val;
    }
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */
