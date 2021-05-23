/* Noah Park

Given a binary string s, return true if the longest contiguous segment of 1s is strictly longer than the longest contiguous segment of 0s in s. Return false otherwise.

For example, in s = "110100010" the longest contiguous segment of 1s has length 2, and the longest contiguous segment of 0s has length 3.
Note that if there are no 0s, then the longest contiguous segment of 0s is considered to have length 0. The same applies if there are no 1s.

*/

class Solution {
    
    // Intuition: Count both longest and return the larger value if ones.
    // Time: O(n) to iterate over the string.
    // Space: O(1) constant.
    public boolean checkZeroOnes(String s) {
        int ones = 0, zeroes = 0, curOnes = 0, curZeroes = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '1') { curOnes++; zeroes = Math.max(zeroes, curZeroes); curZeroes = 0; }
            else { curZeroes++; ones = Math.max(ones, curOnes); curOnes = 0; }
        }
        
        zeroes = Math.max(zeroes, curZeroes);
        ones = Math.max(ones, curOnes);
        
        return ones > zeroes;
    }
}
