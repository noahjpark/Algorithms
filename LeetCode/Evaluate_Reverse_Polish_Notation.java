/* Noah Park

Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, and /. Each operand may be an integer or another expression.

Note that division between two integers should truncate toward zero.

It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate to a result, and there will not be any division by zero operation.

*/

class Solution {
    
    // Intuition: Evaluates a postfix using a stack.
    // Time: O(n) to iterate over tokens.
    // Space: O(n) to maintain the stack.
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        
        for (String token : tokens) {
            if (token.equals("*")) stack.push(stack.pop() * stack.pop());
            else if (token.equals("+")) stack.push(stack.pop() + stack.pop());
            else if (token.equals("-")) {
                int first = stack.pop(), second = stack.pop();
                stack.push(second - first);
            }
            else if (token.equals("/")) {
                int first = stack.pop(), second = stack.pop();
                stack.push(second / first);
            } else stack.push(Integer.parseInt(token));
        }
        
        return stack.pop();
    }
}
