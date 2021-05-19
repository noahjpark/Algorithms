/* Noah Park

Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.

In one move, you can increment or decrement an element of the array by 1.

*/

class Solution {
    
    // Intuition: Greedy solution based on the intuition that the smallest and largest numbers must become the next smallest/largest numbers in order to minimize moves. I'm now realizing it doesn't even have to be as complicated as this. This is the equivalent of adding the difference between the two pointers.
    // Time: O(nlogn) for sorting.
    // Space: O(sort).
    public int minMoves2(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;
        
        Arrays.sort(nums);
        int p1 = 0, p2 = nums.length - 1, res = 0;
        
        for (; p1 < p2; p1++, p2--) {           
            if (p1 + 1 == p2) res += (nums[p2] - nums[p1])*(p1 + 1);
            else res += (nums[p1 + 1] - nums[p1])*(p1 + 1) + (nums[p2] - nums[p2 - 1])*(p1 + 1);
        }
        
        return res;
    }
}
