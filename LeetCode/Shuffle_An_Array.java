/* Noah Park

Given an integer array nums, design an algorithm to randomly shuffle the array.

Implement the Solution class:

Solution(int[] nums) Initializes the object with the integer array nums.
int[] reset() Resets the array to its original configuration and returns it.
int[] shuffle() Returns a random shuffling of the array.

*/

class Solution {
    
    // Intuition: Improved Fisher-Yates algorithm to swap indices as we move throughout them resulting in a similar shuffle as the lesser impressive Fisher-Yates algorithm below.
    // Time: O(n) to shuffle.
    // Space: O(n) to maintain the original array.
    int[] sol;
    Random r;
    
    public Solution(int[] nums) {
        sol = nums;
        r = new Random();
    }
    
    public int[] reset() {
        return sol;
    }
    
    public int[] shuffle() {
        int[] shuffled = sol.clone();
        
        for (int i = 0; i < shuffled.length; i++) 
            swap(shuffled, i, i + r.nextInt(shuffled.length - i));
        
        return shuffled;
    }
    
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }    
    
}

// class Solution {
    
// Intuition: Utilize an arraylist of random indices that we can randomly pull from then remove in shuffled resulting in quadratic time complexity.
// Time: O(n^2) due to calls to remove in a loop.
// Space: O(n) to store the indices.
//     int[] sol;
//     List<Integer> indices;
//     Random r;

//     public Solution(int[] nums) {
//         r = new Random();
//         sol = nums;
//         indices = new ArrayList<>();
//         for (int i = 0; i < sol.length; i++)
//             indices.add(i);
//     }
    
//     /** Resets the array to its original configuration and return it. */
//     public int[] reset() {
//         return sol;
//     }
    
//     /** Returns a random shuffling of the array. */
//     public int[] shuffle() {
//         int[] shuffled = new int[sol.length];
//         int i = 0;
//         List<Integer> temp = new ArrayList<>(indices);
        
//         while (temp.size() > 0) {
//             int rand = r.nextInt(temp.size());
//             shuffled[i++] = sol[temp.get(rand)];
//             temp.remove(rand);
//         }
        
//         return shuffled;
//     }
// }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
