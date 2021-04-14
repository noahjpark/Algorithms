/* Noah Park

Given an array of non-negative integers nums, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

*/

class Solution {
    public boolean canJumpBottomUp(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        
        int n = nums.length;
        Boolean[] mem = new Boolean[n];
        mem[n - 1] = true;
        
        for (int i = nums.length - 2; i >= 0; i--) {
            int jump = Math.min(i + nums[i], n - 1);
            for (int j = i + 1; j <= jump; j++) {
                if (mem[j] != null && mem[j]) {
                    mem[i] = true;
                    break;
                }
            }
        }
        
        return mem[0] != null && mem[0];
    }
    
    public boolean canJumpTopDown(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        Boolean[] mem = new Boolean[nums.length];
        return canJump(nums, 0, mem);
    }
    
    public boolean canJump(int[] nums, int idx, Boolean[] mem) {
        if (idx >= nums.length) return false;
        if (idx == nums.length - 1) return true;
        if (mem[idx] != null) return mem[idx];
        
        boolean jump = false;
        
        for (int i = 1; i <= nums[idx]; i++) 
            jump |= canJump(nums, idx + i, mem);
        
        mem[idx] = jump;
        return mem[idx];
    }
}
