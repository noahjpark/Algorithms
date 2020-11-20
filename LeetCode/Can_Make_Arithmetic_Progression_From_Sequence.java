/* Noah Park

Given an array of numbers arr. A sequence of numbers is called an arithmetic progression if the difference between any two consecutive elements is the same.

Return true if the array can be rearranged to form an arithmetic progression, otherwise, return false.

 

*/

class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr); // sort array
        
        // now we can check each two values for the matching difference
        int diff = arr[1] - arr[0];
        for(int i = 2; i < arr.length; i++)
            if(arr[i] - arr[i - 1] != diff) return false;
        return true;
    }
}
