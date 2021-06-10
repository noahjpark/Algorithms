/* Noah Park

Given an integer num, return a string of its base 7 representation.

*/

class Solution {
    
    // Intuition: Simulate base 7 conversion while maintaining a negative value from the beginning.
    // Time: O(logn) to go through all digits in the number.
    // Space: O(1) constant / O(n) to maintain resulting string otherwise.
    public String convertToBase7(int num) {
        if (num == 0) return "0";
        
        StringBuilder res = new StringBuilder();
        boolean neg = num < 0 ? true : false;
        
        for (int n = Math.abs(num); n > 0; n /= 7)
            res.insert(0, n % 7);
        if (neg) res.insert(0, '-');
        
        return res.toString();
    }
}
