/* Noah Park

Given an array A of positive integers, let S be the sum of the digits of the minimal element of A.

Return 0 if S is odd, otherwise return 1.

*/

class Solution {
    public int sumOfDigits(int[] A) {
        int min = Integer.MAX_VALUE; // compute min
        
        for (int num : A) min = Math.min(min, num); // find the min
        
        int sum = 0;
        
        while (min > 0) { // sum up the digits
            sum += min % 10;
            min /= 10;
        }
        
        return sum % 2 == 0 ? 1 : 0; // return 1 if even and 0 if odd
    }
}
