/* Noah Park

Given a string paragraph and a string array of the banned words banned, return the most frequent word that is not banned. It is guaranteed there is at least one word that is not banned, and that the answer is unique.

The words in paragraph are case-insensitive and the answer should be returned in lowercase.

*/

class Solution {
    
    // Intuition: Maintain the banned words in a set for quick access checking. Otherwise, we need to take each word and store the number of times we see them. This is a simple loop over paragraph with a map but we need to take into account the weird punctuation in the paragraph. Anytime we meet a non-character value, this means we finished building our last word (buffer of 1 extra iteration at the end to ensure we don't miss the last word). If the number of occurrences from the map is larger than the current number of times we have seen our current result word, we can update it.
    // Time: O(n + m) to iterate over the paragraph and iterate over banned.
    // Space: O(n + m) to maintain the entirety of the paragraph and banned.
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> set = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        String res = "";
        StringBuilder temp = new StringBuilder();
        int n = paragraph.length(), resLen = 0;
        char[] c = paragraph.toCharArray();
        
        for (String ban : banned) set.add(ban);
    
        for (int i = 0; i < n + 1; i++) {
            if (i < n && letter(c[i])) temp.append(c[i]);
            else {
                if (temp.length() > 0) {
                    String s = temp.toString().toLowerCase();
                    
                    if (!set.contains(s)) {
                        int val = map.getOrDefault(s, 0) + 1;
                        map.put(s, val);
                        if (val > resLen) { 
                            resLen = val;
                            res = s;
                        }
                    }
                    
                    temp = new StringBuilder();
                }
            }
        }
        
        return res;
    }
    
    public boolean letter(char c) {
        String bad = " !?',;.";
        for (char ch : bad.toCharArray())
            if (c == ch) return false;
        return true;
    }
}
