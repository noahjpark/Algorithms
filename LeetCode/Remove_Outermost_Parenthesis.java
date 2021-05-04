/* Noah Park

A valid parentheses string is either empty (""), "(" + A + ")", or A + B, where A and B are valid parentheses strings, and + represents string concatenation.  For example, "", "()", "(())()", and "(()(()))" are all valid parentheses strings.

A valid parentheses string S is primitive if it is nonempty, and there does not exist a way to split it into S = A+B, with A and B nonempty valid parentheses strings.

Given a valid parentheses string S, consider its primitive decomposition: S = P_1 + P_2 + ... + P_k, where P_i are primitive valid parentheses strings.

Return S after removing the outermost parentheses of every primitive string in the primitive decomposition of S.

*/

class Solution {
    
    // Intuition: Keeps track of the open parenthesis. Increment open on '(' and decrement on ')'. If there is more than one open parenthesis, we add it to the output. If there is more than zero open parenthesis after decrementing a closing parenthesis, add it to the output.
    // Time: O(n) to iterate over S.
    // Space: O(n) to maintain the output string.
    public String removeOuterParentheses(String S) {
        int open = 0;
        StringBuilder res = new StringBuilder();
        
        for (char c : S.toCharArray()) {
            if (c == '(') {
                open++;
                if (open > 1) res.append(c);
            }
            else if (c == ')') {
                open--;
                if (open > 0) res.append(c);
            }
        }
        
        return res.toString();
    }
}
