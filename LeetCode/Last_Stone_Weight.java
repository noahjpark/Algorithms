/* Noah Park

We have a collection of stones, each stone has a positive integer weight.

Each turn, we choose the two heaviest stones and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:

If x == y, both stones are totally destroyed;
If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)

*/

class Solution {
    
    // Intuition: Maintain a maxHeap of all the stones. Operate until there is 0 or 1 stone left. If there are 0 return 0 otherwise return the weight of the stone.
    // Time: O(nlogn) for the heap creation.
    // Space: O(n) to maintain the heap.
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> (b - a));
        
        for (int stone : stones)
            maxHeap.offer(stone);
        
        while (maxHeap.size() > 1) {
            int difference = maxHeap.poll() - maxHeap.poll();
            if (difference > 0) maxHeap.offer(difference);
        }
        
        return maxHeap.size() == 0 ? 0 : maxHeap.peek();
    }
}
