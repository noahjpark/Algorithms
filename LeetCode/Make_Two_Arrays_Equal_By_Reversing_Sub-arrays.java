/* Noah Park

Given two integer arrays of equal length target and arr.

In one step, you can select any non-empty sub-array of arr and reverse it. You are allowed to make any number of steps.

Return True if you can make arr equal to target, or False otherwise.

*/

class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        // In order to make target from arr, arr must contain exactly the numbers that target does.
        // We can keep reversing any subarray of size two to get the proper array.
        // Essentially, we are just comparing integers within the arrays.
        int[] freq = new int[1001];
        int n = target.length;
        
        // Update the freq array to add the values from target and subtract the values from arr. If the arrays contain the same numbers, all freq values will be 0.
        for(int i = 0; i < n; i++){
            freq[target[i]]++;
            freq[arr[i]]--;
        }
        
        // Check the numbers from target and see if they all return 0. Return false if not.
        for(int num : target)
            if(freq[num] != 0) return false;
        
        return true;
    }
}
