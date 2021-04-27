/* Noah Park

Given a string s, find the length of the longest substring without repeating characters.

*/

class Solution {
    
    // Intuition: Utilize counting sort since we know it has spaces, digits, symbols and letters. Spaces are the smallest of them so they are our baseline.
    // Time: O(n) to iterate over s.
    // Space: O(1) constant.
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        
        int[] freq = new int[128];
        int start = 0, end = 0, longest = 0;
        
        while (end < s.length()) {
            char c = s.charAt(end);
            freq[c - ' ']++;
            
            while (freq[c - ' '] > 1) 
                freq[s.charAt(start++) - ' ']--;
            
            longest = Math.max(longest, end - start + 1);
            end++;
        }
        
        return longest;
    }
    
    // Map all characters to their frequencies. Apply sliding window to bring in characters and remove them once we have repeating ones.
    // Time: O(n) to iterate over s.
    // Space: O(n) to store each character.
    public int lengthOfLongestSubstring2(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int wstart = 0, wend = 0;
        int longest = 0;
        
        while(wend < s.length()){
            char c = s.charAt(wend);
            map.put(c, map.getOrDefault(c, 0) + 1);
            
            while(map.get(c) > 1){
                map.replace(s.charAt(wstart), map.get(s.charAt(wstart)) - 1);
                wstart++;
            }
         
            longest = Math.max(longest, wend - wstart + 1);
            
            wend++;
        }
        
        return longest;
    }
}
