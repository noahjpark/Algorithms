/* Noah Park

There is a hidden integer array arr that consists of n non-negative integers.

It was encoded into another integer array encoded of length n - 1, such that encoded[i] = arr[i] XOR arr[i + 1]. For example, if arr = [1,0,2,1], then encoded = [1,2,3].

You are given the encoded array. You are also given an integer first, that is the first element of arr, i.e. arr[0].

Return the original array arr. It can be proved that the answer exists and is unique.

*/

class Solution {
    
    // Intuition: Utilize previously computed values to calculate the current one. Simply xor the previous value with the encoded one to get the current.
    // Time: O(n) to iterate over encoded.
    // Space: O(n) for the output array.
    public int[] decode(int[] encoded, int first) {
        if (encoded == null || encoded.length == 0) return new int[]{};
        
        int n = encoded.length;
        int[] res = new int[n + 1];
        res[0] = first;
        
        for (int i = 1; i < n + 1; i++) {
            res[i] = first ^ encoded[i - 1];
            first = res[i];
        }
        
        return res;
    }
}
