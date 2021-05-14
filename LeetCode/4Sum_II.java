/* Noah Park

Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of tuples (i, j, k, l) such that:

0 <= i, j, k, l < n
nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0

*/

class Solution {
    
    // Intuition: First approach I attempted was brute force quad nested loops O(n^4) which TLE'd. Then I attempted to do a triple nested loop with hashmap complement approach and realized I could try doing this with a full half split. I first attempted to use two hashmaps but was missing out on some combinations. I then realized that I would have had to multiply the values together. In this way I am looping through the final two arrays after populating the hashmap to make all possible combinations that add to 0.
    // Time: O(n^2) two passes.
    // Space: O(n^2) for the map.
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums1.length;
        
        for (int i = 0; i < n; i++) 
            for (int j = 0; j < n; j++) 
                map.put(nums1[i] + nums2[j], map.getOrDefault(nums1[i] + nums2[j], 0) + 1);
            
        int count = 0;
        
        for (int i = 0; i < n; i++) 
            for (int j = 0; j < n; j++) 
                count += map.getOrDefault(-(nums3[i] + nums4[j]), 0);
        
        return count;
    }
}
