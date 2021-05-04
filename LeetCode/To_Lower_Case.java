/* Noah Park

Implement function ToLowerCase() that has a string parameter str, and returns the same string in lowercase.

*/

class Solution {
    
    // Intuition: Straightforward use of lower case method.
    // Time: O(n) to copy str over.
    // Space: O(n) for the stringbuilder.
    public String toLowerCase(String str) {
        StringBuilder res = new StringBuilder();
        
        for (char c : str.toCharArray())
            res.append(Character.toLowerCase(c));
        
        return res.toString();
    }
}
