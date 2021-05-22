/* Noah Park

Given a binary array data, return the minimum number of swaps required to group all 1’s present in the array together in any place in the array.

*/

class Solution {
    
    // Intuition: Sliding window technique. We can swap 1s anywhere in the array in a number of ways. We consider that at any point in the array, all ones have to be grouped together. In this way, we consider all subarrays of size (totalNumberOfOnes) and see how many zeroes there are in it. We minimize this value to minimize the number of swaps.
    // Time: O(n) 2 passes -> 1 for counting the number of ones and 1 for the sliding window.
    // Space: O(1) constant.
    public int minSwaps(int[] data) {
        int numOnes = 0, n = data.length;
        
        for (int i = 0; i < data.length; i++) 
            if (data[i] == 1) numOnes++;
        
        int s = 0, e = 0, curZeroes = 0;
        for (; e < numOnes; e++)
            if (data[e] == 0) curZeroes++;
        
        int min = curZeroes;
        for (; e < n; s++, e++) {
            curZeroes += data[e] == 0 ? 1 : 0;
            curZeroes -= data[s] == 0 ? 1 : 0;
            min = Math.min(min, curZeroes);
        }
        
        return min;
    }
}
