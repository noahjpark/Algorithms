/* Noah Park

Give a binary string s, return the number of non-empty substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.

Substrings that occur multiple times are counted the number of times they occur.

*/

class Solution {
    
    // Intuition: Sliding window of just enough size for all adjacent 0s then 1s or 1s then 0s. Each iteration, we remove the add the next set of 1s or 0s that we didn't have then reset the front.
    // Time: O(n) to iterate over s.
    // Space: O(n) to maintain character array for speed.
    public int countBinarySubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int e = 0, n = s.length(), zcount = 0, ocount = 0, res = 0;
        char[] c = s.toCharArray();
        
        while (e < n && c[e] == c[0]) {
            if (c[e++] == '0') zcount++;
            else ocount++;
        }
        
        while (e < n) {
            int idx = e;
            
            while (e < n && c[e] == c[idx]) {
                if (c[e++] == '0') zcount++;
                else ocount++;
            }
            
            res += Math.min(zcount, ocount);
            
            if (e < n && c[e] == '0') zcount = 0;
            else ocount = 0;
        }
        
        return res;
    }
}
