/* Noah Park

Given a string array words, return the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. If no such two words exist, return 0.

*/

class Solution {
    
    // Intuition: Brute force comparison of all possible combinations of words. Originally used character arrays to maintain the frequencies but a bitmask would be faster.
    // Time: O(n^2+n*m) to make comparisons and the mask array.
    // Space: O(n) to maintain n character arrays.
    public int maxProduct(String[] words) {
        int prod = 0, n = words.length;
        
        int[] map = new int[n];
        
        for (int i = 0; i < n; i++) {
            int mask = 0;
            for (char c : words[i].toCharArray()) 
                mask |= 1 << (c - 'a');
            map[i] = mask;
        }
        
        for (int i = 0; i < n; i++) 
            for (int j = i + 1; j < n; j++) 
                if ((map[i] & map[j]) == 0) prod = Math.max(prod, words[i].length()*words[j].length());
        
        return prod;
    }
}
