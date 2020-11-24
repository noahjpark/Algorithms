/* Noah Park

Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

*/

class Solution {
    
    // Checks if the given substring p1 to p2 is a palindrome or not
    public boolean isPal(String s, int p1, int p2){
        while(p1 < p2){
            if(s.charAt(p1) != s.charAt(p2)) return false;
            p1++; p2--;
        }
        return true;
    }
    
    public boolean validPalindrome(String s) {
        int p1 = 0, p2 = s.length() - 1; // do normal palindrome checks
        while(p1 < p2) {
            // If the characters don't match, check the "removal" of either character. If we have a palindrome, we only needed one removal
            if(s.charAt(p1) != s.charAt(p2)) return isPal(s, p1 + 1, p2) || isPal(s, p1, p2 - 1);
            p1++; p2--;
        }
        return true; // We didn't need to remove
    }
}
