/* Noah Park

Given an array of string words. Return all strings in words which is substring of another word in any order. 

String words[i] is substring of words[j], if can be obtained removing some characters to left and/or right side of words[j].

*/

class Solution {
    
    // Intuition: Optimize comparisons by sorting the array by length, though this really doesn't improve the problem.
    // Time: O(n^2) to make comparisons.
    // Space: O(sort).
    public List<String> stringMatching(String[] words) {
        if (words == null || words.length == 0) return new ArrayList<>();
        
        List<String> res = new ArrayList<>();
        Arrays.sort(words, (a, b) -> (a.length() - b.length()));
        int n = words.length;
        
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (words[j].contains(words[i])) {
                    res.add(words[i]);
                    break;
                }
            }
        }
        
        return res;
    }
    
    // Intuition: Compare all strings.
    // Time: O(n^2) to make comparisons.
    // Space: O(1) constant.
    public List<String> stringMatching2(String[] words) {
        if (words == null || words.length == 0) return new ArrayList<>();
        
        List<String> res = new ArrayList<>();
        
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i != j && words[j].contains(words[i])) {
                    res.add(words[i]);
                    break;
                }
            }
        }
        
        return res;
    }
}
