/* Noah Park

Given a string s, return the longest palindromic substring in s.

*/

class Solution {
    
    // Intuition: Utilize an indices array to maintain the maximum size start and end index difference for the substring. Expand from each point in the string considering both odd and even cases. If we find a longer difference, update the indices array. The first index in indices is inclusive, the second is exclusive since that is how substring works.
    // Time: O(n^2) since we expand on each iteration through s.
    // Space: O(1) constant.
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return s;
        
        int[] indices = new int[]{ -1, -1 };
        
        for (int i = 0; i < s.length(); i++) {
            int[] oddPal = expand(s, i, i), evenPal = expand(s, i, i + 1);
            //System.out.println(oddPal[0] + " " + oddPal[1] + " " + evenPal[0] + " " + evenPal[1]);
            int[] difference = Math.abs(oddPal[0] - oddPal[1]) > Math.abs(evenPal[0] - evenPal[1]) ? oddPal : evenPal;
            
            if (Math.abs(difference[0] - difference[1]) > Math.abs(indices[0] - indices[1])) indices = difference;
        }
        
        return indices[0] == -1 ? null : s.substring(indices[0], indices[1]);
    }
    
    public int[] expand(String s, int i, int j){
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) { i--; j++; }
        return new int[]{ i + 1, j };
    }
}
