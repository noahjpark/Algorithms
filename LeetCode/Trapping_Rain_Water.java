/* Noah Park

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

*/

class Solution {
    public int trap(int[] height) {
        if (height == null) return 0; // edge case
        
        // res is answer, left and right are pointers, lmax and rmax are the respective max heights we have seen so far from that direction
        int res = 0, n = height.length, left = 0, right = n - 1, lmax = 0, rmax = 0;
        
        // two pointer technique
        while (left < right) {
            // this means we can move left in one
            if (height[left] < height[right]) {
                if (height[left] >= lmax) lmax = height[left]; // we have a new left height
                else res += lmax - height[left];               // there was a larger height from the left and this is water now. subtract height[left] because there might be obstacle
                left++; // move left
            }
            else { // same process for the right side
                if (height[right] >= rmax) rmax = height[right];
                else res += rmax - height[right];
                right--;
            }
        }
        
        return res;
    }
    
    // O(n) time and space
    public int trapDP(int[] height) {
        if (height == null) return 0;
        
        int res = 0, n = height.length;
        int[] left = new int[n], right = new int[n]; // store all max heights from the left and right
        
        left[0] = height[0]; right[n - 1] = height[n - 1];
        for (int i = 1; i < n; i++) left[i] = Math.max(height[i], left[i - 1]); // store all max heights from the left
        for (int i = n - 2; i >= 0; i--) right[i] = Math.max(height[i], right[i + 1]); // store all max heights from the right
        
        for (int i = 1; i < n - 1; i++) res += Math.min(left[i], right[i]) - height[i]; // take the overlap of the two
        
        return res;
    }
}
