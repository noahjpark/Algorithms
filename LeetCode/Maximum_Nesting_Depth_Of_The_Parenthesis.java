/* Noah Park

A string is a valid parentheses string (denoted VPS) if it meets one of the following:

It is an empty string "", or a single character not equal to "(" or ")",
It can be written as AB (A concatenated with B), where A and B are VPS's, or
It can be written as (A), where A is a VPS.
We can similarly define the nesting depth depth(S) of any VPS S as follows:

depth("") = 0
depth(C) = 0, where C is a string with a single character not equal to "(" or ")".
depth(A + B) = max(depth(A), depth(B)), where A and B are VPS's.
depth("(" + A + ")") = 1 + depth(A), where A is a VPS.
For example, "", "()()", and "()(()())" are VPS's (with nesting depths 0, 1, and 2), and ")(" and "(()" are not VPS's.

Given a VPS represented as string s, return the nesting depth of s.

*/

class Solution {
    public int maxDepth(String s) {
        int max = 0, parenthesis = 0; // max is the max number of opening parenthesis we have seen so far, parenthesis is the total opening parenthesis we have so far

        for(char c : s.toCharArray()){ // iterate through s
            if(parenthesis > 0 && c == ')') parenthesis--; // if we find a closing parenthesis and have at least 1 opening parenthesis, we can subtract one from our opening count
            else if(c == '(') parenthesis++; // we found an opening and can add one to our opening count
            
            max = Math.max(max, parenthesis); // keep track of the maximum number of opening parenthesis we have ever had
        }
        
        return max - parenthesis; // our deepest nest would be the maximum opening parenthesis - the remaining we have leftover
    }
}
