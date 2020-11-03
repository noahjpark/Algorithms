/* Noah Park

Given two strings s and t, return the minimum window in s which will contain all the characters in t. If there is no such window in s that covers all characters in t, 
return the empty string "".

Note that If there is such a window, it is guaranteed that there will always be only one unique minimum window in s.

 

*/

class Solution {
    public String minWindow(String s, String t) {
        int wstart = 0, wend = 0;
        int window = Integer.MAX_VALUE;
        int start = -1, end = -1;
        int match = 0;
        HashMap<Character, Integer> freq = new HashMap<>();
        
        for(char c : t.toCharArray())
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        
        while(wend < s.length()){
            char c = s.charAt(wend);
            if(freq.containsKey(c)){
                freq.put(c, freq.get(c) - 1);
                if(freq.get(c) == 0) match++;
            }
            
            while(match >= freq.size()){
                if(wend - wstart + 1 < window){
                    window = Math.min(window, wend - wstart + 1);
                    start = wstart;
                    end = wend;
                }
                char remove = s.charAt(wstart++);
                if(freq.containsKey(remove)){
                    freq.put(remove, freq.get(remove) + 1);
                    if(freq.get(remove) > 0) match--;
                }
            }
            
            wend++;
        }
        
        return start == -1 ? "" : s.substring(start, end + 1);
    }
}
