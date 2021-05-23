/* Noah Park

In some array arr, the values were in arithmetic progression: the values arr[i + 1] - arr[i] are all equal for every 0 <= i < arr.length - 1.

A value from arr was removed that was not the first or last value in the array.

Given arr, return the removed value.

*/

class Solution {
    
    // Intuition: Maintain the largest and smallest difference between two numbers. At some point we find the maximum difference and maintain the number at the start of this difference. We simply add the normal difference (min) to that number in the return. To accomodate decreasing values, a check is done to see if the first two numbers are decreasing.
    // Time: O(n) to iterate over arr.
    // Space: O(1) constant.
    public int missingNumber(int[] arr) {
        int max = 0, min = Integer.MAX_VALUE, n1 = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            int a = Math.abs(arr[i] - arr[i - 1]);
            
            if (a > max) { max = a; n1 = arr[i - 1]; }
            if (a < min) min = a;
        }
        
        if (arr[0] > arr[1]) min *= -1;
        
        return n1 + min;
    }
}
