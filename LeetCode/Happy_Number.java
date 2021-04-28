/* Noah Park

Write an algorithm to determine if a number n is happy.

A happy number is a number defined by the following process:

Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy.
Return true if n is a happy number, and false if not.

*/

class Solution {
    
    // Intuition: Fast slow pointer to detect cycles.
    // Time: O(log n)
    // Space: O(1)
    public boolean isHappy(int num) {
        int slow = num, fast = num;
        while(fast != 1){
          int s2 = square(slow);
          int s1 = square(square(fast));
          if(s1 == s2 && s1 != 1) return false;

          slow = s2;
          fast = s1;
        }
        return true;
    }
    
    public int square(int num){
        int total = 0;
        while(num > 0){
          total += Math.pow(num % 10, 2);
          num /= 10;
        }
        return total;
    }
}
