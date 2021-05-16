/* Noah Park

Design a queue-like data structure that moves the most recently used element to the end of the queue.

Implement the MRUQueue class:

MRUQueue(int n) constructs the MRUQueue with n elements: [1,2,3,...,n].
fetch(int k) moves the kth element (1-indexed) to the end of the queue and returns it.

*/

class MRUQueue {
    
    // Intuition: Straightforward linear movement whenever fetching.
    // Time: O(n) to move to the end.
    // Space: O(n) for the queue.
    int[] q;
    int n;

    public MRUQueue(int n) {
        this.n = n;
        q = new int[n];
        for (int i = 0; i < n; i++) 
            q[i] = i + 1;
    }
    
    public int fetch(int k) {
        for (int i = k - 1; i < n - 1; i++) {
            int temp = q[i];
            q[i] = q[i + 1];
            q[i + 1] = temp;
        }
        return q[n - 1];
    }
}

/**
 * Your MRUQueue object will be instantiated and called as such:
 * MRUQueue obj = new MRUQueue(n);
 * int param_1 = obj.fetch(k);
 */
