/* Noah Park

You are given an array of strings words and a string chars.

A string is good if it can be formed by characters from chars (each character can only be used once).

Return the sum of lengths of all good strings in words.

*/

class Solution {
    public int countCharacters(String[] words, String chars) {
        int[] freq = new int[26];
        for(char c : chars.toCharArray())
            freq[c - 'a']++;
        
        int lengths = 0;
        for(String string : words){
            if(string.length() > chars.length()) continue; // optimization to skip if the word cannot be created due to number of letters
            int[] counts = new int[26];
            for(char c : string.toCharArray())
                counts[c - 'a']++;
            
            int letterCount = 0, matches = 0;
            for(int i = 0; i < 26; i++){
                if(counts[i] > 0){
                    letterCount++;
                    if(counts[i] <= freq[i]) matches++;
                    else break; // optimization to break if we cannot make the word due to not enough characters of a specific type
                }
            }
            
            if(letterCount == matches) lengths += string.length();
        }
        
        return lengths;
    }
}
