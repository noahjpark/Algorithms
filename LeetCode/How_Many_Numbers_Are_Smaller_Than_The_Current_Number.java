/* Noah Park

Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it. 

That is, for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].

Return the answer in an array.

*/

class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] counts = new int[102]; // nums[i] has a range of [0,100]
        int[] smaller = new int[nums.length];
        
        for(int i = 0; i < nums.length; i++)
            counts[nums[i] + 1]++; // Update the count for the next value by one which can be used for all other values larger than it.
        
        for(int i = 1; i < 101; i++)
            counts[i] += counts[i - 1]; // Update the running counts for each one so that we can update the smaller array
        
        for(int i = 0; i < nums.length; i++)
            smaller[i] = counts[nums[i]];
        
        return smaller;
    }
}
