/* Noah Park

Given an array of strings wordsDict and two strings that already exist in the array word1 and word2, return the shortest distance between these two words in the list.

Note that word1 and word2 may be the same. It is guaranteed that they represent two individual words in the list.

*/

class Solution {
    
    // Intuition: Broke the problem into two defined parts: if word1 does not match word2 and if they are the same. If they are the same, check every pair in a single pass to minimize the difference between each position. If word1 does not match word2, maintain pointers to the most recent occurrence of each word and attempt to minimize the difference between each as we iterate over the dictionary. If the minimum difference is 1, we can break early. Otherwise, we continue to update each pointer that corresponds to that word if it matches the dictionary. We only minimize if p1/p2 are on a valid occurrence of the corresponding word.
    // Time: O(n) to iterate over the word dictionary.
    // Space: O(1) constant.
    public int shortestWordDistance(String[] wordsDict, String word1, String word2) {
        int n = wordsDict.length, min = n, p1 = -1, p2 = -1;
        
        if (word1.equals(word2)) {
            for (p1 = 0; p1 < n && !wordsDict[p1].equals(word1);) p1++;
            for (; p2 < n; p1 = p2) {
                for (p2 = p1 + 1; p2 < n && !wordsDict[p2].equals(word2);) p2++;
                if (p2 < n) min = Math.min(min, p2 - p1);
            }
        } else {
            for (int i = 0; i < n; i++) {
                if (min == 1) break;

                if (wordsDict[i].equals(word1)) p1 = i;
                if (wordsDict[i].equals(word2)) p2 = i;
                if (p1 != -1 && p2 != -1) min = Math.min(min, Math.abs(p1 - p2));
            }
        }
        
        return min;
    }
}
