/* Noah Park

A sentence sentence is given, composed of words separated by spaces. Each word consists of lowercase and uppercase letters only.

We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)

The rules of Goat Latin are as follows:

If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
For example, the word 'apple' becomes 'applema'.
 
If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
For example, the word "goat" becomes "oatgma".
 
Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
Return the final sentence representing the conversion from sentence to Goat Latin. 

*/

class Solution {
    
    // Intuition: Follow the rules, check each words first character and do the appropriatae thing based on vowel/consonant.
    // Time: O(n) to iterate over the sentence.
    // Space: O(n) for the return string.
    public String toGoatLatin(String sentence) {
        String[] split = sentence.split(" ");
        StringBuilder res = new StringBuilder(), a = new StringBuilder();
        a.append('a');
        
        for (int i = 0; i < split.length; i++) {
            String cur = split[i];
            
            if (!isVowel(cur.charAt(0)))
                res.append(cur.substring(1)).append(cur.charAt(0));
            else
                res.append(cur);
            
            res.append("ma").append(a.toString()).append(" ");
            a.append('a');
        }
        
        return res.toString().trim();
    }
    
    public boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
