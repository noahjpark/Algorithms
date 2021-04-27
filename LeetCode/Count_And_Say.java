/* Noah Park

The count-and-say sequence is a sequence of digit strings defined by the recursive formula:

countAndSay(1) = "1"
countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1), which is then converted into a different digit string.
To determine how you "say" a digit string, split it into the minimal number of groups so that each group is a contiguous section all of the same character. Then for each group, say the number of characters, then say the character. To convert the saying into a digit string, replace the counts with a number and concatenate every saying.

For example, the saying and conversion for digit string "3322251":


Given a positive integer n, return the nth term of the count-and-say sequence.

*/

class Solution {
    
    // Intuition: Follow the recursive steps. Get the next levels string then apply the count and say algorithm to a solution.
    // Time: O(n * m) where n is the recursion and m is the length of the strings.
    // Space: O(n) for the recursive stack height.
    public String countAndSay(int n) {
        if (n == 1) return "1";
        
        String s = countAndSay(n - 1);
        StringBuilder res = new StringBuilder();
        int count = 0;
        char cur = s.charAt(0);
        
        for (int i = 0; i < s.length(); i++) {
            while (i < s.length() && s.charAt(i) == cur) { count++; i++; }
            
            res.append(count);
            res.append(cur);
            
            if (i < s.length()) { cur = s.charAt(i); count = 0; i--; }
        }
        
        return res.toString();
    }
}
