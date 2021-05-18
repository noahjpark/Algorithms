/* Noah Park

Given strings s1 and s2 of the same length, we say s1[i] and s2[i] are equivalent characters. For example, if s1 = "abc" and s2 = "cde", then we have 'a' == 'c', 'b' == 'd', 'c' == 'e'.

Equivalent characters follow the usual rules of any equivalence relation:

Reflexivity: 'a' == 'a'
Symmetry: 'a' == 'b' implies 'b' == 'a'
Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'
For example, given the equivalency information from s1 and s2 above and baseStr = "eed", "acd" and "aab" are equivalent strings of baseStr, and "aab" is the lexicographically smallest equivalent string of baseStr.

Return the lexicographically smallest equivalent string of baseStr by using the equivalency information from s1 and s2.

*/

class Solution {
    
    // Intuition: Union find the groupings to match like characters together.
    // Time: O(n + m) to iterate over s1/s2 then over the base. Union find call is negligible since the size of the map is 26.
    // Space: O(1) map is always size 26.
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        int n = s1.length();
        char[] map = new char[26];
        for (int i = 0; i < 26; i++) 
            map[i] = (char) ('a' + i);
        
        for (int i = 0; i < n; i++) 
            union(map, s1.charAt(i), s2.charAt(i));
        
        StringBuilder res = new StringBuilder();
        for (char c : baseStr.toCharArray()) 
            res.append(find(map, c)); // This is how we are able to avoid the issue like o/r then a/o where r gets updated to o then never updated to a. Find will allow us to still associate r with a since o gets updated to a. It maintains a path to the smallest parent.
        
        return res.toString();
    }
    
    // union the character mappings together to the same smaller parent based on lexicographic order.
    public void union(char[] map, char c1, char c2) {
        char s1 = find(map, c1), s2 = find(map, c2), smaller = s1 < s2 ? s1 : s2;
        map[s1 - 'a'] = smaller;
        map[s2 - 'a'] = smaller;
    }
    
    // find the parent of a given character
    public char find(char[] map, char c) {
        if (map[c - 'a'] == c) return c;
        map[c - 'a'] = find(map, map[c - 'a']);
        return map[c - 'a'];
    }
    
}
