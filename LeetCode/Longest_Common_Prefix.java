/* Noah Park

Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

*/

class Solution {
    
    // Intuition: The vertical scanning solution. Utilize the first string as a baseline. As we go to each character in the first string, check if it matches the index of the corresponding index in all other strings. Uses constant space by the substring method.
    // Time: O(n * m) where n is the number of strings and m is the length of each string.
    // Space: O(1) constant.
    public String longestCommonPrefix(String[] strs) {
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != c) return strs[0].substring(0, i);
            }
        }
        
        return strs[0];
    }
    
    // Intuition: Map frequncies of each character at each index to a frequency map using counting sort.
    // Then build a string with the longest length.
    // Time: O(n * m) where n is the number of strings and m is the length of each string.
    // Space: O(m) constant frequency array with longest string potentially in string builder.
    public String longestCommonPrefix2(String[] strs) {
        int[][] freqs = new int[200][26];
    
        for (int i = 0; i < strs.length; i++) 
            for (int j = 0; j < strs[i].length(); j++) 
                freqs[j][strs[i].charAt(j) - 'a']++;
        
        StringBuilder s = new StringBuilder();
        
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 26; j++) {
                if (freqs[i][j] != 0 && freqs[i][j] != strs.length) return s.toString();
                if (freqs[i][j] == strs.length) s.append((char) (j + 'a'));
            }
        }
        
        
        return s.toString();
    }
}
