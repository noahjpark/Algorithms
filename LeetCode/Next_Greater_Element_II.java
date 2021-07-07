/* Noah Park

Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.

The next greater number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.

*/

class Solution {
    
    // Intuition: Utilize a monotonically decreasing stack. This way, whenever we find a good element we can pop elements off the stack and update the result. We can avoid the issue of wrap around by iterating over the nums array twice.
    // Time: O(n) two passes over nums.
    // Space: O(n) for the stack and resulting list.
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) return new int[]{};
        
        int n = nums.length;
        int[] res = new int[n];
        Deque<int[]> s = new LinkedList<>();
        
        for (int i = 0; i < n * 2 + 1; i++) {
            if (s.isEmpty() && i < n) s.addLast(new int[]{ nums[i], i });
            else {
                while (!s.isEmpty() && nums[i % n] > s.getLast()[0]) {
                    int[] cur = s.removeLast();
                    res[cur[1]] = nums[i % n];
                }
                
                if (i < n) s.addLast(new int[]{ nums[i], i });
            }
        }
        
        while (!s.isEmpty()) {
            int[] cur = s.removeLast();
            res[cur[1]] = -1;
        }
        
        return res;
    }
}
