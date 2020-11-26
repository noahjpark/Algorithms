/* Noah Park

Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.

Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)

*/

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        
        // slightly more inefficient using division
//         int prod = 1, zeroes = 0, zeroidx = 0;
//         for(int i = 0; i < n; i++){
//             if(nums[i] != 0) prod *= nums[i];
//             else { zeroes++; zeroidx = i; }
//         }
        
//         if(zeroes > 1) return res;
//         else if(zeroes == 1) { res[zeroidx] = prod; return res; }
        
//         for(int i = 0; i < n; i++){
//             prod /= nums[i];
//             res[i] = prod;
//             prod *= nums[i];
//         }
        
        // left and right are products of all values to the left/right of our current index
        int left = 1, right = 1;
        for(int i = 0; i < n; i++){
            if(i > 0) left *= nums[i - 1]; // accumulate from the left using the previous nums value
            res[i] = left; // store in result array
        }
        for(int i = n - 1; i > -1; i--){
            if(i < n - 1) right *= nums[i + 1]; // accumulate from the right using the following nums value
            res[i] *= right; // store in result array
        }
        
        return res;
    }
}
