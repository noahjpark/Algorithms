/* Noah Park

Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.

In one move, you can increment n - 1 elements of the array by 1.

*/

class Solution {
    
    // Intuition: Instead of attempting to build the smallest elements to the largest, work backwards. In this way, the problem can be framed as, decrement 1 element in each move. Now the problem is simply finding the difference between each element and the smallest element in nums.
    // Time: O(n) two passes.
    // Space: O(1) constant.
    public int minMoves(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int min = Integer.MAX_VALUE, res = 0;
        for (int num : nums) 
            min = Math.min(min, num);
        
        for (int num : nums) 
            res += (num - min);
        
        return res;
    }
}
