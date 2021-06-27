/* Noah Park

You are given a sorted unique integer array nums.

Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.

Each range [a,b] in the list should be output as:

"a->b" if a != b
"a" if a == b

*/

class Solution {
    
    // Intuition: Keep track of each range of adjacent queries where consecutive elements are one away from one another. StringBuilder helps us utilize the fastest time possible. If end is greater than start, tack on the ->end portion. Otherwise, we add the StringBuilder value to the return list.
    // Time: O(n) to iterate over nums.
    // Space: O(n) for the resulting list.
    public List<String> summaryRanges(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        
        List<String> res = new ArrayList<>();
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            StringBuilder temp = new StringBuilder();
            int start = nums[i], end = nums[i];
            temp.append(start);
            
            while (i + 1 < n && nums[i + 1] - nums[i] == 1) 
                end = nums[++i];
            
            if (end > start) temp.append("->").append(end);
            res.add(temp.toString());
        }
        
        return res;
    }
    
}
