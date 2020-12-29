/* Noah Park

Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).

The replacement must be in place and use only constant extra memory.

*/

class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) return; // edge cases
        
        // get i to the first number (from the end) that is not in descending order from the front
        int i = nums.length - 2;
        while (i > -1 && nums[i] >= nums[i + 1]) i--;
        
        // if there is a valid number, swap the first larger number from the back with the i number
        if (i > -1) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }
        
        // finally reverse the rest of the array, if there were no valid numbers, this reverses the entire array
        rev(nums, i + 1, nums.length - 1);
    }
    
    // swaps the values at i and j
    public void swap(int nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    // reverses the array given the two pivots
    public void rev(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++; j--;
        }
    }
}
