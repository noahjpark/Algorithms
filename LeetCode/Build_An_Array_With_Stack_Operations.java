/* Noah Park

Given an array target and an integer n. In each iteration, you will read a number from  list = {1,2,3..., n}.

Build the target array using the following operations:

Push: Read a new element from the beginning list, and push it in the array.
Pop: delete the last element of the array.
If the target array is already built, stop reading more elements.
Return the operations to build the target array. You are guaranteed that the answer is unique.

*/

class Solution {
    
    // Intuition: If we have a matching value in the sequence, we can simply push, otherwise we will add a "Push" and "Pop" since the current number is not in the sequence until we reach a point where we have a match again.
    // Time: O(n) to iterate up to n.
    // Space: O(1) if not counting the resulting list, otherwise O(n) to maintain the result.
    public List<String> buildArray(int[] target, int n) {
        List<String> res = new ArrayList<>();
        int cur = 1;
        
        for (int i = 0; i < target.length; i++, cur++) {
            while (cur != target[i]) {
                res.add("Push");
                res.add("Pop");
                cur++;
            }
            res.add("Push");
        }
        
        return res;
    }
}
