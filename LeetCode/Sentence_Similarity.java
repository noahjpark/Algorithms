/* Noah Park

We can represent a sentence as an array of words, for example, the sentence "I am happy with leetcode" can be represented as arr = ["I","am",happy","with","leetcode"].

Given two sentences sentence1 and sentence2 each represented as a string array and given an array of string pairs similarPairs where similarPairs[i] = [xi, yi] indicates that the two words xi and yi are similar.

Return true if sentence1 and sentence2 are similar, or false if they are not similar.

Two sentences are similar if:

They have the same length (i.e. the same number of words)
sentence1[i] and sentence2[i] are similar.
Notice that a word is always similar to itself, also notice that the similarity relation is not transitive. For example, if the words a and b are similar and the words b and c are similar, a and c are not necessarily similar.

*/

class Solution {
    
    // Intuition: Map all similar words from the string list. We need to store them in a set for quick access though as one word could have many words it is similar with.
    // Time: O(n + m) where n is the length of the similar pairs list and m is the length of sentence 1.
    // Space: O(n) where n is the length of the pairs list.
    public boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) return false;
        
        Map<String, Set<String>> map = new HashMap<>();
        
        for (List<String> words : similarPairs) {
            String s1 = words.get(0), s2 = words.get(1);
            
            if (!map.containsKey(s1)) map.put(s1, new HashSet<>());
            map.get(s1).add(s2);
        }
        
        for(int i = 0; i < sentence1.length; i++) {
            String s1 = sentence1[i], s2 = sentence2[i];
            
            if (s1.equals(s2) || map.getOrDefault(s1, new HashSet<>()).contains(s2) || map.getOrDefault(s2, new HashSet<>()).contains(s1)) continue;
            else return false;
        }
        
        return true;
    }
}
