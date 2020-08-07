// Noah Park
// My array implementation of a stack to be used for my stack problems

public class Stack<T extends Comparable<T>> {

    // stack is public so for printing purposes (toString) in SetOfStacks
    // stack is an array representation of a stack
    // top represents our current pointer to the top element
    public T[] stack;
    private int top;

    // Base constructor
    @SuppressWarnings("unchecked")
    public Stack(){
        this.stack = (T[]) new Comparable[10];
        this.top = 0;
    }

    // User input constructor
    @SuppressWarnings("unchecked")
    public Stack(int size){
        this.stack = (T[]) new Comparable[size];
        this.top = 0;
    }

    // Push element to the top of the stack
    public void push(T element){
        // If the stack isn't full we can put it at the top position and increment top
        if(!this.isFull()){
            this.stack[top++] = element;
        }
        // Stack is full and we cannot push
        else{
            System.out.println("Stack is Full: Failed to push element");
        }
    }

    // Pop element from the top of the stack
    public T pop(){
        // If the stack isn't empty, we can get the top element, set is position to null, and return it
        if(!this.isEmpty()){
            T element = this.stack[--top];
            this.stack[top] = null;
            return element;
        }
        // If the stack is empty, we cannot pop
        else{
            System.out.println("Stack is Empty: Failed to pop element");
            return null;
        }
    }

    // If top is at the stack's length, the stack is full
    public boolean isFull(){
        return this.top == this.stack.length;
    }

    // If top is at 0, the stack is empty
    public boolean isEmpty(){
        return this.top == 0;
    }

    // Returns the current number of elements in the stack
    public int getSize(){
        return this.top;
    }
}
