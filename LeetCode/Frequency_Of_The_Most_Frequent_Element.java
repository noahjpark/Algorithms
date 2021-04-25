/* Noah Park

The frequency of an element is the number of times it occurs in an array.

You are given an integer array nums and an integer k. In one operation, you can choose an index of nums and increment the element at that index by 1.

Return the maximum possible frequency of an element after performing at most k operations.

*/

class Solution {
    
    // Intuition: Utilize sliding window since we are sorting. Keep track of the total value of all numbers added together and shrink the window when that total value becomes smaller than what the numbers add up to.
    // Time: O(n log n) for sorting.
    // Space: O(1) constant.
    public int maxFrequency(int[] nums, long k) {
        Arrays.sort(nums);
        int i = 0, j;
        
        for (j = 0; j < nums.length; j++) {
            k += nums[j];
            
            if (k < (long) (nums[j]) * (j - i + 1)) k -= nums[i++]; // if the total value after adding all increments is not large enough to match what all values would have to be at that point, we shrink the window.
        }
        
        return j - i;
     }
    
    // Intuition: Brute force from the back of the array after sorting, check if how many values you can increment to that point. Optimize to break if the frequency matches the current i + 1 value since that would mean all are able to be done and you can't find a larger frequency.
    // Time: O(n^2) checking all increments for each number.
    // Space: O(1) constant.
    public int maxFrequency2(int[] nums, int k) {
        Arrays.sort(nums);
        int freq = 1;
        
        for (int i = nums.length - 1; i > 0; i--) {
            int temp = k;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] - nums[j] > temp) break;
                else { freq = Math.max(i - j + 1, freq); temp -= nums[i] - nums[j]; }
            }
            if (freq == i + 1) break;
        }
    
        return freq;
    }
}
