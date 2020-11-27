/* Noah Park

In a list of songs, the i-th song has a duration of time[i] seconds. 

Return the number of pairs of songs for which their total duration in seconds is divisible by 60.  Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.

*/

class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int n = time.length, count = 0; 
        int[] mod = new int[60]; // store frequencies of each number mod 60 we find
        
        for(int num : time){ // iterate over all numbers
            if(num % 60 == 0) count += mod[0]; // i is already divisible by 60, check the 0 spot
            else count += mod[60 - num % 60]; // otherwise, check the complement and add all numbers we have found there as they can be valid pairs
            mod[num % 60]++; // update the mod count at our current number
            
        }
            
        return count;
    }
}
