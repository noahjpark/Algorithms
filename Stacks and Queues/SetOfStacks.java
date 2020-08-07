// Noah Park
/*

Problem: Imagine a (literal) stack of plates. If the stack gets too high,
it might topple. Therefore, in real life, we would likely start a new
stack when the previous stack exceeds some threshold. Implement a data structure
SetOfStacks that mimics this. SetOfStacks should be composed of several stacks
and should create a new stack once the previous one exceeds capacity. SetOfStacks.push()
and SetOfStacks.pop() should behave identically to a single stack (that is, pop()
should return the same values as it would if there were just a single stack).
FOLLOW UP
Implement a function popAt(int index) which performs a pop operation on a specific sub-stack.

*/

public class SetOfStacks<T extends Comparable<T>> {

    // Stacks is a stack of stacks
    // currentStack is our pointer to the current stack we are filling
    public Stack<T>[] stacks;
    public int currentStack;

    // Base constructor
    @SuppressWarnings("unchecked")
    public SetOfStacks(){
        this.stacks = new Stack[10];
        this.currentStack = 0;
        // Must populate stacks with empty stacks in each position to avoid nullPointerExceptions
        for(int i = 0; i < this.stacks.length; i++){
            this.stacks[i] = new Stack<>(10);
        }
    }

    // User input constructor
    @SuppressWarnings("unchecked")
    public SetOfStacks(int size){
        this.stacks = new Stack[size];
        this.currentStack = 0;
        // Must populate stacks with empty stacks in each position to avoid nullPointerExceptions
        for(int i = 0; i < this.stacks.length; i++){
            this.stacks[i] = new Stack<>(size);
        }
    }

    // Doubles 'stacks' size when all stacks in 'stacks' have been filled
    @SuppressWarnings("unchecked")
    public void growStack(){
        // Copy all elements from 'stacks' into 'larger'
        Stack<T>[] larger = new Stack[this.stacks.length * 2];
        for(int i = 0; i < this.stacks.length; i++){
            larger[i] = this.stacks[i];
        }
        // Fill the rest of larger's indices with new stacks of equal size to the original ones
        for(int i = stacks.length; i < larger.length; i++){
            larger[i] = new Stack<>(this.stacks[0].getSize());
        }
        // Update 'stacks'
        this.stacks = larger;
    }

    // Push a new element like normal to our current stack in 'stacks'
    public void push(T element){
        // If 'stacks' isn't full then we push our element
        if(!this.isFull()){
            // Check if the current stack we are trying to push to isn't full and push if not
            if(!currentStackFull()){
                this.stacks[currentStack].push(element);
            }
            // Current stack in 'stacks' is full. We must increment our currentStack index to
            // move to the next stack value and retry the push method. If currentStack goes
            // "out of bounds", calling the push method again will cause the stack to grow.
            else {
                currentStack++;
                this.push(element);
            }
        }
        // Otherwise, we must double 'stacks' size and retry the push method
        else{
            System.out.println("Stack is Full: Doubling stack capacity to make room!");
            growStack();
            this.push(element);
        }
    }

    // Pop the top element like normal from our current stack in 'stacks'
    public T pop(){
        // If 'stacks' isn't empty then we pop our element
        if(!isEmpty()){
            // If the current stacks is empty, decrement our currentStack pointer
            if (currentStackEmpty()) {
                currentStack--;
            }
            // Calling pop will be fine here, as the stack class I wrote has built in error checking
            // for if the current stack is empty
            return this.stacks[currentStack].pop();
        }
        // Otherwise, do not pop anything if 'stacks' is empty
        else{
            System.out.println("Stack of Stacks is Empty: Pop failed!");
            return null;
        }
    }

    // Pops the top element of the stack at a particular index in 'stacks'
    // Does not complete the operation if the index is out of bounds
    public T popAt(int index){
        if(index < 0 || index >= this.stacks.length){
            System.out.println("Index out of bounds: Pop failed!");
            return null;
        }

        if(!currentStackEmpty()){
            return this.stacks[index].pop();
        }
        else{
            System.out.println("Stack at index " + index + " is empty: Pop failed!");
            return null;
        }
    }

    // Check if 'stacks' is full - currentStack pointer is at 'stacks' length
    public boolean isFull(){
        return this.currentStack == this.stacks.length;
    }

    // Check if the current stack in 'stacks' is full using the stack's isFull() method
    public boolean currentStackFull(){
        return this.stacks[this.currentStack].isFull();
    }

    // Check if 'stacks' is empty - currentStack pointer is 0 and the current stack in
    // 'stacks' is empty
    public boolean isEmpty(){
        return this.currentStack == 0 && this.currentStackEmpty();
    }

    // Checks if the current stack in 'stacks' is empty using the isEmpty() method
    public boolean currentStackEmpty(){
        return this.stacks[this.currentStack].isEmpty();
    }

    // Prints each stack in 'stacks' like a stack (from top to bottom) without actually
    // modifying the stacks themselves
    public String toString(){
        String s = "";
        for(int k = 0; k < this.stacks.length; k++){
            System.out.println("Stack " + k + ": TOP\n");
            for(int i = this.stacks[k].getSize() - 1; i >= 0; i--){
                System.out.println(this.stacks[k].stack[i]);
            }
            System.out.println();
            System.out.println("BOTTOM");
            System.out.println();
        }
        return s;
    }

    // Some testing to make sure everything is working right.
    public static void main(String[] args){
        SetOfStacks<String> s = new SetOfStacks<>(2);
        s.push("first");
        s.push("second");
        s.push("third");
        s.push("fourth");
        s.push("fifth");
        s.push("sixth");
        System.out.println(s);
        s.pop();
        s.pop();
        s.pop();
        s.pop();
        s.pop();
        s.pop();
        System.out.println(s);
        s.pop();

        s.push("first");
        s.push("second");
        s.push("third");
        s.push("fourth");

        s.popAt(0);
        System.out.println(s);
    }
}
