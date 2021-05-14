/* Noah Park

Given two integers left and right, find the count of numbers in the range [left, right] (inclusive) having a prime number of set bits in their binary representation.

(Recall that the number of set bits an integer has is the number of 1s present when written in binary. For example, 21 written in binary is 10101 which has 3 set bits. Also, 1 is not a prime.)

*/

class Solution {
    
    // Intuition: Count bits of each number then check if that count is prime. Largest number is 19 since that is the most bits that our range can hold.
    // Time: O(nlogn) to count the bits of each number in the range.
    // Space: O(1) constant.
    int[] primes = new int[]{ 2, 3, 5, 7, 11, 13, 17, 19 };
    
    public int countPrimeSetBits(int left, int right) {
        int primes = 0;
        
        for (int i = left; i <= right; i++)
            if (isPrime(countBits(i))) primes++;
        
        return primes;
    }
    
    public boolean isPrime(int n) {
        if (n == 1 || n == 0) return false;
        
        for (int i : primes) {
            if (n == i) return true;
            if (n % i == 0) return false;
        }
        
        return true;
    }
    
    public int countBits(int n) {
        int count = 0;
        
        for (; n > 0; n >>= 1)
            count += n & 1;
        
        return count;
    }
    
    
}
