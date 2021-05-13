/* Noah Park

Given two strings s and t, determine if they are isomorphic.

Two strings s and t are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.

*/

class Solution {
    
    // Intuition: freq1 keeps track of if a character in s has been mapped. freq2 keeps track of if a character in t has been mapped. map maps the character from t to the associated counting sort position in the map. If we have not mapped the current character in s and the current t character is already mapped, we have to return false since two characters in s are trying to map to the same character in t. Otherwise, we update the map and the usage map for both the current characters in s and t. We then check to ensure that the mapped character is correct in case we had already used the character earlier in s.
    // Time: O(n) to iterate over the length of the strings.
    // Space: O(1) constant since we are utilizing fixed size arrays.
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        
        boolean[] freq1 = new boolean[256], freq2 = new boolean[256];
        char[] map = new char[256];
        
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i), c2 = t.charAt(i);
            
            if (!freq1[c1]) {
                if (freq2[c2]) return false;
                freq2[c2] = true;
                
                map[c1] = c2;
                freq1[c1] = true;
            }
            if (map[c1] != c2) return false;
        }
        
        return true;
    }
    
    public boolean isIsomorphic2(String s, String t) {
        if (s.length() != t.length()) return false;
        
        Map<Character, Character> map = new HashMap<>();
        int[] freq = new int[256];
        
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i), c2 = t.charAt(i);
            
            if (!map.containsKey(c1)) {
                if (freq[c2 - '\0'] == 1) return false;
                freq[c2 - '\0'] = 1;
                map.put(c1, c2);
            }
            if (map.get(c1) != c2) return false;
        }
        
        return true;
    }
}
