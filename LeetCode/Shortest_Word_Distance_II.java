/* Noah Park

Design a data structure that will be initialized with a string array, and then it should answer queries of the shortest distance between two different strings from the array.

Implement the WordDistance class:

WordDistance(String[] wordsDict) initializes the object with the strings array wordsDict.
int shortest(String word1, String word2) returns the shortest distance between word1 and word2 in the array wordsDict

*/

class WordDistance {
    
    // Intuition: I initially did the processing in the shortest method. This resulted in a timeout since we were always iterating over the entirety of the dictionary for each call. Instead, I opted to process all words to a map of their indices from left to right (sorted naturally). This way, the shortest method can simply utilize a two pointer technique to minimize the cost of the distances.
    // Time: O(n) to iterate over the indices and to process the initial map.
    // Space: O(n) to maintain the dictionary and map.
    Map<String, List<Integer>> map = new HashMap<>();
    String[] d;
    int n;

    public WordDistance(String[] wordsDict) {
        d = wordsDict;
        n = d.length;
        int i = 0;
        for (String word : wordsDict) {
            if (!map.containsKey(word)) map.put(word, new ArrayList<>());
            map.get(word).add(i++);
        }
    }
    
    public int shortest(String word1, String word2) {
        List<Integer> i1 = map.get(word1), i2 = map.get(word2);
        int min = n, p1 = 0, p2 = 0;
        
        while (p1 < i1.size() && p2 < i2.size()) {
            int v1 = i1.get(p1), v2 = i2.get(p2);
            if (v1 > v2) { min = Math.min(min, v1 - v2); p2++; }
            else { min = Math.min(min, v2 - v1); p1++; }
        }
        
        return min;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(wordsDict);
 * int param_1 = obj.shortest(word1,word2);
 */
