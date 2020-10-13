// Noah Park
/*

Problem: Write a function that takes in a string made up of brackets ( (, [, {, ), ], } ) and other
optional characters. The function should return a boolean representing whether the string is balanced
with regards to brackets.

A string is said to be balanced if it has as many opening brackets of a certain type as it has closing
brackets of that type and if no bracket is unmatched. Note that an opening bracket can't match a corresponding
closing bracket that comes before it, and similarly, a closing bracket can't match a corresponding opening bracket
that comes after it. Also brackets can't overlap each other as in [(]).

*/

import java.util.HashMap;
import java.util.Stack;

public class Balanced_Brackets {
    // Time: O(n) | Space: O(n)
    public static boolean balancedBrackets(String str) {
        // Store all opposing brackets in a hash map
        // Store the closing ones first - essentially, we are checking to see if, at a certain
        // character, the top of our stack will contain the opening bracket.
        // Initialize an empty stack
        HashMap<Character, Character> brackets = new HashMap<>();
        brackets.put(')', '(');
        brackets.put('}', '{');
        brackets.put(']', '[');
        Stack<Character> s = new Stack<>();

        // Iterate through the strings indices
        for(int i = 0; i < str.length(); i++){
            // Get the current character
            /*
            If there is an optional different character, the first if statement will continue
            to the next iteration instead of doing additional checks since we don't care about optional characters.
            If the stack is empty, we should push the current character into the stack. Ideally this will be an opening bracket.
            If it isn't, we will catch it in our return statement.
            Else, if the stack is not empty, we have to check if the brackets.get(character) matches the top bracket in
            the stack. If they match, we found an inner pair and can pop off the stack. If they don't match, we must
            push the bracket onto the stack.
            In the end, we check if the stack is empty and return true if it is and false otherwise. If all the brackets followed
            the rules, the stack must be empty as we would have found a matching pair and popped its complement from the stack.
            */
            char c = str.charAt(i);
            if(!brackets.containsValue(c) && !brackets.containsKey(c)) continue;
            if(s.isEmpty()) s.push(c);
            else{
                if(brackets.get(c) == s.peek()) s.pop();
                else s.push(c);
            }
        }

        return s.isEmpty();
    }
}
