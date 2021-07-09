/* Noah Park

The pair sum of a pair (a,b) is equal to a + b. The maximum pair sum is the largest pair sum in a list of pairs.

For example, if we have pairs (1,5), (2,3), and (4,4), the maximum pair sum would be max(1+5, 2+3, 4+4) = max(6, 5, 8) = 8.
Given an array nums of even length n, pair up the elements of nums into n / 2 pairs such that:

Each element of nums is in exactly one pair, and
The maximum pair sum is minimized.
Return the minimized maximum pair sum after optimally pairing up the elements.

*/

class Solution {
    
    // Intuition: Counting sort to sort in linear time. Then two pointer scan to minimize the maximum while maintaining the largest value.
    // Time: O(n) two passes.
    // Space: O(1) constant.
    public int minPairSum(int[] nums) {
        int n = nums.length, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, res = Integer.MIN_VALUE;
        int[] map = new int[100001];
        
        for (int num : nums) {
            map[num]++;
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        int l = min, r = max;
        while (l <= r) {
            res = Math.max(res, l + r);
            if (--map[l] == 0)
                while (l <= r && map[l] == 0) l++;
            if (--map[r] == 0)
                while (l <= r && map[r] == 0) r--;
        }
        
        return res;
    }
    
    // Intuition: Sort the array to optimally pair the largest with the smallest values. Find the largest of these optimal pairs.
    // Time: O(nlogn) for sorting.
    // Space: O(sort) for sorting.
    public int minPairSum2(int[] nums) {
        Arrays.sort(nums);
        
        int l = 0, r = nums.length - 1, max = Integer.MIN_VALUE;
        while (l < r) 
            max = Math.max(max, nums[l++] + nums[r--]);
        
        return max;
    }
}
