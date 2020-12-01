/* Noah Park

Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
 

*/

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>(); // stack will hold our most recent parenthesis
        HashMap<Character, Character> h = new HashMap<>(); // map all possible parenthesis combinations
        h.put('}', '{');
        h.put(')', '(');
        h.put(']', '[');
        
        for(char c : s.toCharArray()){ // iterate through all characters in s
            if(h.containsKey(c)){ // if we are on a closing parenthesis
                char top = stack.isEmpty() ? ' ' : stack.pop(); // get the top of the stack
                if(top != h.get(c)) return false; // if they don't match, we don't have a valid parenthesis
            }
            else stack.add(c); // else put on top of the stack (opening parenthesis)
        }
        
        return stack.size() == 0; // stack should be empty in the end
    }
}
