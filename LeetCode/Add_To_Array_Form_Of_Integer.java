/* Noah Park

The array-form of an integer num is an array representing its digits in left to right order.

For example, for num = 1321, the array form is [1,3,2,1].
Given num, the array-form of an integer, and an integer k, return the array-form of the integer num + k.

*/

class Solution {
    
    // Intuition: Add each number and maintain the remainder to build the list as we go.
    // Time: O(max(n, log(k))) the longer of the length of A or the number of digits in k.
    // Space: O(n) for the resulting list / O(1) constant otherwise.
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> res = new LinkedList<>();
        int rem = 0, n = A.length;
        
        for (int i = n - 1; i >= 0 || K > 0; i--, K /= 10) {
            int add = (i >= 0 ? A[i] : 0) + K % 10 + rem;
            
            if (add > 9) { add -= 10; rem = 1; }
            else rem = 0;
            
            res.add(0, add);
        }
        
        if (rem == 1) res.add(0, rem);
        
        return res;
    }
}
