/* Noah Park

The product difference between two pairs (a, b) and (c, d) is defined as (a * b) - (c * d).

For example, the product difference between (5, 6) and (2, 7) is (5 * 6) - (2 * 7) = 16.
Given an integer array nums, choose four distinct indices w, x, y, and z such that the product difference between pairs (nums[w], nums[x]) and (nums[y], nums[z]) is maximized.

Return the maximum such product difference.

*/

class Solution {
    
    // Intuition: Maintain four pointers as we iterate over nums once.
    // Time: O(n) to iterate over nums.
    // Space: O(1) constant.
    public int maxProductDifference(int[] nums) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE, max1 = 0, max2 = 0;
        
        for (int num : nums) {
            if (num < min1) {
                min1 = num;
                
                if (min1 < min2) {
                    int temp = min1;
                    min1 = min2;
                    min2 = temp;
                }
            }
            
            if (num > max1) {
                max1 = num;
                
                if (max1 > max2) {
                    int temp = max1;
                    max1 = max2;
                    max2 = temp;
                }
            }
        }
        
        return max1 * max2 - min1 * min2;
    }
    
    // Intuition: Sort the array to easily take the smallest and largest numbers in nums to create the maximum product difference.
    // Time: O(nlogn) for sorting.
    // Space: O(sort) for sorting.
    public int maxProductDifference2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        return nums[n - 1]*nums[n - 2] - nums[0]*nums[1];
    }
}
