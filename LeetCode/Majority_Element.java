/* Noah Park

Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

*/

class Solution {
    
    // Boyer-moore algorithm: Whenever we have a total of 0, we update the current number we consider the majority since we are equivalently at the start of a new problem. Otherwise, if we find an occurrence of our current number, we increment the count, else we decrement. In the end, the majority element will be our current candidate since it will have been counted the majority times.
    public int majorityElement(int[] nums) {
        int total = 0, cur = 0;
        
        for (int num : nums) {
            if (total == 0) cur = num;
            total += cur == num ? 1 : -1;
        }
        
        return cur;
    }
}
