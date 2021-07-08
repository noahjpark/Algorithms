/* Noah Park

Given a character array s, reverse the order of the words.

A word is defined as a sequence of non-space characters. The words in s will be separated by a single space.

Your code must solve the problem in-place, i.e. without allocating extra space.

*/

class Solution {
    
    // Intuition: Reverse the entire character array then reverse each word. Two passes over the array. reverse is utilized for both parts making this problem very modular.
    // Time: O(n) two passes.
    // Space: O(1) constant.
    public void reverseWords(char[] s) {
        int p1 = 0, p2 = 0, n = s.length;
        reverse(s, 0, n);
        
        while (p2 < n) {
            while (p2 < n && s[p2] != ' ') p2++;
            reverse(s, p1, p2);
            p1 = ++p2;
        }
    }
    
    public void reverse(char[] s, int i, int j) {
        for (int k = 0; k < (j - i) / 2; k++) {
            char temp = s[i + k];
            s[i + k] = s[j - 1 - k];
            s[j - 1 - k] = temp;
        }
    }
}
