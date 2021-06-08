/* Noah Park

Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].

Return true if there is a 132 pattern in nums, otherwise, return false.

*/

class Solution {
    
    // Intuition: Utilize a monotonically decreasing stack to maintain the middle value. When we find a larger value than our stack values, we can maximize the middle value until the stack is empty. If we reach a value that is smaller than the maximized value we have a 132 pattern.
    // Time: O(n) to iterate over nums.
    // Space: O(n) for the stack.
    public boolean find132pattern(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        
        int n = nums.length, max = Integer.MIN_VALUE;
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.getLast() < nums[i]) max = stack.removeLast();
            if (nums[i] > max) stack.addLast(nums[i]);
            if (nums[i] < max) return true;
        }
        
        return false;
    }
    
    // Intuition: TLE simulation going from the largest number and expanding outwards at each point.
    // Time: O(n^2log(n)) to expand while using the heap.
    // Space: O(n) for the heap.
    public boolean find132pattern2(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        
        int n = nums.length;
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> (b[1] - a[1]));
        
        for (int i = 0; i < n; i++) 
            maxHeap.offer(new int[]{ i, nums[i] });
        
        while (!maxHeap.isEmpty()) {
            int[] cur = maxHeap.poll();
            int maxIdx = cur[0], leftIdx = Integer.MAX_VALUE, rightIdx = Integer.MIN_VALUE;
            
            for (int i = maxIdx - 1; i >= 0; i--) 
                if (leftIdx == Integer.MAX_VALUE || nums[leftIdx] > nums[i]) leftIdx = i; // find the smallest value on the left side
            if (leftIdx == Integer.MAX_VALUE) continue;
            
            for (int i = maxIdx + 1; i < n; i++)
                if (rightIdx == Integer.MIN_VALUE || (nums[rightIdx] < nums[i] && nums[i] < nums[maxIdx])) rightIdx = i; // find largest value on the right side up to the maximum index (not inclusive)
            if (rightIdx == Integer.MIN_VALUE) continue;
            
            int i = nums[leftIdx], j = nums[maxIdx], k = nums[rightIdx];
            if (i < j && k < j && i < k) return true;
        }
        
        return false;
    }
}
