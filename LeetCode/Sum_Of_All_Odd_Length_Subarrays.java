/* Noah Park

Given an array of positive integers arr, calculate the sum of all possible odd-length subarrays.

A subarray is a contiguous subsequence of the array.

Return the sum of all odd-length subarrays of arr

*/

class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        int total = 0;
        
        for(int i = 1; i <= arr.length; i+=2){
            int wstart = 0, wend = 0;
            int sum = 0;
            
            while(wend < arr.length){
                sum += arr[wend];
                
                while(wend - wstart + 1 > i)
                    sum -= arr[wstart++];
                
                if(wend - wstart + 1 == i) total += sum;
                
                wend++;
            }
            
        }
        
        return total;
        
        /* Better O(n) solution using Mathematics */
        // int total = 0;
        // for(int i = 0; i < arr.length; i++){
        //      total += ((((arr.length-i) * (i+1)) + 1 )/2) * arr[i];
        // }
        // return total;
    }
}
