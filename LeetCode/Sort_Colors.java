/* Noah Park

Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, 
with the colors in the order red, white, and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

*/

class Solution {
    public void sortColors(int[] nums) {
        if(nums == null || nums.length <= 1) return;
                
        int left = 0, right = nums.length - 1;
        
        for(int i = 0; i <= right;){
            if(nums[i] == 0){
                swap(nums, i++, left++);
            }
            else if(nums[i] == 1) i++;
            else if(nums[i] == 2){
                swap(nums, i, right--);
            }
        }
    }
    
    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
