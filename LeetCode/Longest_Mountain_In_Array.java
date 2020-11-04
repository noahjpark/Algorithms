/* Noah Park

Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:

B.length >= 3
There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
(Note that B could be any subarray of A, including the entire array A.)

Given an array A of integers, return the length of the longest mountain. 

Return 0 if there is no mountain.

*/

class Solution {
    public int longestMountain(int[] A) {
        int longest = 0;
        for(int i = 1; i < A.length - 1; i++){
            if(A[i] > A[i + 1] && A[i] > A[i - 1]){
                int left = i - 1;
                int right = i + 1;
                while(left > -1 && A[left] < A[left + 1]) left--;
                while(right < A.length && A[right] < A[right - 1]) right++;
                longest = Math.max(longest, right - left - 1);
                i = right - 1;
            }
        }
        return longest;
    }
}
