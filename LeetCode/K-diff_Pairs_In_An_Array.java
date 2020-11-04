/* Noah Park

Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.

A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:

0 <= i, j < nums.length
i != j
|nums[i] - nums[j]| == k
Notice that |val| denotes the absolute value of val.

*/

class Solution {
    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        
        int i = 0, j = 0;
        int pairs = 0;
        
        while(i < nums.length) {
            j = Math.max(j, i + 1);
            while(j < nums.length && Math.abs(nums[j] - nums[i]) < k)
                j++;
            
            if (j < nums.length && Math.abs(nums[j] - nums[i]) == k) pairs++;
            
            while (i + 1 < nums.length && nums[i] == nums[i + 1]) 
                i++;
            
            i++;
        }
        
        return pairs;
    }
}
