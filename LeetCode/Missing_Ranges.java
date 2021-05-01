/* Noah Park

You are given an inclusive range [lower, upper] and a sorted unique integer array nums, where all elements are in the inclusive range.

A number x is considered missing if x is in the range [lower, upper] and x is not in nums.

Return the smallest sorted list of ranges that cover every missing number exactly. That is, no element of nums is in any of the ranges, and each missing number is in one of the ranges.

Each range [a,b] in the list should be output as:

"a->b" if a != b
"a" if a == b

*/

class Solution {
    
    // Intuition: More concise than the second but does the same thing without a stringbuilder and uses a helper function.
    // Time: O(n) to iterate over ranges.
    // Space: O(n) for the output list.
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        
        if (nums.length == 0) { res.add(getRange(lower, upper)); return res; } // empty edge case
        
        if (lower != nums[0]) res.add(getRange(lower, nums[0] - 1)); // before start
        
        for (int i = 0; i < nums.length - 1; i++) {
            int first = nums[i] + 1, second = nums[i + 1] - 1;
            if (first > second) continue;
            res.add(getRange(first, second));
        }
        
        if (upper != nums[nums.length - 1]) res.add(getRange(nums[nums.length - 1] + 1, upper)); // after end
        
        return res;
    }
    
    // Intuition: Utilize a stringbuilder to get the ranges. Cases: Empty nums, before first num, each range, after last num.
    // Time: O(n) to iterate over ranges.
    // Space: O(n) for the output list.
    public List<String> findMissingRanges2(int[] nums, int lower, int upper) {
        StringBuilder s = new StringBuilder();
        List<String> res = new ArrayList<>();
        
        if (nums.length == 0) {
            s.append(lower == upper ? lower : lower + "->" + upper);
            res.add(s.toString());
            return res;
        } 
        
        if (lower != nums[0]) {
            s.append(lower == nums[0] - 1 ? lower : lower + "->" + (nums[0] - 1));
            res.add(s.toString());
            s = new StringBuilder();
        }
        
        for (int i = 0; i < nums.length - 1; i++) {
            int first = nums[i] + 1, second = nums[i + 1] - 1;
            
            if (first > second) continue;
            
            s.append(first == second ? first : first + "->" + second);
            res.add(s.toString());
            s = new StringBuilder();
        }
        
        if (upper != nums[nums.length - 1]) {
            s.append(upper == nums[nums.length - 1] + 1 ? upper : (nums[nums.length - 1] + 1) + "->" + upper);
            res.add(s.toString());
        }
        
        return res;
    }
    
    public String getRange(int i, int j) {
        return i == j ? String.valueOf(i) : (i + "->" + j);
    }
}
