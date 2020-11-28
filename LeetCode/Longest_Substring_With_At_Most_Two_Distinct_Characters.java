/* Noah Park

Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.

*/

class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        // Store window characters in hash map to get the size of the hashmap for the distinct characters
        Map<Character, Integer> freq = new HashMap<>();
        int wstart = 0, wend = 0, count = 0, n = s.length();
        
        // iterate over characters in s
        while(wend < n){
            // add to the map
            char c = s.charAt(wend);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
            
            // if there are more than 2 characters in the map, shrink the window and remove from the map
            while(freq.size() > 2){
                char ch = s.charAt(wstart++);
                freq.put(ch, freq.get(ch) - 1);
                if(freq.get(ch) == 0) freq.remove(ch);
            }
            
            // count will be the largest value window we see so far
            count = Math.max(count, wend - wstart + 1);
            
            wend++;
        }
        
        return count;
    }
}
