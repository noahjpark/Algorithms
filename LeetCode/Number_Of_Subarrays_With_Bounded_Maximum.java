/* Noah Park

We are given an array nums of positive integers, and two positive integers left and right (left <= right).

Return the number of (contiguous, non-empty) subarrays such that the value of the maximum array element in that subarray is at least left and at most right.

*/

class Solution {
    
    // Intuition: Another approach with constant space would be to count the subarrays that are from [0,R] and subtract the number that are from [0,L) to get the range of [L,R].
    // Time: O(n) to iterate over the nums.
    // Space: O(1) constant.
    public int numSubarrayBoundedMax(int[] nums, int l, int r) {
        return count(nums, r) - count(nums, l - 1);
    }
    
    public int count(int[] nums, int r) {
        int res = 0, cur = 0;
        
        for (int num : nums) {
            cur = (num <= r) ? cur + 1 : 0;
            res += cur;
        }
        
        return res;
    }
    
    // Intuition: Maintain all larger values than right in a list to know how many subarrays we can include of the current number. For values smaller than left, we need to maintain a multiplier that we can utilize when we choose a valid position as we could tack on that number of smaller numbers in front of all valid solutions for more valid subarrays.
    // Time: O(n) two passes.
    // Space: O(n) for the indices list.
    public int numSubarrayBoundedMax2(int[] nums, int left, int right) {
        if (nums == null || nums.length == 0) return 0;
        
        int res = 0, n = nums.length, j = 0, mul = 1;
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < n; i++) 
            if (nums[i] > right) indices.add(i);
        
        for (int i = 0; i < n; i++) {
            int nextLargest = j < indices.size() ? indices.get(j) : n;
            
            if (nums[i] >= left && nums[i] <= right) { res += ((nextLargest - i)*mul); mul = 1; }
            else if (i == nextLargest) { mul = 1; j++; }
            else if (nums[i] < left) mul++;
        }
        
        return res;
    }
}
