/* Noah Park

Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. 
In other words, one of the first string's permutations is the substring of the second string.

*/

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s2 == null || s2.length() < s1.length()) return false;
        
        Map<Character, Integer> freq = new HashMap<>();
        for(char c : s1.toCharArray())
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        
        int wstart = 0, wend = 0;
        int match = 0;
        
        while(wend < s2.length()){
            char c = s2.charAt(wend);
            if(freq.containsKey(c)) {
                freq.put(c, freq.get(c) - 1);
                if(freq.get(c) == 0) match++;
            }
            
            while(wend - wstart + 1 > s1.length()){
                char ch = s2.charAt(wstart++);
                if(freq.containsKey(ch)){
                    if(freq.get(ch) == 0) match--;
                    freq.put(ch, freq.get(ch) + 1);
                }
            }
            
            if(match == freq.size()) return true;
            wend++;
        }
        
        return false;
    }
}
