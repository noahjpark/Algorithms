/* Noah Park

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

*/

class Solution {
    // Time: O(n*m) where n is the number of words in the list and m is the length of the longer input word
    // Space: O(1)
    public int shortestDistance(String[] words, String word1, String word2) {
        int start = -1, end = -1, distance = Integer.MAX_VALUE;
        
        // each time we find a new word1 or word2 match, update the indices
        // always attempt to find the new minimum value from the current distance
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) start = i;
            else if (words[i].equals(word2)) end = i;
            
            if (end != -1 && start != -1) distance = Math.min(Math.abs(end - start), distance);
        }
        
        return distance;
    }
}
