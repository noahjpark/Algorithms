/* Noah Park

Given an array of integers nums.

A pair (i,j) is called good if nums[i] == nums[j] and i < j.

Return the number of good pairs.

*/

class Solution {
    public int numIdenticalPairs(int[] nums) {
        int count = 0;
        int[] freq = new int[101]; // nums can have a value as large as 100
        for(int i = 0; i < nums.length; i++)
            count += freq[nums[i]]++;
        return count;
    }
}
