/* Noah Park

Given a binary array, find the maximum number of consecutive 1s in this array.

*/

class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int temp = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 1) temp++;
            else{
                count = Math.max(temp, count);
                temp = 0;
            }
        }
        count = Math.max(temp, count);
        return count;
    }
}
