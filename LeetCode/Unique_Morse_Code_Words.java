/* Noah Park

class Solution {
    
    String[] code = new String[]{ ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", 
                                 "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.." };
    
    // Intuition: Utilize a global code converter from the corresponding alphabet letter to the morse code equivalent. Utilize a set that maintains each converted word. From here we iterate over all words and build the morse code equivalent then add to the set which prevents duplicates.
    // Time: O(w*c) where w is the number of words and c is the number of characters in each word.
    // Space: O(w*c) to store all words in the set.
    public int uniqueMorseRepresentations(String[] words) {
        Set<String> set = new HashSet<>();
        
        for (String s : words) {
            StringBuilder temp = new StringBuilder();
            
            for (char c : s.toCharArray()) 
                temp.append(code[c - 'a']);
            
            set.add(temp.toString());
        }
        
        return set.size();
    }
}

*/

class Solution {
    
    String[] code = new String[]{ ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", 
                                 "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.." };
    
    // Intuition: Utilize a global code converter from the corresponding alphabet letter to the morse code equivalent. Utilize a set that maintains each converted word. From here we iterate over all words and build the morse code equivalent then add to the set which prevents duplicates.
    // Time: O(w*c) where w is the number of words and c is the number of characters in each word.
    // Space: O(w*c) to store all words in the set.
    public int uniqueMorseRepresentations(String[] words) {
        Set<String> set = new HashSet<>();
        
        for (String s : words) {
            StringBuilder temp = new StringBuilder();
            
            for (char c : s.toCharArray()) 
                temp.append(code[c - 'a']);
            
            set.add(temp.toString());
        }
        
        return set.size();
    }
}
