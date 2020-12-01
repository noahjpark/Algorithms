/* Noah Park

Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Notice that the solution set must not contain duplicate triplets.

*/

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums); // sort the numbers
        Set<List<Integer>> set = new HashSet<>(); // set will prevent duplicates
        for(int i = 0; i < nums.length - 2; i++){ // iterate while there are three distinct numbers
            int left = i + 1, right = nums.length - 1; // left/right pointers
            while(left < right){ // two pointer technique
                int sum = nums[i] + nums[left] + nums[right];
                if(sum == 0) set.add(Arrays.asList(nums[i], nums[left++], nums[right--])); // we have a match
                else if(sum < 0) left++; // else move pointers accordingly
                else right--;
            }
        }
        
        return new ArrayList<>(set);
    }
}
