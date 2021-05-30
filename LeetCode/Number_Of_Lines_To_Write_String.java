/* Noah Park

You are given a string s of lowercase English letters and an array widths denoting how many pixels wide each lowercase English letter is. Specifically, widths[0] is the width of 'a', widths[1] is the width of 'b', and so on.

You are trying to write s across several lines, where each line is no longer than 100 pixels. Starting at the beginning of s, write as many letters on the first line such that the total width does not exceed 100 pixels. Then, from where you stopped in s, continue writing as many letters as you can on the second line. Continue this process until you have written all of s.

Return an array result of length 2 where:

result[0] is the total number of lines.
result[1] is the width of the last line in pixels.

*/

class Solution {
    
    // Intuition: Maintain the total lines used and the length of the current line (These are the return values as well). If adding a character results in a line length of greater than 100, we go to the next line and reset the current line length. Otherwise, we add the width of the character from s.
    // Time: O(n) to iterate over s.
    // Space: O(1) constant.
    public int[] numberOfLines(int[] widths, String s) {
        int lines = 1, curLine = 0;
        
        for (char c : s.toCharArray()) {
            if (curLine + widths[c - 'a'] <= 100) curLine += widths[c - 'a'];
            else {
                lines++;
                curLine = widths[c - 'a'];
            }
        }
        
        return new int[]{ lines, curLine };
    }
}
