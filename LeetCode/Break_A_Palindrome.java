/* Noah Park

Given a palindromic string palindrome, replace exactly one character by any lowercase English letter so that the string becomes the lexicographically smallest possible string that isn't a palindrome.

After doing so, return the final string.  If there is no way to do so, return the empty string.

*/

class Solution {
    public String breakPalindrome(String palindrome) {
        char[] letters = palindrome.toCharArray(); // convert to character array for easy modification
        
        // palindrome means that the first half of the characters are in the second half reversed
        // if we find a letter other than 'a' we can replace that first occurence with 'a' for a valid broken palindrome that is lexicographically smaller and return that string
        for (int i = 0; i < letters.length / 2; i++) {
            if (letters[i] > 'a') {
                letters[i] = 'a';
                return new String(letters);
            }
        }
        
        // replace the last letter with 'b' if all letters are 'a' and either return that string or "" if the length of letters is only 1
        letters[letters.length - 1] = 'b';
        return letters.length == 1 ? "" : new String(letters);
    }
}
