/* Noah Park

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

*/

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>(); // list of all generated strings
        helper(res, "", 0, 0, n); // helper
        return res;
    }
    
    public void helper(List<String> res, String cur, int open, int closed, int n) {
        if (open == n && closed == n) { res.add(cur); return; } // we have a fully generated string
        
        // if we can add an open, do so
        if (open < n) helper(res, cur + "(", open + 1, closed, n);
        if (closed < open) helper(res, cur + ")", open, closed + 1, n); // if we can add a closed (there are open ones available to close), do so
    }
}
