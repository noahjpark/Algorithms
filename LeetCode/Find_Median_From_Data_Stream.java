/* Noah Park

The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value and the median is the mean of the two middle values.

For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.

*/

class MedianFinder {
    
    // Intuition: Maintain two heaps where the tops are the middle two numbers. Heaps are balanced such that the height differs by no more than 1 at any time and the smallest numbers are in the maxheap while the largest numbers are in the min heap.
    // Time: O(log n) for the heaps.
    // Space: O(n) for the heaps.
    PriorityQueue<Integer> maxHeap, minHeap;

    /** initialize your data structure here. */
    public MedianFinder() {
        maxHeap = new PriorityQueue<>((a, b) -> (b - a));
        minHeap = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        minHeap.offer(num);
        
        if (maxHeap.size() - minHeap.size() == 2) minHeap.offer(maxHeap.poll());
        else if (minHeap.size() - maxHeap.size() == 2) maxHeap.offer(minHeap.poll());
        
        if (!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()){
            int temp = maxHeap.poll(), temp2 = minHeap.poll();
            maxHeap.offer(temp2);
            minHeap.offer(temp);
        }
    }
    
    public double findMedian() {
        if (minHeap.isEmpty() || maxHeap.isEmpty()) return minHeap.isEmpty() ? (double) maxHeap.peek() : (double) minHeap.peek();
        return minHeap.size() == maxHeap.size() ? (double) (minHeap.peek() + maxHeap.peek()) / 2 : minHeap.size() > maxHeap.size() ? (double) minHeap.peek() : (double) maxHeap.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
