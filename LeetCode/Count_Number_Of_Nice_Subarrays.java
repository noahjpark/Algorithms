/* Noah Park

Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.

Return the number of nice sub-arrays.

*/

class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int wstart = 0, wend = 0;
        int subarrays = 0;
        int match = 0;
        int val = 0;
        
        while(wend < nums.length){
            // Increase window using end pointer and val keeps track of the number of odd numbers we still need
            // Each time we increase, ensure that match is set to 0
            if(nums[wend] % 2 == 1) { val++; match = 0; }
            
            // Count all subarrays that have matching k odd numbers
            while(val == k){
                if(nums[wstart++] % 2 == 1) val--;
                match++;
            }
            subarrays += match;
            wend++;
        }
        
        return subarrays;
    }
}
