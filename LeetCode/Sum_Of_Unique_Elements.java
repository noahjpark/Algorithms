/* Noah Park

You are given an integer array nums. The unique elements of an array are the elements that appear exactly once in the array.

Return the sum of all the unique elements of nums.

*/

class Solution {

    // Intuition: Same as hashmap but optimized since we know the length of the array is at most 100.
    // Time: O(1) since nums length is at most 101 and the second loop always loops through all 101 spaces
    // Space: O(1) since the size is always 101 to store the frequencies
    public int sumOfUnique(int[] nums) {
        int[] freq = new int[101];
        int sum = 0;
        
        for (int num : nums) 
            freq[num]++;
        
        for (int i = 0; i < 101; i++)
            if (freq[i] == 1) sum += i;
        
        return sum;
    }
    
    // Intuition: Sort to count the frequecies of individual numbers. We can easily add ones that only occur once then.
    // Time: O(n log n) to sort the array
    // Space: O(1) constant
    public int sumOfUnique2(int[] nums) {
        if (nums.length == 1) return nums[0];
        
        Arrays.sort(nums);
        int sum = 0;
        
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != nums[i + 1]) sum += nums[i];
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) i++;
        }
        
        if (nums[nums.length - 1] != nums[nums.length - 2]) sum += nums[nums.length - 1];
        
        return sum;
    }
    
    // Intuition: Store the frequencies of the numbers in the array. Make a second pass to sum the ones that only occurred once.
    // Time: O(n) two pass solution
    // Space: O(n) for the hashmap
    public int sumOfUnique3(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int num : nums) 
            map.put(num, map.getOrDefault(num, 0) + 1);
        
        int sum = 0;
        
        for (Integer num : map.keySet()) 
            if (map.get(num) == 1) sum += num;
        
        return sum;
    }
}
