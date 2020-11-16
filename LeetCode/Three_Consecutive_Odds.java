/* Noah Park

Given an integer array arr, return true if there are three consecutive odd numbers in the array. Otherwise, return false.

*/

class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        if(arr.length < 3) return false;
        
        int p1 = 0, p2 = 1, p3 = 2;
        while(p3 < arr.length){
            if(arr[p1] % 2 == 1 && arr[p2] % 2 == 1 && arr[p3] % 2 == 1) return true;
            p1++; p2++; p3++;
        }
        
        return false;
    }
}
