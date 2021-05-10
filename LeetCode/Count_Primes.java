/* Noah Park

Count the number of prime numbers less than a non-negative number, n.

*/

class Solution {
    
    // Intuition: Sieve of Eratosthenes to check all primes under n. Iterate from 2 to sqrt n since our inner will start at our outer squared assuming our outer is a prime number. If it isn't, all multiples won't be prime (obviously).
    // Time: O(sqrt(n) * loglogn) outer*inner.
    // Space: O(n) for the arr.
    public int countPrimes(int n) {
        int count = 0;
        int[] arr = new int[n];
        
        for (int i = 2; i * i < n; i++) 
            if (arr[i] == 0) 
                for (int j = i*i; j < n; j += i) 
                    arr[j] = -1;
        
        for (int i = 2; i < n; i++)
            if (arr[i] == 0) count++;
        
        return count;
    }
    
}
