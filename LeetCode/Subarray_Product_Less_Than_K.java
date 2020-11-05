/* Noah Park

Your are given an array of positive integers nums.

Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.

*/

class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int wstart = 0, wend = 0;
        int subarrays = 0;
        int product = 1;
        
        while(wend < nums.length){
            product *= nums[wend];
            
            while(wstart < nums.length && product >= k)
                product /= nums[wstart++];
            
            if(product < k) subarrays += wend - wstart + 1;
            
            wend++;
        }
        
        return subarrays;
    }
}
