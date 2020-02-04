/*

Noah Park

Simple Node implementation for a linked list to aid in testing the linked list algorithms

*/

public class Node {

    // Initialize next node to null
    private Node next = null;
    private int data;

    // Set data to the passed in data
    public Node(int d){
        this.data = d;
    }

    // Return data
    public int getData(){
        return this.data;
    }

    // Set new data
    public void setData(int d){
        this.data = d;
    }

    // Return next node
    public Node getNextNode(){
        return this.next;
    }

    // Set new next node
    public void setNextNode(Node n){
        this.next = n;
    }
}
