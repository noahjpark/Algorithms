/* Noah Park

Given a string s, return the "reversed" string where all characters that are not a letter stay in the same place, and all letters reverse their positions.

*/

class Solution {
    
    // Intuition: Swaps each letter on opposite sides of the string using two pointers.
    // Time: O(n) to iterate over s.
    // Space: O(1) constant.
    public String reverseOnlyLetters(String s) {
        if (s == null || s.length() == 0) return s;
        
        char[] res = s.toCharArray();
        int n = s.length(), i = 0, j = n - 1;
        
        while (i < j) {
            while (i < j && !Character.isAlphabetic(res[i])) i++;
            while (i < j && !Character.isAlphabetic(res[j])) j--;
            
            if (i < j) swap(res, i++, j--);
        }
        
        
        return new String(res);
    }
    
    public void swap(char[] res, int i, int j) {
        char temp = res[i];
        res[i] = res[j];
        res[j] = temp;
    }
}
