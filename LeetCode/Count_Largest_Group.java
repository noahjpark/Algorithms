/* Noah Park

Given an integer n. Each number from 1 to n is grouped according to the sum of its digits. 

Return how many groups have the largest size.

*/

class Solution {
    // Time: O(n*d)
    // Space: O(1)
    public int countLargestGroup(int n) {
        int[] freq = new int[37]; // optimize instead of hashmap since we know the maximum number for n is 10000, so the maximum digit sum is 9+9+9+9 = 36
        int largestSum = 0;
        
        // accumulate frequency in freq array and store maximum digit sum
        for (int i = 1; i <= n; i++) {
            int digits = sumDigits(i);
            freq[digits]++;
            largestSum = Math.max(largestSum, freq[digits]);
        }
        
        int res = 0;
        
        // increment for each number that has the maximum digit sum
        for (int num : freq)
            if (num == largestSum) res++;
        
        return res;
    }
    
    // Time: O(n*d)
    // Space: O(n)
    public int countLargestGroupMap(int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int largestSum = 0;
        
        for (int i = 1; i <= n; i++) {
            int digits = sumDigits(i);
            map.put(digits, map.getOrDefault(digits, 0) + 1);
            largestSum = Math.max(map.get(digits), largestSum);
        }
        
        int res = 0;
        
        for (Integer key : map.keySet()) 
            if (map.get(key) == largestSum) res++;
        
        return res;
    }
    
    // Time: O(d) where d is the number of digits in n
    // Space: O(1)
    public int sumDigits(int n) {
        int sum = 0;
        
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        
        return sum;
    }
}
