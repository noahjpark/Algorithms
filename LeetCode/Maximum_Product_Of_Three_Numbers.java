/* Noah Park

Given an integer array nums, find three numbers whose product is maximum and return the maximum product.

*/

class Solution {
    public int maximumProduct(int[] nums) {
        // originally thought to sort then do an O(n^2) pointer solution, did not think about this to begin with.
        
        // the maximum product of three numbers in nums is either the largest 3 multiplied together OR the largest multiplied by the smallest two (due to large negatives).
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE, min1 = Integer.MAX_VALUE, min2 = Integer.MIN_VALUE;
        
        for(int num : nums){
            // Update largest values
            if(num > max1) { max3 = max2; max2 = max1; max1 = num; }
            else if(num > max2) { max3 = max2; max2 = num; }
            else if(num > max3) max3 = num;
            
            // Update smallest values
            if(num < min1) { min2 = min1; min1 = num; }
            else if(num < min2) min2 = num;
        }
        
        // return the product of the largest three values or the largest multiplied with the smallest
        return Math.max(max1 * max2 * max3, max1 * min1 * min2);
    }
}
