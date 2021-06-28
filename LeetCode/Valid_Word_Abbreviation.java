/* Noah Park

A string can be abbreviated by replacing any number of non-adjacent substrings with their lengths. For example, a string such as "substitution" could be abbreviated as (but not limited to):

"s10n" ("s ubstitutio n")
"sub4u4" ("sub stit u tion")
"12" ("substitution")
"su3i1u2on" ("su bst i t u ti on")
"substitution" (no substrings replaced)
Note that "s55n" ("s ubsti tutio n") is not a valid abbreviation of "substitution" because the replaced substrings are adjacent.

Given a string s and an abbreviation abbr, return whether the string matches with the given abbreviation.

*/

class Solution {
    
    // Intuition: Two pointers. When abbr pointer is on a character, we ensure both characters match and move each pointer forward. Otherwise, we calculate the number and move i forward that many places since we are jumping over those characters. There is a weird edge case with leading zeroes.
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0, j = 0, n = word.length(), m = abbr.length();
        
        while (i < n && j < m) {
            if (!Character.isDigit(abbr.charAt(j))) {
                if (word.charAt(i++) != abbr.charAt(j++)) return false;
            } else {
                int jump = abbr.charAt(j) - '0';
                if (jump == 0) return false; // edge case for leading zeroes
                while (j + 1 < m && Character.isDigit(abbr.charAt(j + 1)))
                    jump = (jump * 10) + (abbr.charAt(++j) - '0');
                j++;
                i += jump;
            }
        }
        
        return i == n && j == m;
    }
    
}
