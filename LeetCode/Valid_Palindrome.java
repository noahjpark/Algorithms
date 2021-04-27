/* Noah Park

Given a string s, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

*/

class Solution {
    
    // Intuition: Utilize two pointers to move to the next comparable alnum characters.
    // Time: O(n) to iterate over s. alnum method is constant but isLetterOrDigit is faster.
    // Space: O(1) constant.
    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        
        while (i < j) {
            while (i < s.length() && !Character.isLetterOrDigit(s.charAt(i))) i++;
            while (j >= 0 && !Character.isLetterOrDigit(s.charAt(j))) j--;
            
            if (i >= j) break;
            
            if (Character.toLowerCase(s.charAt(i++)) != Character.toLowerCase(s.charAt(j--))) return false;
        }
        
        return true;
    }
    
    // Intuition: Store all valid alnum chars in a stringbuilder then compare start and end to the middle.
    // Time: O(n) to iterate over s then check if palindrome. 
    // Space: O(n) to convert s to string builder.
    public boolean isPalindrome2(String s) {
        StringBuilder cut = new StringBuilder();
        
        for (char c : s.toCharArray())
            if (isAlNum(Character.toLowerCase(c))) cut.append(Character.toLowerCase(c));
        
        int i = 0, j = cut.length() - 1;
        
        while (i < j) 
            if (cut.charAt(i++) != cut.charAt(j--)) return false;
        
        return true;
    }
    
    public boolean isAlNum(char c) {
        c = Character.toLowerCase(c);
        String alnum = "abcdefghijklmnopqrstuvwxyz0123456789";
        
        for (char ch : alnum.toCharArray())
            if (c == ch) return true;
        
        return false;
    }
}
