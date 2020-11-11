/* Noah Park

Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

*/

class Solution {
    public int findKthLargest(int[] nums, int k) {
        // Min Heap stores largest elements seen so far
        // The k'th largest element will be the smallest of the k largest elements which will be the top of the min heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k, (a, b) -> Integer.compare(a, b));
        
        // Offer the first k elements
        for(int i = 0; i < k; i++)
            minHeap.offer(nums[i]);
        
        // Go through the rest and update the min heap based on the number
        for(int i = k; i < nums.length; i++){
            if(nums[i] > minHeap.peek()){
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        
        // k'th element is at the top of the min heap
        return minHeap.peek();
    }
}
