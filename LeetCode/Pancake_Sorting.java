/* Noah Park

Given an array of integers arr, sort the array by performing a series of pancake flips.

In one pancake flip we do the following steps:

Choose an integer k where 1 <= k <= arr.length.
Reverse the sub-array arr[0...k-1] (0-indexed).
For example, if arr = [3,2,1,4] and we performed a pancake flip choosing k = 3, we reverse the sub-array [3,2,1], so arr = [1,2,3,4] after the pancake flip at k = 3.

Return an array of the k-values corresponding to a sequence of pancake flips that sort arr. Any valid answer that sorts the array within 10 * arr.length flips will be judged as correct.

*/

class Solution {
    
    // Intuition: Maintain (from the end) the current sorted portion at the end of the array. Find the current largest value between 0 and that end then either reverse from 0 through that point to move that largest value to the start. Otherwise, if it already at the start, reverse through the marked sorted end to move it the the end of the "unsorted" subarray.
    // Time: O(n^2) since we may have to do n reversals n times.
    // Space: O(n) for the return array.
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> res = new ArrayList<>();
        int end = arr.length - 1;
        
        while (end > 0) {
            int maxIdx = 0;
            boolean sorted = true;
            
            for (int i = 0; i <= end; i++) {
                if (i > 0 && arr[i] < arr[i - 1]) sorted = false;
                if (arr[i] > arr[maxIdx]) maxIdx = i;
            }
            
            if (sorted) break;
            if (maxIdx == 0) maxIdx = end--;
            
            res.add(maxIdx + 1);
            reverse(arr, maxIdx + 1);
        }
        
        return res;
    }
    
    // Reverses the subarray from 0 up to e (exclusive)
    public void reverse(int[] arr, int e) {
        for (int i = 0; i < e / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[e - i - 1];
            arr[e - i - 1] = temp;
        }
    }
}
