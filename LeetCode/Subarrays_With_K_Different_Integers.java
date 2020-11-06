/* Noah Park

Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A 

good if the number of different integers in that subarray is exactly K.

(For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)

Return the number of good subarrays of A.

*/

class Solution {
    public int subarraysWithKDistinct(int[] A, int K) {
        int wstart = 0, wend = 0;
        int subarrays = 0;
        int match = 0, p = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        
        // start pointer always points to an element with only 1 frequency
        while(wend < A.length){
            if(!freq.containsKey(A[wend]) || freq.get(A[wend]) == 0) match++;
            
            freq.put(A[wend], freq.getOrDefault(A[wend], 0) + 1);
            
            // Too many numbers
            if(match > K){
                int num = A[wstart++];
                p = 0;
                freq.put(num, freq.get(num) - 1);
                match--; // Since the frequency of start is always 1, match is decremented
            }
            
            // Ensure that the start always points to a frequency of 1
            // Increment p since match is no longer greater than K
            while(freq.get(A[wstart]) > 1){
                int num = A[wstart++];
                freq.put(num, freq.get(num) - 1);
                p++;
            }
    
            if(match == K) subarrays += p + 1;
            
            wend++;
        }
        
        return subarrays;
    }
}
