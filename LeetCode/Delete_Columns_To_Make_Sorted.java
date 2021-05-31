/* Noah Park

You are given an array of n strings strs, all of the same length.

The strings can be arranged such that there is one on each line, making a grid. For example, strs = ["abc", "bce", "cae"] can be arranged as:

abc
bce
cae
You want to delete the columns that are not sorted lexicographically. In the above example (0-indexed), columns 0 ('a', 'b', 'c') and 2 ('c', 'e', 'e') are sorted while column 1 ('b', 'c', 'a') is not, so you would delete column 1.

Return the number of columns that you will delete.

*/

class Solution {
    
    // Intuition: Check each column of strings and remove all that are not sorted.
    // Time: O(n*m) to iterate over all characters in each word.
    // Space: O(1) constant.
    public int minDeletionSize(String[] strs) {
        if (strs == null || strs.length == 0) return 0;
        
        int n = strs.length, m = strs[0].length(), res = 0;
        
        for (int i = 0; i < m; i++) {
            boolean rem = false;
            
            for (int j = 1; j < n; j++) {
                if (strs[j].charAt(i) < strs[j - 1].charAt(i)) { rem = true; break; }
            }
            
            if (rem) res++;
        }
        
        return res;
    }
}
