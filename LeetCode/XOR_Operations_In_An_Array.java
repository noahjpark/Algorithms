/* Noah Park

Given an integer n and an integer start.

Define an array nums where nums[i] = start + 2*i (0-indexed) and n == nums.length.

Return the bitwise XOR of all elements of nums.

*/

class Solution {
    public int xorOperation(int n, int start) {
        int total = start;
        for(int i = 1; i < n; i++){
            start += 2;
            total ^= start;
        }
        return total;
    }
}

// There is an O(1) solution, but it would've been tricky for me to come up with.
