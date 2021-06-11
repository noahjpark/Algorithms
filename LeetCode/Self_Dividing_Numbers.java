/* Noah Park

A self-dividing number is a number that is divisible by every digit it contains.

For example, 128 is a self-dividing number because 128 % 1 == 0, 128 % 2 == 0, and 128 % 8 == 0.
A self-dividing number is not allowed to contain the digit zero.

Given two integers left and right, return a list of all the self-dividing numbers in the range [left, right].

*/

class Solution {
    
    // Intuition: Check all digits in the range [left, right] to see if they are self dividing.
    // Time: O(n*log(m)) where n is the range of numbers and m is the current number.
    // Space: O(n) for the result otherwise O(1) constant.
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= right; i++) 
            if (selfDividing(i)) res.add(i);
        return res;
    }
    
    public boolean selfDividing(int num) {
        for (int n = num; n > 0; n /= 10)
            if (n % 10 == 0 || num % (n % 10) != 0) return false;
        
        return true;
    }
    
}
