/* Noah Park

You are given a string s, a split is called good if you can split s into 2 non-empty strings p and q where its concatenation is equal to s and the number of distinct letters in p and q are the same.

Return the number of good splits you can make in s.

*/

class Solution {
    
    // Intuition: Utilize a frequency "map" of both the left and right substrings. Initially populate the right with all letters, left stays empty. A second pass will make considerations at each point. Each character we consider gets added to left and removed from right. I maitain the number of distinct in both the left and right for easy computation and update based on the value that is there before incrementing/decrementing the maps. Good ways is incremented each time l and r match.
    public int numSplits(String s) {
        int[] left = new int[26], right = new int[26];
        int good = 0, l = 0, r = 0;
        
        for (char c : s.toCharArray())
            if (right[c - 'a']++ == 0) r++;
                                                   
        for (char c : s.toCharArray()) {
            if (left[c - 'a']++ == 0) l++;
            if (right[c - 'a']-- == 1) r--;
            if (l == r) good++;
        }
        
        return good;
    }
}
