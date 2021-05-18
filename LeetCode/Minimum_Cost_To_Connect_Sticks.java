/* Noah Park

You have some number of sticks with positive integer lengths. These lengths are given as an array sticks, where sticks[i] is the length of the ith stick.

You can connect any two sticks of lengths x and y into one stick by paying a cost of x + y. You must connect all the sticks until there is only one stick remaining.

Return the minimum cost of connecting all the given sticks into one stick in this way.

*/

class Solution {
    
    // Intuition: I notice that at any point, we try to minimize the cost of combining sticks. This screams ordering so I chose a min heap. Simply iterate until the size of the heap is 1 (i.e. we have combined all sticks into 1). At each iteration take the two smallest sticks, combine them (add them to the result) then add the combination back to the heap. Heap maintains the minimum ordering so the top will always be the smallest elements.
    // Time: O(nlogn) for heap creation and looping.
    // Space: O(n) to maintain the heap.
    public int connectSticks(int[] sticks) {
        if (sticks == null || sticks.length == 0) return 0;
        
        int res = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int num : sticks)
            minHeap.offer(num);
        
        while (minHeap.size() > 1) {
            int first = minHeap.poll(), second = minHeap.poll();
            res += (first + second);
            minHeap.offer(first + second);
        }
        
        return res;
    }
}
