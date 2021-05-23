/* Noah Park

We have an integer array arr, where all the integers in arr are equal except for one integer which is larger than the rest of the integers. You will not be given direct access to the array, instead, you will have an API ArrayReader which have the following functions:

int compareSub(int l, int r, int x, int y): where 0 <= l, r, x, y < ArrayReader.length(), l <= r and x <= y. The function compares the sum of sub-array arr[l..r] with the sum of the sub-array arr[x..y] and returns:
1 if arr[l]+arr[l+1]+...+arr[r] > arr[x]+arr[x+1]+...+arr[y].
0 if arr[l]+arr[l+1]+...+arr[r] == arr[x]+arr[x+1]+...+arr[y].
-1 if arr[l]+arr[l+1]+...+arr[r] < arr[x]+arr[x+1]+...+arr[y].
int length(): Returns the size of the array.
You are allowed to call compareSub() 20 times at most. You can assume both functions work in O(1) time.

Return the index of the array arr which has the largest integer.

Follow-up:

What if there are two numbers in arr that are bigger than all other numbers?
What if there is one number that is bigger than other numbers and one number that is smaller than other numbers?

*/

/**
 * // This is ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface ArrayReader {
 *     // Compares the sum of arr[l..r] with the sum of arr[x..y] 
 *     // return 1 if sum(arr[l..r]) > sum(arr[x..y])
 *     // return 0 if sum(arr[l..r]) == sum(arr[x..y])
 *     // return -1 if sum(arr[l..r]) < sum(arr[x..y])
 *     public int compareSub(int l, int r, int x, int y) {}
 *
 *     // Returns the length of the array
 *     public int length() {}
 * }
 */

class Solution {
    
    // Intuition: There are two situations -> The subarray length is odd or even. This is important, as if the length is odd, we have to exclude the midpoint in our summing of each side as it will throw off the comparison of each subarray. In the odd case, we consider everything left of the midpoint and everything right of the midpoint. If they are the same, the midpoint is the answer, otherwise we either update right or left based on the value returned. For the even case, we do the same as the odd except include the midpoint and consider moving left/right. When the loop breaks, left will be on the largest number in the array.
    public int getIndex(ArrayReader reader) {
        int left = 0, right = reader.length() - 1;
        
        while (left < right) {
            int len = right - left + 1, mid = left + (right - left) / 2;
            if (len % 2 == 1) {
                int val = reader.compareSub(left, mid - 1, mid + 1, right);
                if (val == 0) return mid;
                else if (val == 1) right = mid;
                else left = mid + 1;
            } else {
                if (reader.compareSub(left, mid, mid + 1, right) == 1) right = mid;
                else left = mid + 1;
            }
        }
        
        return left;
    }
}
