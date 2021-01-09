/* Noah Park

Given a positive integer num consisting only of digits 6 and 9.

Return the maximum number you can get by changing at most one digit (6 becomes 9, and 9 becomes 6).

*/

class Solution {
    public int maximum69Number (int num) {
        // intuition: find the leftmost 6, if there is one, and change it to a 9 for the return integer. if there is no 6, return num
        
        String s = String.valueOf(num); // store string value for easy index finding
        
        int idx = 0;
        
        // find leftmost index of a 6 if one exists
        while (idx < s.length()) {
            if (s.charAt(idx) == '6') break;
            else idx++;
        }
        
        // no sixes, we can't make a larger number so return num
        if (idx == s.length()) return num;
        
        int res = 0;
        
        // add all numbers back to a new integer like normal except at idx we are changing a 6 to a 9
        for (int i = 0; i < s.length(); i++) {
            int val = i != idx ? Integer.parseInt(String.valueOf(s.charAt(i))) : 9;
            res = res * 10 + val;
        }
        
        return res;
    }
}
