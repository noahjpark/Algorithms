/* Noah Park

Given two arrays of integers nums and index. Your task is to create target array under the following rules:

Initially target array is empty.
From left to right read nums[i] and index[i], insert at index index[i] the value nums[i] in target array.
Repeat the previous step until there are no elements to read in nums and index.
Return the target array.

It is guaranteed that the insertion operations will be valid.

*/

class Solution {
    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            int val = nums[i], idx = index[i];
            ans.add(idx, val);
        }
        
        int[] res = new int[ans.size()];
        for(int i = 0; i < ans.size(); i++)
            res[i] = ans.get(i);
        
        return res;
    }
}
