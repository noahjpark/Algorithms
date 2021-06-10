/* Noah Park

Given an integer array arr, and an integer target, return the number of tuples i, j, k such that i < j < k and arr[i] + arr[j] + arr[k] == target.

As the answer can be very large, return it modulo 109 + 7.

*/

class Solution {
    
    // Intuition: Essentially we are considering how many ways we can choose k from n given different situations for i, j, and k. If they are all the same, we can take the total * choosing one less * choosing another less. If two values are the same, we can choose the singular value and the other two are like the previous case. Otherwise, we can use all numbers. To achieve this, we map all numbers (could use a hash map for this) to solve this situation.
    // Time: O(n) to iterate over nums.
    // Space: O(1) / O(n) if mapping all combinations.
    public int threeSumMulti(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        
        long[] map = new long[101];
        for (int num : nums)
            map[num]++;
        
        long res = 0;
        int mod = (int) (1e9 + 7), n = nums.length;
        
        for (int i = 0; i < 101; i++) {
            for (int j = i; j < 101; j++) {
                int k = target - i - j;
                if (k > 100 || k < 0) continue;
                
                if (i == j && j == k) res += map[i] * (map[i] - 1) * (map[i] - 2) / 6;
                else if (i == j && j != k) res += map[i] * (map[i] - 1) / 2 * map[k];
                else if (i < k && j < k) res += map[i] * map[j] * map[k];
                
                res %= mod;
            }
        }
        
//         for (int i = 0; i < 101; i++) {
//             int k = target - 2*i;
//             if (k > i && k < 101) {
//                 res += map[i] * (map[i] - 1) / 2 * map[k];
//                 res %= mod;
//             }
//         }
        
//         for (int i = 0; i < 101; i++) {
//             if (target % 2 == i % 2) {
//                 int k = (target - i) / 2;
//                 if (k > i && k < 101) {
//                     res += map[i] * map[k] * (map[k] - 1) / 2;
//                     res %= mod;
//                 }
//             }
//         }
        
        // if (target % 3 == 0) {
        //     int i = target / 3;
        //     if (i < 101 && i >= 0) {
        //         res += map[i] * (map[i] - 1) * (map[i] - 2) / 6;
        //         res %= mod;
        //     }
        // }
        
        return (int) res;
    }
    
}
