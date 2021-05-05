/* Noah Park

Given a string columnTitle that represents the column title as appear in an Excel sheet, return its corresponding column number.

*/

class Solution {
    
    // Intuition: Take 26 to the power of length - current index - 1 (since 1 indexed) to get the value from each letter in the sheet.
    // Time: O(n) to iterate over the title.
    // Space: O(1) constant.
    String cols = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    public int titleToNumber(String columnTitle) {
        int title = 0, n = columnTitle.length();
        
        for (int i = 0; i < n; i++) 
            title += getVal(columnTitle.charAt(i), n - i - 1);
        
        return title;
    }
    
    public int getVal(char c, int idx) {
        return cols.indexOf(c) * (int) Math.pow(26, idx);
    }
}
