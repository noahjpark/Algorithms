/* Noah Park

We are given a list nums of integers representing a list compressed with run-length encoding.

Consider each adjacent pair of elements [freq, val] = [nums[2*i], nums[2*i+1]] (with i >= 0).  For each such pair, there are freq elements with value val concatenated in a sublist. Concatenate all the sublists from left to right to generate the decompressed list.

Return the decompressed list.

 

*/

class Solution {
    public int[] decompressRLElist(int[] nums) {
        int size = 0;
        
        for(int i = 0; 2*i < nums.length; i++)
            size += nums[2*i];
        
        int[] ans = new int[size];
        
        int idx = 0;
        for(int i = 0; 2*i < nums.length; i++){
            // for(int j = 0; j < nums[2*i]; j++){
            //     ans[idx++] = nums[2*i+1];
            // }
            Arrays.fill(ans, idx, idx + nums[2*i], nums[2*i+1]);
            idx += nums[2*i];
        }
        
        return ans;
    }
}
