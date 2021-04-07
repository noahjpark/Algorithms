/* Noah Park

Given an array of strings words, return the words that can be typed using letters of the alphabet on only one row of American keyboard like the image below.

In the American keyboard:

the first row consists of the characters "qwertyuiop",
the second row consists of the characters "asdfghjkl", and
the third row consists of the characters "zxcvbnm".

*/

class Solution {
    
    // Intuition: Each row is cached in a constant array. findChar returns the corresponding row a character resides in. Thus, all that is required is to iterate over all words then their characters and ensure they all come from the same row.
    // Time: O(n*m) where n is the length of the words list and m is the length of the characters in each word
    // Space: O(1) not including the output array since the cached values are always constant.
    char[] a1 = new char[]{ 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p' };
    char[] a2 = new char[]{ 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l' };
    char[] a3 = new char[]{ 'z', 'x', 'c', 'v', 'b', 'n', 'm' };

    public String[] findWords(String[] words) {
        List<String> res = new ArrayList<>();
        
        for (String word : words) {
            char first = Character.toLowerCase(word.charAt(0));
            int row = findChar(first);
            boolean match = true;
            
            for (int i = 1; i < word.length(); i++) 
                if (findChar(Character.toLowerCase(word.charAt(i))) != row) { match = false; break; }
            
            if (match) res.add(word);
        }
        
        return res.toArray(new String[res.size()]);
    }
    
    public int findChar(char c) {
        for (int i = 0; i < 10; i++) {
            if (c == a1[i]) return 1;
            if (i < 9 && c == a2[i]) return 2;
            if (i < 7 && c == a3[i]) return 3;
        }
        return -1;
    }
}
