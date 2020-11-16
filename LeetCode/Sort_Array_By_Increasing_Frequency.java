/* Noah Park

Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If multiple values have the same frequency, sort them in decreasing order.

Return the sorted array.

*/

class Solution {
    public int[] frequencySort(int[] nums) {
        int[] freq = new int[201];
        for(int num : nums)
            freq[num + 100]++;
        
        int idx = 0;
        for(int i = 1; i < nums.length; i++){
            for(int j = freq.length - 1; j >= 0; j--){
                if(freq[j] == i){
                    while(freq[j] > 0){
                        nums[idx++] = j - 100;
                        freq[j]--;
                    }
                }
            }
        }
        
        return nums;
    }
}
