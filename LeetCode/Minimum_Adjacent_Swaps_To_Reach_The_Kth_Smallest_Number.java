/* Noah Park

You are given a string num, representing a large integer, and an integer k.

We call some integer wonderful if it is a permutation of the digits in num and is greater in value than num. There can be many wonderful integers. However, we only care about the smallest-valued ones.

For example, when num = "5489355142":
The 1st smallest wonderful integer is "5489355214".
The 2nd smallest wonderful integer is "5489355241".
The 3rd smallest wonderful integer is "5489355412".
The 4th smallest wonderful integer is "5489355421".
Return the minimum number of adjacent digit swaps that needs to be applied to num to reach the kth smallest wonderful integer.

The tests are generated in such a way that kth smallest wonderful integer exists.

*/

class Solution {
    
    // Intuition: Utilize the next permutation solution to build into this problem. We simply call nextPermutation k times then count the number of swaps from the original to the permuted sequence.
    // Time: O(n^2 + n*k) n*k for the nextPermutation calls, n^2 for the counting of the swaps.
    // Space: O(n) to build the new permutation.
    public int getMinSwaps(String num, int k) {
        char[] nums = num.toCharArray();
        
        for (; k > 0; k--) 
            nextPermutation(nums);
        
        return countSwaps(nums, num.toCharArray());
    }
    
    // Swap until the original sequence matches the permuted one.
    public int countSwaps(char[] nums, char[] original) {
        int j = 0, swaps = 0;

        for (int i = 0; i < nums.length; i++) {
            j = i;

            while (original[j] != nums[i]) j++;

            while (i < j) {
                char temp = original[j];
                original[j] = original[j - 1];
                original[j-- - 1] = temp;
                swaps++;
            }
        }
        
        return swaps;
    }
    
    public void nextPermutation(char[] nums) {
        int i = nums.length - 2;
        while (i > -1 && nums[i] >= nums[i + 1]) i--;

        if (i > -1) {
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }

        Arrays.sort(nums, i + 1, nums.length);
    }
    
    public void swap(char[] nums, int i, int j) {
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
