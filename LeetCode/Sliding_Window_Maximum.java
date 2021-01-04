/* Noah Park

You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the max sliding window.

*/

class Solution {
     // Time: O(n) | Space: O(k)
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) return new int[]{}; // edge cases
        
        int n = nums.length;
        int[] res = new int[n - k + 1]; // resulting maxes
        Deque<Integer> q = new ArrayDeque<>(); // array deque of always decreasing values corresponding with the indexes in nums
        
        // iterate over the numbers
        for (int i = 0; i < n; i++) {
            if (!q.isEmpty() && q.peekFirst() < (i - k + 1)) q.pollFirst(); // shrink window so it is always 'k' size based on the lowest index in the queue
            
            while (!q.isEmpty() && nums[q.peekLast()] <= nums[i]) q.pollLast(); // essentially we are ensuring the queue is always decreasing, we are trying to put nums[i] in the queue where it is in proper decreased order; numbers smaller than nums[i] are irrelevant to this problem
            
            q.offer(i); // always add our current index
            
            if (i - k + 1 >= 0) res[i - k + 1] = nums[q.peekFirst()]; // once we reach a size k window, we will always add the current max in from the deque (the start)
        }
        
        return res;
    }
    
    public int[] maxSlidingWindowSubOptimal(int[] nums, int k) {
        if(k == 1) return nums;
        if(k == 0) return new int[0];
        List<Integer> res = new ArrayList<>();
        
        int wstart = 0, wend = 0, max = Integer.MIN_VALUE;
        // initialize max in sliding window
        while(wend < k) 
            max = Math.max(max, nums[wend++]);
        wend = k - 1; // put wend on the max window size so far
        
        // loop through
        while(wend < nums.length){           
            // add the max each new window
            if(wend - wstart + 1 == k) res.add(max);
    
            // update the window end
            // update max in case we found a larger one
            wend++;
            if(wend < nums.length && nums[wend] > max) max = nums[wend];
            
            // break if we fall off the end
            if(wend >= nums.length) break;
            
            // shrink the window if too large
            if(wend - wstart + 1 > k) {
                if(nums[wstart] == max){
                    wstart++;
                    max = Integer.MIN_VALUE; // find new max if we drop our old one
                    for(int i = wstart; i <= wend; i++)
                        max = Math.max(max, nums[i]);
                }
                else wstart++;
            }
        }
        
        int[] ret = new int[res.size()];
        for(int i = 0; i < res.size(); i++)
            ret[i] = res.get(i);
        
        return ret;
    }
}
