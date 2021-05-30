/* Noah Park

You are keeping score for a baseball game with strange rules. The game consists of several rounds, where the scores of past rounds may affect future rounds' scores.

At the beginning of the game, you start with an empty record. You are given a list of strings ops, where ops[i] is the ith operation you must apply to the record and is one of the following:

An integer x - Record a new score of x.
"+" - Record a new score that is the sum of the previous two scores. It is guaranteed there will always be two previous scores.
"D" - Record a new score that is double the previous score. It is guaranteed there will always be a previous score.
"C" - Invalidate the previous score, removing it from the record. It is guaranteed there will always be a previous score.
Return the sum of all the scores on the record.

*/

class Solution {
    
    // Intuition: Maintain the record in a stack. If the current element is a +, take the top two add them together and add to the stack. If the current element is a D, add double the top element to the stack. If the current element is a C, remove the top element from the stack. Otherwise, we can simply add the number to the record. Whatever is remaining in the stack is added to the result.
    // Time: O(n) to iterate over ops then empty the stack.
    // Space: O(n) to maintain the stack.
    public int calPoints(String[] ops) {
        Deque<Integer> s = new ArrayDeque<>();
        
        for (int i = 0; i < ops.length; i++) {
            if (ops[i].equals("+")) {
                int top = s.removeLast(), val = top + s.getLast();
                s.addLast(top);
                s.addLast(val);
            } else if (ops[i].equals("D")) s.addLast(s.getLast() * 2);
            else if (ops[i].equals("C")) s.removeLast();
            else s.addLast(Integer.parseInt(ops[i]));
        }
        
        int res = 0;
        
        while (!s.isEmpty())
            res += s.pop();
        
        return res;
    }
}
