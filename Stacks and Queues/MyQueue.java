// Noah Park
/*

Problem: Implement a MyQueue class which implements a queue using two stacks

*/

public class MyQueue<T extends Comparable<T>> {

    // mainStack is the stack maintaining the queue
    // transferStack is the stack to help add, remove, and print the queue's contents
    private Stack<T> mainStack;
    private Stack<T> transferStack;

    // Base constructor for the generic size queue (10)
    public MyQueue(){
        mainStack = new Stack<>();
        transferStack = new Stack<>();
    }

    // Second constructor for choosing the size of the queue
    public MyQueue(int size){
        mainStack = new Stack<>(size);
        transferStack = new Stack<>(size);
    }

    // Add to the queue if the mainStack is not empty, otherwise send an error message
    public void add(T element){
        if(!mainStack.isFull()){
            mainStack.push(element);
        }
        else{
            System.out.println("Queue is Full: Push failed");
        }
    }

    // Remove from the queue using transferStack
    // Queue is FIFO: by adding all the popped elements to the transferStack from mainStack,
    // we end up with the backwards order i.e. the first element is on top.
    // We can simply pop the top element from transferStack then push the rest of the elements
    // that are popped back to mainStack excluding the first one.
    // Note: if the mainStack is empty, we do not remove from the queue
    public T remove(){
        if(!mainStack.isEmpty()){
            while(!mainStack.isEmpty()){
                transferStack.push(mainStack.pop());
            }
            T element = transferStack.pop();
            while(!transferStack.isEmpty()){
                mainStack.push(transferStack.pop());
            }
            return element;
        }
        else{
            System.out.println("Queue is Empty: Pop failed");
            return null;
        }
    }

    // Similar technique to remove except we still push the top element from transferStack
    // back onto mainStack so order is maintained. We just simply store it in a temporary
    // variable that we return in the end.
    public T peek(){
        while(!mainStack.isEmpty()){
            transferStack.push(mainStack.pop());
        }
        T element = transferStack.peek();
        while (!transferStack.isEmpty()) {
            mainStack.push(transferStack.pop());
        }
        return element;
    }

    // Return true if the mainStack is full and false otherwise
    public boolean isFull(){
        return this.mainStack.isFull();
    }

    // Return true if the mainStack is empty and false otherwise
    public boolean isEmpty(){
        return this.mainStack.isEmpty();
    }

    // Prints the queue in a readable format
    public String toString(){
        String s = "";
        s += "Queue Start: ";
        while(!mainStack.isEmpty()){
            transferStack.push(mainStack.pop());
        }
        while(!transferStack.isEmpty()){
            s += transferStack.peek();
            s += " -> ";
            mainStack.push(transferStack.pop());
        }
        s += "END";
        return s;
    }

    // Testing of the functionality of the MyQueue class
    public static void main(String[] args){
        MyQueue<String> q = new MyQueue<>(5);
        q.remove();
        q.add("first");
        q.add("second");
        q.add("third");
        System.out.println(q);
        q.add("fourth");
        q.add("fifth");
        q.add("sixth");
        System.out.println(q);
        q.remove();
        System.out.println(q);
    }

}
