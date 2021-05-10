/* Noah Park

Given an array of integers target. From a starting array, A consisting of all 1's, you may perform the following procedure :

let x be the sum of all elements currently in your array.
choose index i, such that 0 <= i < target.size and set the value of A at index i to x.
You may repeat this procedure as many times as needed.
Return True if it is possible to construct the target array from A otherwise return False.

*/

class Solution {
    
    // Intuition: Work backwards from the target array until the maximum value in our max heap is 1 meaning all values are 1. Since we go from largest to smallest, a max heap makes sense for this.
    // Time: O(n + logk*logn) for first loop plus the heap loop.
    // Space: O(n) for the max heap.
    public boolean isPossible(int[] target) {
        if (target.length == 1) return target[0] == 1;
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> (b - a));
        
        int sum = 0;
        for (int num : target) { sum += num; maxHeap.offer(num); }
        
        while (maxHeap.peek() != 1) {
            int cur = maxHeap.poll();
            sum -= cur; // get remaining sum
            
            if (sum == 1) return true; // sum only becomes 1 if there are two elements.
            if (cur <= sum || sum < 1) return false; // if max value isn't larger than the sum of the remaining elements, the array is clearly invalid. If sum is 0, the array is invalid.
            
            int rem = cur % sum; // break down as low as possible
            sum += rem; // rebuild full sum for next iteration
            
            if (rem == 0 || rem == cur) return false;
            
            maxHeap.offer(rem); // If we have a remainder, we can put the number back in, otherwise, 
        }
        
        return true;
    }
}
