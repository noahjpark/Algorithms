/* Noah Park

Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].

*/

class Solution {
    
    // Intuition: Utilize a stack to maintain temperatures when we don't have a larger temperature currently so we can come back to them.
    // Time: O(n) where n is the length of T since we would push/pop each element once from the stack (2n).
    // Space: O(n) where n is the length of T.
    public int[] dailyTemperatures1(int[] T) {
        Stack<int[]> s = new Stack<>();
        int[] res = new int[T.length];
        
        for (int i = 0; i < T.length; i++) {
            while (!s.isEmpty() && T[i] > s.peek()[0])
                res[s.peek()[1]] = i - s.pop()[1];
            
            s.push(new int[]{ T[i], i });
        }
        
        return res;
    }
}
