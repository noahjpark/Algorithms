/* Noah Park

Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

*/

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        // solution using hashmaps
//         List<Integer> res = new ArrayList<>();
//         Map<Character, Integer> freq = new HashMap<>();
//         for(char c : p.toCharArray())
//             freq.put(c, freq.getOrDefault(c, 0) + 1);
        
//         int wstart = 0, wend = 0, n = p.length(), letters = 0;
//         Map<Character, Integer> match = new HashMap<>();
        
//         while(wend < s.length()){
//             char c = s.charAt(wend);
//             if(freq.containsKey(c)){
//                 match.put(c, match.getOrDefault(c, 0) + 1);
//                 if(freq.get(c) >= match.get(c)) letters++;
//             }
            
//             if(wend - wstart + 1 == n){
//                 if(match.size() == freq.size() && letters == n) res.add(wstart);
//                 char ch = s.charAt(wstart++);
//                 if(match.containsKey(ch)) {
//                     match.put(ch, match.get(ch) - 1);
//                     if(match.get(ch) < freq.get(ch)) letters--;
//                     if(match.get(ch) == 0) match.remove(ch);
//                 }
//             }
            
//             wend++;
//         }
        
        
        
//         return res;
        
        // solution with arrays
        List<Integer> res = new ArrayList<>();
        int[] freq = new int[26], match = new int[26]; // freq stores p's letters, match will store the windows letters in s
        for(char c : p.toCharArray())
            freq[c - 'a']++;
        
        // window bounds and string bounds
        int wstart = 0, wend = 0, n = p.length(), k = s.length();
        
        // iterate over s
        while(wend < k){
            char c = s.charAt(wend);
            match[c - 'a']++; // add the end character of the window
            
            if(wend - wstart + 1 > n) match[s.charAt(wstart++) - 'a']--; // shrink the window when it grows too large
            
            if(Arrays.equals(freq, match)) res.add(wstart); // if the arrays match, the window is an anagram and we add to the output
            
            wend++;
        }
        
        return res;
    }
}
