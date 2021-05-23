/* Noah Park

Given an array of strings words, return true if it forms a valid word square.

A sequence of strings forms a valid word square if the kth row and column read the same string, where 0 <= k < max(numRows, numColumns).

*/

class Solution {
    
    // Intuition: Comparisons at each square point. If the current word length is larger than the size, it isn't a possible word. Otherwise we iterate through the smaller of the size or the length of the word. If the current row index is larger than the word's length it doesn't match, otherwise we check to see if the associated characters match and return false if not. 
    // Time: O(n*m) to iterate over all characters in each word.
    // Space: O(1) constant.
    public boolean validWordSquare(List<String> words) {
        int size = words.size();
        
        for (int i = 0; i < size; i++) {
            if (words.get(i).length() > words.size()) return false;
            
            for (int j = 0; j < Math.min(size, words.get(i).length()); j++) 
                if (i >= words.get(j).length() || words.get(i).charAt(j) != words.get(j).charAt(i)) return false;
        }
        
        return true;
    }
}
