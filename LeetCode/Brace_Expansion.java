/* Noah Park

You are given a string s representing a list of words. Each letter in the word has one or more options.

If there is one option, the letter is represented as is.
If there is more than one option, then curly braces delimit the options. For example, "{a,b,c}" represents options ["a", "b", "c"].
For example, if s = "a{b,c}", the first character is always 'a', but the second character can be 'b' or 'c'. The original list is ["ab", "ac"].

Return all words that can be formed in this manner, sorted in lexicographical order.

*/

class Solution {
    
    // Intuition: Typical dfs to create all string possibilities.
    // Time: O(n^m) to generate all possibilities n is the number of individual choices, m is the number of choices from each choice.
    // Space: O(n^m) to maintain all possibilities.
    public String[] expand(String s) {
        List<String> sort = new ArrayList<>();
        dfs(s, 0, new StringBuilder(), sort);
        Collections.sort(sort);
        
        return sort.toArray(new String[sort.size()]);
    }
    
    public void dfs(String s, int i, StringBuilder cur, List<String> sort) {
        if (i >= s.length()) sort.add(new String(cur.toString()));
        else {
            char c = s.charAt(i);
            
            if (c != '{') {
                cur.append(c);
                dfs(s, i + 1, cur, sort);
                cur.deleteCharAt(cur.length() - 1);
            } else {
                int end = i;
                
                for (int j = i + 1; j < s.length(); j++) {
                    if (s.charAt(j) == '}') { end = j; break; }
                }
                
                for (int j = i + 1; j < s.length() - 1; j+=2) {
                    cur.append(s.charAt(j));
                    dfs(s, end + 1, cur, sort);
                    cur.deleteCharAt(cur.length() - 1);
                    if (j + 1 == end) break;
                }
            }
        }
    }
}
