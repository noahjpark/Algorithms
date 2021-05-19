/* Noah Park

You are given an integer length and an array updates where updates[i] = [startIdxi, endIdxi, inci].

You have an array arr of length length with all zeros, and you have some operation to apply on arr. In the ith operation, you should increment all the elements arr[startIdxi], arr[startIdxi + 1], ..., arr[endIdxi] by inci.

Return arr after applying all the updates.

*/

class Solution {
    
    // Intuition: Range caching. Updates the outsides of the range instead of naively iterating over each range. To maintain the stopping point of the range, we subtract the value at the point right after the end. As we accumulate, this will result in a stopping point of adding that particular range.
    // Time: O(n + m) for the length of each array.
    // Space: O(n) for the reutrn array/O(1) constant otherwise.
    public int[] getModifiedArray(int l, int[][] updates) {
        int[] res = new int[l];
        
        for (int[] update : updates) {
            res[update[0]] += update[2];
            if (update[1] + 1 < l) res[update[1] + 1] -= update[2];
        }
        
        for (int num : res)
            System.out.print(num + " " );
        
        for (int i = 1; i < l; i++)
            res[i] += res[i - 1];
        
        return res;
    }
    
    // Intuition: Brute force update array with all updates.
    // Time: O(n*m) to iterate update the array with each update.
    // Space: O(n) for the return array/O(1) constant otherwise.
    public int[] getModifiedArray2(int length, int[][] updates) {
        int[] res = new int[length];
        
        for (int[] update : updates) 
            update(res, update[0], update[1], update[2]);
        
        return res;
    }
    
    public void update(int[] arr, int i, int j, int val) {
        for (int k = i; k <= j; k++)
            arr[k] += val;
    }
}
