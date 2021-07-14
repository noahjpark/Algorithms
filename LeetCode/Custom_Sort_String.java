/* Noah Park

order and str are strings composed of lowercase letters. In order, no letter occurs more than once.

order was sorted in some custom order previously. We want to permute the characters of str so that they match the order that order was sorted. More specifically, if x occurs before y in order, then x should occur before y in the returned string.

Return any permutation of str (as a string) that satisfies this property.

*/

class Solution {
    
    // Intuition: Store characters in an arraylist. Use a custom comparator and sort.
    // Time: O(n log n) for sorting
    // Space: O(n) for the output.
    public String customSortString2(String order, String str) {
        List<Character> l = new ArrayList<>();
        for (char c : str.toCharArray())
            l.add(c);
        
        Collections.sort(l, (a, b) -> (order.indexOf(a) - order.indexOf(b)));
        StringBuilder res = new StringBuilder();
        for (char ch : l)
            res.append(ch);
        
        return res.toString();
    }
    
    // Intuition: Maintain a frequency array of constant size to put the characters from order then the remaining.
    // Time: O(n + m) where n is the length of order and m is the length of str.
    // Space: O(m) length of str.
    public String customSortString(String order, String str) {
        int[] freq = new int[26];
        for (char c : str.toCharArray())
            freq[c - 'a']++;
        
        StringBuilder res = new StringBuilder();
        for (char c : order.toCharArray()) {
            for (int i = 0; i < freq[c - 'a']; i++)
                res.append(c);
            freq[c - 'a'] = 0;
        }
        
        for (int i = 0; i < 26; i++)
            for (int j = 0; j < freq[i]; j++)
                res.append((char) (i + 'a'));
            
        return res.toString();
    }
    
}
