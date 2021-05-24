/* Noah Park

We have an integer array nums, where all the integers in nums are 0 or 1. You will not be given direct access to the array, instead, you will have an API ArrayReader which have the following functions:

int query(int a, int b, int c, int d): where 0 <= a < b < c < d < ArrayReader.length(). The function returns the distribution of the value of the 4 elements and returns:
4 : if the values of the 4 elements are the same (0 or 1).
2 : if three elements have a value equal to 0 and one element has value equal to 1 or vice versa.
0 : if two element have a value equal to 0 and two elements have a value equal to 1.
int length(): Returns the size of the array.
You are allowed to call query() 2 * n times at most where n is equal to ArrayReader.length().

Return any index of the most frequent value in nums, in case of tie, return -1.

Follow up: What is the minimum number of calls needed to find the majority element?

*/

/**
 * // This is the ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface ArrayReader {
 *   public:
 *     // Compares 4 different elements in the array
 *     // return 4 if the values of the 4 elements are the same (0 or 1).
 *     // return 2 if three elements have a value equal to 0 and one element has value equal to 1 or vice versa.
 *     // return 0 : if two element have a value equal to 0 and two elements have a value equal to 1.
 *     public int query(int a, int b, int c, int d);
 *
 *     // Returns the length of the array
 *     public int length();
 * };
 */

class Solution {
    
    // Intuition: Initialize first five numbers by leaving one out at each point and considering the change. From here, we just iterate over the rest and use the value we have previously in the map.
    // Time: O(n) to iterate over the array.
    // Space: O(n) to map the array.
    public int guessMajority(ArrayReader reader) {
        int i1 = -1, i2 = -1, n = reader.length(), c1 = 0, c2 = 0;
        boolean[] arr = new boolean[n];
        
        int n1 = reader.query(0, 1, 2, 3), n2 = reader.query(1, 2, 3, 4), n3 = reader.query(0, 2, 3, 4), n4 = reader.query(0, 1, 3, 4), n5 = reader.query(0, 1, 2, 4);
        if (n1 != n2) arr[4] = true;
        if (n2 != n3) arr[1] = true;
        arr[2] = n3 != n4 ? !arr[1] : arr[1];
        arr[3] = n4 != n5 ? !arr[2] : arr[2];
        
        for (int i = 0; i < 5; i++) {
            if (!arr[i]) { i1 = i; c1++; }
            else { i2 = i; c2++; }
        }
        
        int prev = n2;
        for (int i = 2; i + 3 < n; i++) {
            int cur = reader.query(i, i + 1, i + 2, i + 3);
            arr[i + 3] = cur != prev ? !arr[i - 1] : arr[i - 1];
            prev = cur;
            
            if (!arr[i + 3]) { i1 = i + 3; c1++; }
            else { i2 = i + 3; c2++; }
        }
        
        return c1 == c2 ? -1 : c1 > c2 ? i1 : i2;
    }
}
