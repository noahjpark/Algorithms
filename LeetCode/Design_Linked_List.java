/* Noah Park

Design your implementation of the linked list. You can choose to use a singly or doubly linked list.
A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node.
If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.

Implement the MyLinkedList class:

MyLinkedList() Initializes the MyLinkedList object.
int get(int index) Get the value of the indexth node in the linked list. If the index is invalid, return -1.
void addAtHead(int val) Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
void addAtTail(int val) Append a node of value val as the last element of the linked list.
void addAtIndex(int index, int val) Add a node of value val before the indexth node in the linked list. If index equals the length of the linked list, the node will be appended to the end of the linked list. If index is greater than the length, the node will not be inserted.
void deleteAtIndex(int index) Delete the indexth node in the linked list, if the index is valid.

*/

// Intuition: Utilize a doubly linked list for all methods.
// Time: O(n) to iterate in some cases
// Space: O(1) in all methods.

class Node {
    Node next, prev;
    int val;
    
    public Node(int val) {
        this.val = val;
    }
    
    public Node(int val, Node next) {
        this.next = next;
        this.val = val;
    }
    
    public Node(int val, Node next, Node prev) {
        this.next = next;
        this.prev = prev;
        this.val = val;
    }
}

class MyLinkedList {

    Node head, tail;
    int size;
    
    /** Initialize your data structure here. */
    public MyLinkedList() {
        head = tail = null;
        size = 0;
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */ // optimize with bidirectional search later
    public int get(int index) {
        if (index >= size) return -1;
        
        //print();
        
        Node cur = head;
        
        for (int i = 0; i < index; i++)
            cur = cur.next;
        
        return cur.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        if (head == null) {
            head = new Node(val);
            tail = head;
        } else {
            Node front = new Node(val, head, null);
            if (head != null) head.prev = front;
            head = front;
        }
        size++;
        
        if (tail == null) tail = head;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node end = new Node(val, null, tail);
        if (tail != null) tail.next = end;
        tail = end;
        size++;
        
        if (head == null) head = tail;
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index > size) return;
        else if (index == size) addAtTail(val);
        else if (index == 0) addAtHead(val);
        else {
            Node cur = head;
            for (int i = 0; i < index - 1; i++)
                cur = cur.next;
            Node n = new Node(val, cur.next, cur);
            cur.next.prev = n;
            cur.next = n;
            size++;
        }
    }
    
    public void print() {
        for (Node cur = head; cur != null; cur = cur.next)
            System.out.print(cur.val + " -> ");
        System.out.println("null");
        System.out.println("Head is: " + head.val);
        System.out.println("Tail is: " + tail.val);
        System.out.println("Size is: " + size);
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        //System.out.println(index + " " + size);
        if (index >= size) return;
        else if (index == 0) { 
            head = head.next; 
            if (head != null) head.prev = null; 
        }
        else if (index == size - 1) { 
            tail = tail.prev; 
            if (tail != null) tail.next = null; 
        } 
        else {
            Node prev = head, cur = head;
        
            for (int i = 0; i < index; i++) {
                prev = cur;
                cur = cur.next;
            }
            
            prev.next = cur.next;
            if (cur.next != null) cur.next.prev = prev;
        }
            
        size--;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
