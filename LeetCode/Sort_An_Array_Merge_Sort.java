/* Noah Park

Given an array of integers nums, sort the array in ascending order.

*/

class Solution {
    public int[] sortArray(int[] nums) {
        sort(nums, 0, nums.length - 1); // sort the array
        return nums;
    }

    public void sort(int[] nums, int l, int r) {
        if (l < r) { // continue until l >= r
            int mid = l + (r - l) / 2; // find mid point
            sort(nums, l, mid); // sort before to mid point
            sort(nums, mid + 1, r); // sort after mid point
            merge(nums, l, mid, r); // merge sorted arrays
        }
    }
    
    public void merge(int[] nums, int l, int mid, int r) {
        int n1 = mid - l + 1, n2 = r - mid; // get array sizes
        int[] left = new int[n1], right = new int[n2]; // initialize temp arrays
        
        // populate temp arrays
        for (int i = 0; i < n1; i++) left[i] = nums[i + l];
        for (int i = 0; i < n2; i++) right[i] = nums[i + mid + 1];
        
        // put the array info in sorted order
        int p1 = 0, p2 = 0, idx = l;
        while (p1 < n1 && p2 < n2) {
            if (left[p1] <= right[p2]) nums[idx++] = left[p1++];
            else nums[idx++] = right[p2++];
        }
        
        // recover straggling numbers
        while(p1 < n1) nums[idx++] = left[p1++];
        while(p2 < n2) nums[idx++] = right[p2++];
    }
}
