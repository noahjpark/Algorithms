/* Noah Park

Design a hit counter which counts the number of hits received in the past 5 minutes (i.e., the past 300 seconds).

Your system should accept a timestamp parameter (in seconds granularity), and you may assume that calls are being made to the system in chronological order (i.e., timestamp is monotonically increasing). Several hits may arrive roughly at the same time.

Implement the HitCounter class:

HitCounter() Initializes the object of the hit counter system.
void hit(int timestamp) Records a hit that happened at timestamp (in seconds). Several hits may happen at the same timestamp.
int getHits(int timestamp) Returns the number of hits in the past 5 minutes from timestamp (i.e., the past 300 seconds).

*/

// Intuition: My initial solution was with a min heap in case the ordering wasn't guaranteed. This way, we could maintain the ordering of the counts and add easily as well as remove old timestamps. The queue size maintains the number of valid hits in the last 300 seconds. The second solution is derived from the minimizing space idea where we group hits in the same timestamp together in a wrapper integer array. The only caviat is that we must maintain the overall values in the queue since we don't want to loop over the queue to accumulate again.
// Time: O(1) if order is guaranteed, O(log n) for the pq if we must maintain order.
// Space: O(n) to maintain all hits if different. Ideal solution minimizes this in the event of duplicate hits in the same timestamps.

class HitCounter {
    
    int size = 0;
    Deque<int[]> q;
    
    public HitCounter() {
        q = new LinkedList<>();
    }
    
    public void hit(int t) {
        if (q.isEmpty() || q.getLast()[0] != t) q.addLast(new int[]{ t, 1 });
        else {
            int[] cur = q.removeLast();
            cur[1]++;
            q.addLast(cur);
        }
        
        
        size++;
    }
    
    public int getHits(int t) {
        while (!q.isEmpty() && t - q.getFirst()[0] >= 300) {
            int[] cur = q.removeFirst();
            size -= cur[1];
        }
        return size;
    }
    
}

// class HitCounter {
    
//     PriorityQueue<Integer> minHeap;

//     /** Initialize your data structure here. */
//     public HitCounter() {
//         minHeap = new PriorityQueue<>();
//     }
    
//     /** Record a hit.
//         @param timestamp - The current timestamp (in seconds granularity). */
//     public void hit(int timestamp) {
//         minHeap.offer(timestamp);
//     }
    
//     /** Return the number of hits in the past 5 minutes.
//         @param timestamp - The current timestamp (in seconds granularity). */
//     public int getHits(int timestamp) {
//         while (!minHeap.isEmpty() && timestamp - minHeap.peek() >= 300) minHeap.poll();
//         return minHeap.size();
//     }
// }

/**
 * Your HitCounter object will be instantiated and called as such:
 * HitCounter obj = new HitCounter();
 * obj.hit(timestamp);
 * int param_2 = obj.getHits(timestamp);
 */
