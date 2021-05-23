/* Noah Park

Given a number n, return true if and only if it is a confusing number, which satisfies the following condition:

We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid. A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.

*/

class Solution {
    
    // Intuition: Maintain what numbers can be mapped to a valid number. Create the rotated number and ensure it doesn't match the original.
    // Time: O(n) to count the digits in n.
    // Space: O(1) constant.
    public boolean confusingNumber(int n) {
        int[] map = new int[10];
        Arrays.fill(map, -1);
        map[0] = 0;
        map[1] = 1;
        map[6] = 9;
        map[8] = 8;
        map[9] = 6;
        
        int num = 0;
        for (int t = n; t > 0; t /= 10) {
            if (map[t % 10] == -1) return false;
            num = (num + (map[t % 10]));
            if (t >= 10) num *= 10;
        }
        
        return num != n;
    }
}
