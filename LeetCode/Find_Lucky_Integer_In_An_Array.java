/* Noah Park

Given an array of integers arr, a lucky integer is an integer which has a frequency in the array equal to its value.

Return a lucky integer in the array. If there are multiple lucky integers return the largest of them. If there is no lucky integer return -1.

*/

class Solution {
    public int findLucky(int[] arr) {
        int[] freq = new int[501];
        
        for(int num : arr)
            freq[num]++;
        
        for(int i = 500; i > 0; i--)
            if(freq[i] == i) return i;
        
        return -1;
    }
}
