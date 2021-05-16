/* Noah Park

Design a system that manages the reservation state of n seats that are numbered from 1 to n.

Implement the SeatManager class:

SeatManager(int n) Initializes a SeatManager object that will manage n seats numbered from 1 to n. All seats are initially available.
int reserve() Fetches the smallest-numbered unreserved seat, reserves it, and returns its number.
void unreserve(int seatNumber) Unreserves the seat with the given seatNumber.

*/

class SeatManager {
    
    // Intuition: Maintain the normal reservation of seats through the ne (next empty) variable. When a seat is unreserved, the minheap will maintain the smallest ordering of unreserved seats since ne will be a larger seat. When reserving, prioritize taking the minimum from the min heap otherwise ne will be the smallest.
    int ne = 1;
    PriorityQueue<Integer> minHeap;

    public SeatManager(int n) {
        minHeap = new PriorityQueue<>();
    }
    
    public int reserve() {
        if (!minHeap.isEmpty()) return minHeap.poll();
        return ne++;
    }
    
    public void unreserve(int seatNumber) {
        minHeap.offer(seatNumber);
    }
}

/**
 * Your SeatManager object will be instantiated and called as such:
 * SeatManager obj = new SeatManager(n);
 * int param_1 = obj.reserve();
 * obj.unreserve(seatNumber);
 */
