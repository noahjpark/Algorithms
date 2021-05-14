/* Noah Park

Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".

One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, we can use the space to store new values.

Implementation the MyCircularQueue class:

MyCircularQueue(k) Initializes the object with the size of the queue to be k.
int Front() Gets the front item from the queue. If the queue is empty, return -1.
int Rear() Gets the last item from the queue. If the queue is empty, return -1.
boolean enQueue(int value) Inserts an element into the circular queue. Return true if the operation is successful.
boolean deQueue() Deletes an element from the circular queue. Return true if the operation is successful.
boolean isEmpty() Checks whether the circular queue is empty or not.
boolean isFull() Checks whether the circular queue is full or not.
You must solve the problem without using the built-in queue data structure in your programming language.

*/

class MyCircularQueue {
    
    // Intuition: Typical circular queue implementation. Wraps start and end when needed.
    // Time: O(1) all operations are constant.
    // Space: O(k) for the queue.
    int[] q;
    int s, e, size, k;

    public MyCircularQueue(int k) {
        this.k = k;
        q = new int[k];
        s = 0;
        e = 0;
        size = 0;
    }
    
    public boolean enQueue(int value) {
        if (size == k) return false;
        
        q[e++] = value;
        size++;
        e = e % k;
        return true;
    }
    
    public boolean deQueue() {
        if (size == 0) return false;
        
        s = (s + 1) % k;
        size--;
        return true;
    }
    
    public int Front() {
        return size == 0 ? -1 : q[s];
    }
    
    public int Rear() {
        return size == 0 ? -1 : e == 0 ? q[k - 1] : q[e - 1];
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == k;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
