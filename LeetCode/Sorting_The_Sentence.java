/* Noah Park

A sentence is a list of words that are separated by a single space with no leading or trailing spaces. Each word consists of lowercase and uppercase English letters.

A sentence can be shuffled by appending the 1-indexed word position to each word then rearranging the words in the sentence.

For example, the sentence "This is a sentence" can be shuffled as "sentence4 a3 is2 This1" or "is2 sentence4 This1 a3".
Given a shuffled sentence s containing no more than 9 words, reconstruct and return the original sentence.

*/

class Solution {
    
    // Intuition: Split the string then store based on the index.
    // Time: O(n) two passes.
    // Space: O(n) for the split.
    public String sortSentence(String s) {
        if (s == null || s.length() == 0) return s;
        
        String[] split = s.split(" "), res = new String[split.length];
        
        for (String st : split)
            res[Integer.parseInt(st.substring(st.length() - 1)) - 1] = st.substring(0, st.length() - 1);
        
        StringBuilder ans = new StringBuilder();
        for (String st : res) ans.append(st).append(" ");
        
        return ans.toString().trim();
    }
}
