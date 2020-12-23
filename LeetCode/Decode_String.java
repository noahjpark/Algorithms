/* Noah Park

Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

*/

class Solution {
    public String decodeString(String s) {
        if (s == null || s.length() == 0) return s; // edge cases
        
        StringBuilder res = new StringBuilder(); // resulting string
        Stack<String> stack = new Stack<>(); // stores strings as we go
        Stack<Integer> count = new Stack<>(); // stores number of times we need to append the string
        int cur = 0; // keeps track of our current number
        
        for (char c : s.toCharArray()) { // iterate over the characters
            if (Character.isDigit(c)) cur = (cur * 10) + c - '0'; // add a new digit if we find one
            else if (c == '[') { // we are starting a substring repetition, push to the stacks and reset the variables
                count.push(cur);
                stack.push(res.toString());
                res = new StringBuilder();
                cur = 0;
            } else if (c == ']') { // we are at the end of a repetition, add to the resulting string
                StringBuilder temp = res;
                res = new StringBuilder(stack.pop());
                int n = count.pop();
                for (int j = 0; j < n; j++) res.append(temp);
            } else res.append(c); // append a character
        }
        
        return res.toString();
    }
}
