/* Noah Park

Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

*/

class Solution {
    public void wiggleSort(int[] nums) {
        // iterate through based on the condition and always swap if the condition is not met at a partcular index even or odd
        for (int i = 0; i < nums.length - 1; i++) {
            if ((i % 2 == 0 && nums[i] > nums[i + 1]) || (i % 2 == 1 && nums[i] < nums[i + 1])) swap(nums, i, i + 1);
        }
    }
    
    // swaps the indexes i and j
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    // sort the array then swap every odd and even index to ensure the condition is met
    public void wiggleSortSubOptimal(int[] nums) {
        Arrays.sort(nums);
        
        for (int i = 1; i < nums.length - 1; i += 2)
            swap(nums, i, i + 1);
    }
    
    // copies array over to another one, sorts then uses two pointers to swap nums
    public void wiggleSortInitial(int[] nums) {
        int[] freq = new int[nums.length];
        
        for (int i = 0; i < nums.length; i++) 
            freq[i] = nums[i];
        
        Arrays.sort(freq);
        
        int p1 = 0, p2 = nums.length - 1, i = 0;
        
        while (i < nums.length) {
            nums[i++] = freq[p1++];
            if (i < nums.length) nums[i++] = freq[p2--];
        }
    }
}
