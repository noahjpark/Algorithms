/* Noah Park

Given two binary strings a and b, return their sum as a binary string.

*/

class Solution {
    
    // Intuition: Two pointers on the ends working towards the start of each string. Typical addition algorithm with stringbuilder result.
    // Time: O(max(n, m)) iterating over the longer string.
    // Space: O(max(n, m)) length of the longer string.
    public String addBinary(String a, String b) {
        int p1 = a.length() - 1, p2 = b.length() - 1, rem = 0;
        StringBuilder res = new StringBuilder();
        
        while (p1 >= 0 || p2 >= 0) {
            char c1 = p1 >= 0 ? a.charAt(p1) : '0', c2 = p2 >= 0 ? b.charAt(p2) : '0';
            int val = rem;
            
            if (c1 == '1') val++;
            if (c2 == '1') val++;
            
            res.append(val % 2);
            rem = val > 1 ? 1 : 0;
            
            p1--; p2--;
        }
        
        if (rem > 0) res.append(rem);
        
        return res.reverse().toString();
    }
}
