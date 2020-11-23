/* Noah Park

Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.

Find the kth positive integer that is missing from this array.

*/

class Solution {
    public int findKthPositive(int[] arr, int k) {
        // cur is our current value, idx is our index into arr, n is the array length, and missing is the number of missing numbers we have seen so far
        int cur = 1, idx = 0, n = arr.length, missing = 0;
        
        while(missing < k){ // loop through the proper number of missing numbers
            if(idx < n && cur == arr[idx]) idx++; // if idx is valid and it is contained in the arr, move the idx forward but don't touch missing
            else missing++; // else the cur number is missing and can update cur
            cur++; // update cur each iteration
        }
        
        // return cur - 1, as cur will be one in front of the value we want since we started missing at 0 instead of 1
        return cur - 1;
    }
}
