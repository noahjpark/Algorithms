/* Noah Park

Given a sorted array of integers nums and integer values a, b and c. Apply a quadratic function of the form f(x) = ax2 + bx + c to each element x in the array.

The returned array must be in sorted order.

Expected time complexity: O(n)

*/

class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        if (nums == null || nums.length == 0) return nums; // edge cases
        
        // resulting array and index based on the larger values
        // if a is >= 0, largest values are on the outside of nums, else they are the smallest
        int[] res = new int[nums.length];
        int idx = a >= 0 ? nums.length - 1 : 0, i = 0, j = nums.length - 1;
        
        // fill the array based on the larger values, or smaller, depending on the value of a
        while (i <= j) {
            if (a >= 0) res[idx--] = calculate(nums[i], a, b, c) >= calculate(nums[j], a, b, c) ? calculate(nums[i++], a, b, c) : calculate(nums[j--], a, b, c);
            else res[idx++] = calculate(nums[i], a, b, c) >= calculate(nums[j], a, b, c) ? calculate(nums[j--], a, b, c) : calculate(nums[i++], a, b, c);
        }
        
        return res;
    }
    
    public int calculate(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }
}
