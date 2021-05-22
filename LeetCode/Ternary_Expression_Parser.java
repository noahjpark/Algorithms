/* Noah Park

Given a string expression representing arbitrarily nested ternary expressions, evaluate the expression, and return the result of it.

You can always assume that the given expression is valid and only contains digits, '?', ':', 'T', and 'F' where 'T' is true and 'F' is false. All the numbers in the expression are one-digit numbers (i.e., in the range [0, 9]).

The conditional expressions group right-to-left (as usual in most languages), and the result of the expression will always evaluate to either a digit, 'T' or 'F'.

*/

class Solution {
    
    // Intuition: If we are at the end of the string, the current value is not the beginning of a new ternary expression, or we have a digit, we can return a substring of our current value. Otherwise, if we have a 'T' we simply move forward two spaces into ternary expression. if we have a false, we need to find the corresponding ':' to our associated '?'. We can count the '?' and subtract the number of ':' from this value until we get back to zero.
    // Time: O(n) to iterate over the expression.
    // Space: O(1) constant.
    public String parseTernary(String expression) {
        int p = 0;
        
        while (p < expression.length()) {
            if (p + 1 == expression.length() || expression.charAt(p + 1) != '?' || Character.isDigit(expression.charAt(p))) return expression.substring(p, p + 1);
            else if (expression.charAt(p) == 'T') p += 2;
            else {        
                for (int qcount = 0; p < expression.length(); p++) {   
                    if (expression.charAt(p) == '?') qcount++;
                    else if (expression.charAt(p) == ':') qcount--;
                    
                    if (expression.charAt(p) == ':' && qcount == 0) { p++; break; } 
                }
            }
        }
        
        return null;
    }
}
