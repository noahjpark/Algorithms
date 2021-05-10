/* Noah Park

Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Implement KthLargest class:

KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of integers nums.
int add(int val) Returns the element representing the kth largest element in the stream.

*/

class KthLargest {

    // Intuition: Utilize a min heap to keep track of the k largest elements in the stream. Whenever the heap's size grows larger than k, poll the smallest element from the top. Otherwise whenever we need the kth largest, we just look at the top (smallest) element. Since we aren't removing anything, we don't care about the other elements.
    // Time: O(n log k) for creating the heap.
    // Space: O(k) for storing k integers.
    int k;
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    
    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) minHeap.poll();
        }
    }
    
    public int add(int val) {
        minHeap.offer(val);
        if (minHeap.size() > k) minHeap.poll();
        return minHeap.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
