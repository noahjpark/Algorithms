/* Noah Park

Given a string s and an integer array indices of the same length.

The string s will be shuffled such that the character at the ith position moves to indices[i] in the shuffled string.

Return the shuffled string.

*/

class Solution {
    public String restoreString(String s, int[] indices) {
        if (s == null || s.length() == 0) return s; // edge cases
        
        // store the characters from s in their appropriate shuffled indices using a temporary character array
        char[] res = new char[s.length()];
        int i = 0;
        
        // shuffle the stuff
        for (int index : indices) 
            res[index] = s.charAt(i++);
        
        // return the shuffled characters as a string
        return new String(res);
    }
}
