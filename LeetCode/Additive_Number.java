/* Noah Park

Additive number is a string whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.

Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.

*/

class Solution {
    
    // Intuition: dfs all possibilities to create subsequent sums. -1 signifies an unchosen number. If we have no first number, we attempt to choose all possible numbers excluding leading zero numbers but not excluding the first zero value. If we have no second number, we do the exact same thing. Once we have our two previous numbers, we attempt to make a third. If the third number becomes too large or has a leading zero, we must return false. Otherwise, we continue building the number. If we reach a point where it is a full subsequence (x + y = z) then we move old forward, prev forward, and reset cur to 0. 0 signifies that we had success in choosing our value and will return true if we fall off the end of the string.
    // Time: O(n^2) to make all comparisons.
    // Space: O(n) for the recursion stack.
    public boolean isAdditiveNumber(String num) {
        if (num == null || num.length() < 3) return false;
        return dp(num, 0, -1, -1, -1);
    }
    
    public boolean dp(String num, int i, long old, long prev, long cur) {
        if (i >= num.length()) return cur == 0; 
        
        boolean res = false;
        
        // choose the first number in the sequence if not chosen
        if (old == -1) {
            old = 0;
            for (int j = i; j < num.length(); j++) {
                old = (old * 10) + (num.charAt(j) - '0');
                res |= dp(num, j + 1, old, prev, cur);
                if (j == i && old == 0) break; // leading zero
            }
        } else if (prev == -1) { // choose the second number in the sequence if not chosen
            prev = 0;
            for (int j = i; j < num.length(); j++) {
                prev = (prev * 10) + (num.charAt(j) - '0');
                res |= dp(num, j + 1, old, prev, cur);
                if (j == i && prev == 0) break; // leading zero
            }
        } else { // choose the third number in the sequence if we have two chosen
            cur = 0;
            for (int j = i; j < num.length(); j++) {
                cur = (cur * 10) + (num.charAt(j) - '0');
                if (old + prev < cur || (old + prev > cur && cur == 0)) return false;
                else if (old + prev == cur) {
                    old = prev;
                    prev = cur;
                    cur = 0;
                    res |= dp(num, j + 1, old, prev, cur);
                } 
            }
        }
        
        return res;
    }
}
